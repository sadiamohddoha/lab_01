import java.io.*;
import java.util.Random;

class SlotMachine {
    private char symbol1, symbol2, symbol3;
    private double moneyPot;

    // Constructor without filename - initializes moneyPot to $1,000,000
    public SlotMachine() {
        this.moneyPot = 1000000.00;
    }

    // Constructor with filename - initializes moneyPot from file
    public SlotMachine(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            this.moneyPot = Double.parseDouble(reader.readLine());
            reader.close();
        } catch (IOException e) {
            this.moneyPot = 1000000.00;  // Default to $1,000,000 if file reading fails
        }
    }

    // Pull the lever, reducing the money pot and returning a prize
    public double pullLever(double amount) {
        Random rand = new Random();
        char[] symbols = {'☺', '❤', '7'};
        symbol1 = symbols[rand.nextInt(3)];
        symbol2 = symbols[rand.nextInt(3)];
        symbol3 = symbols[rand.nextInt(3)];

        if (symbol1 == symbol2 && symbol2 == symbol3) {
            double prize = amount * 10;  // Player wins 10 times their bet
            moneyPot -= prize;  // Decrease the money pot
            return prize;
        } else {
            moneyPot += amount;  // If no match, the machine keeps the bet
            return 0;
        }
    }

    // Convert the symbols into a string
    public String toString() {
        return "" + symbol1 + symbol2 + symbol3;
    }

    // Get the current money pot
    public double getMoneyPot() {
        return moneyPot;
    }
}

class Customer {
    private double wallet;

    // Constructor without filename - initializes wallet to $500
    public Customer() {
        this.wallet = 500.00;
    }

    // Constructor with filename - initializes wallet from file
    public Customer(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            this.wallet = Double.parseDouble(reader.readLine());
            reader.close();
        } catch (IOException e) {
            this.wallet = 500.00;  // Default to $500 if file reading fails
        }
    }

    // Spend money
    public void spend(double amount) {
        if (wallet >= amount) {
            wallet -= amount;
        } else {
            wallet = 0;  // If not enough money, set wallet to 0
        }
    }

    // Receive money
    public void receive(double amount) {
        wallet += amount;
    }

    // Check current wallet balance
    public double checkWallet() {
        return wallet;
    }

    // Save the current wallet balance to a file
    public void save(String filename) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(String.valueOf(wallet));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving wallet data.");
        }
    }
}

class GoodCasino {

    // Play the game: customer spends money, pulls lever, and receives winnings
    public static double play(Customer customer, SlotMachine slotMachine, double amount) {
        customer.spend(amount);  // Customer spends money
        double winnings = slotMachine.pullLever(amount);  // Play the slot machine
        customer.receive(winnings);  // Customer receives winnings
        return winnings;
    }

    public static void main(String[] args) {
        // Create customer and slot machine
        Customer customer = new Customer("customer.txt");
        SlotMachine slotMachine = new SlotMachine("slot-machine.txt");

        // Game loop
        while (true) {
            System.out.println("You have $" + customer.checkWallet() + " in your wallet.");
            System.out.print("Enter amount to play (or type 'quit' to stop): ");
            String input = System.console().readLine();
            if (input.equals("quit")) {
                break;
            }

            try {
                double amount = Double.parseDouble(input);
                if (amount <= 0 || amount > customer.checkWallet()) {
                    System.out.println("Invalid amount. Try again.");
                    continue;
                }

                // Play the game
                double winnings = play(customer, slotMachine, amount);
                System.out.println("The slot machine shows: " + slotMachine.toString());
                System.out.println("You won $" + winnings + ". Your current wallet balance is $" + customer.checkWallet());

                // If the customer or machine is out of money, stop the game
                if (customer.checkWallet() <= 0 || slotMachine.getMoneyPot() <= 0) {
                    System.out.println("Game Over!");
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }

        // Save the final state of customer and slot machine
        customer.save("customer.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("slot-machine.txt"));
            writer.write(String.valueOf(slotMachine.getMoneyPot()));
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving slot machine data.");
        }
    }
}
