public class CreatureCLI {

    public static void main(String[] args) {

        if (args.length == 0) {
            printHelp();
            System.exit(1);
        }

        String command = args[0];
        CreatureRegistry reg = new CreatureRegistry("creature-data.csv");

        try {

            switch (command) {

                case "create": {
                    Creature c = parseCreature(args[1]);
                    reg.addCreature(c);
                    reg.save();
                    break;
                }

                case "read": {
                    int index = Integer.parseInt(args[1]);
                    Creature c = reg.getCreatureCopy(index);
                    System.out.println(c.name + " " + c.size + " " + c.color);
                    break;
                }

                case "update": {
                    int index = Integer.parseInt(args[1]);
                    Creature newC = parseCreature(args[2]);
                    reg.updateCreature(index, newC);
                    reg.save();
                    break;
                }

                case "delete": {
                    int index = Integer.parseInt(args[1]);
                    reg.deleteCreature(index);
                    reg.save();
                    break;
                }

                default:
                    printHelp();
                    System.exit(1);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }

    private static Creature parseCreature(String s) {
        // format: "name:Dragon size:500 color:Green"
        String[] parts = s.split(" ");
        String name = parts[0].split(":")[1];
        double size = Double.parseDouble(parts[1].split(":")[1]);
        String color = parts[2].split(":")[1];

        return new Creature(name, size, color);
    }

    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("java CreatureCLI create 'name:NAME size:SIZE color:COLOR'");
        System.out.println("java CreatureCLI read INDEX");
        System.out.println("java CreatureCLI update INDEX 'name:NAME size:SIZE color:COLOR'");
        System.out.println("java CreatureCLI delete INDEX");
    }
}
