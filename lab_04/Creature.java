public class Creature {
    // 1. Attributes 
    private String name;
    private double size;
    private String color;

    // Constructor
    public Creature(String name, double size, String color) {
        this.name = name;
        this.size = size;
        this.color = color;
    }

    // GETTERS 
    public String getName() {
        return name;
    }
    public double getSize() {
        return size;
    }
    public String getColor() {
        return color;
    }

    // SETTERS
    public void setName(String name) {
        this.name = name;
    }
    public void setSize(double size) {
        this.size = size;
    }
    public void setColor(String color) {
        this.color = color;
    }

    //  ACTION METHODS 
    public String sleep() {
        return name + " is sleeping peacefully.";
    }
    public String play(String activity) {
        return name + " is playing " + activity + ".";
    }
    public String eat(String food) {
        return name + " eats " + food + ".";
    }

    
    @Override
    public String toString() {
        return "--- CREATURE DETAILS ---\n" +
               "Name: " + name + "\n" +
               "Size: " + size + "\n" +
               "Color: " + color + "\n" +
               "------------------------";
    }
}
