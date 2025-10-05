public class Creature {
    String name;
    double size;
    String color;  // Add color property

    // Constructor to initialize Creature
    public Creature(String name, double size, String color) {
        this.name = name;
        this.size = size;
        this.color = color;  // Initialize color
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

    // Method to display Creature information
    public void display() {
        System.out.println(name + " " + size + " " + color);
    }
}
