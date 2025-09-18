public class Creature {
    String name;
    double size;

    // Constructor
    public Creature(String name, double size) {
        this.name = name;
        this.size = size;
    }

    // Action methods
    public void sleep() {
        System.out.println(name + " is sleeping peacefully.");
    }

    public void play(String activity) {
        System.out.println(name + " is playing " + activity + ".");
    }

    public void eat(String food) {
        System.out.println(name + " eats " + food + ".");
    }

    // Main method
    public static void main(String[] args) {
        Creature c = new Creature("Fluff", 0.8);
        c.sleep();
        c.play("catching a ball");
        c.eat("carrots");
    }
}
