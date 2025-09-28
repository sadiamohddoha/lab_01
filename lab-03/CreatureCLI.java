public class CreatureCLI {
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java CreatureCLI <command> <args>");
            System.exit(1);
        }

        String command = args[0];
        CreatureRegistry registry = new CreatureRegistry("creature-data.csv");

        try {
            switch (command) {
                case "create":
                    String[] createData = args[1].split(" ");
                    String name = createData[0].split(":")[1];
                    double weight = Double.parseDouble(createData[1].split(":")[1]);
                    registry.addCreature(new Creature(name, weight));
                    break;
                case "read":
                    int readIndex = Integer.parseInt(args[1]);
                    Creature c = registry.getCreature(readIndex);
                    System.out.println("Creature: " + c.name + ", Weight: " + c.size);
                    break;
                case "delete":
                    int deleteIndex = Integer.parseInt(args[1]);
                    registry.removeCreature(deleteIndex);
                    break;
                case "update":
                    int updateIndex = Integer.parseInt(args[1]);
                    String[] updateData = args[2].split(" ");
                    String updatedName = updateData[0].split(":")[1];
                    double updatedWeight = Double.parseDouble(updateData[1].split(":")[1]);
                    Creature updatedCreature = new Creature(updatedName, updatedWeight);
                    registry.getCreature(updateIndex).name = updatedName;  // Update values
                    break;
                default:
                    System.out.println("Unknown command");
                    System.exit(1);
            }
        } catch (Exception e) {
            System.out.println("Error processing command: " + e.getMessage());
            System.exit(1);
        }

        registry.saveToFile("creature-data.csv"); // Save back to file
    }
}
