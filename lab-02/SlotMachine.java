import java.io.*;
import java.util.Random;

class SlotMachine {
    private char symbol1, symbol2, symbol3;
    private double moneyPot;

    public SlotMachine() {
        this.moneyPot = 1000000.00;
    }

    public SlotMachine(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            this.moneyPot = Double.parseDouble(reader.readLine());
            reader.close();
        } catch (IOException e) {
            this.moneyPot = 1000000.00;  
        }
    }

    public double pullLever(double amount) {
        Random rand = new Random();
        char[] symbols = {'☺', '❤', '7'};
        symbol1 = symbols[rand.nextInt(3)];
        symbol2 = symbols[rand.nextInt(3)];
        symbol3 = symbols[rand.nextInt(3)];

        if (symbol1 == symbol2 && symbol2 == symbol3) {
            double prize = amount * 10;  // Win 10 times the bet
            moneyPot -= prize;  
            return prize;
        } else {
            moneyPot += amount;  
            return 0;
        }
    }

    public String toString() {
        return "" + symbol1 + symbol2 + symbol3;
    }

    public double getMoneyPot() {
        return moneyPot;
    }
}

class Customer {
    private double wallet;

    public Customer() {
        this.wallet = 500.00;
    }

    public Customer(String filename) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            this.wallet = Double.parseDouble(reader.readLine());
            reader.close();
        } catch (IOException e) {
            this.wallet = 500.00;  
    }

    public void spend(double amount) {
        if (wallet >= amount) {
            wallet -= amount;
        } else {
            wallet = 0;  // If not enough money, set wallet to 0
        }
    }

    public void receive(double amount) {
        wallet += amount;
    }

    public double checkWallet() {
        return wallet;
    }

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
    public static double play(Customer customer, SlotMachine slotMachine, double amount) {
        customer.spend(amount);  // Customer spends money
        double winnings = slotMachine.pullLever(amount);  // Slot machine pulls lever
        customer.receive(winnings);  // Customer receives winnings
        return winnings;
    }

    public static void main(String[] args) {
        Customer customer = new Customer("customer.txt");
        SlotMachine slotMachine = new SlotMachine("slot-machine.txt");

        System.out.println("Welcome to the GoodCasino Slot Machine Game!");
        System.out.println("You start with $" + customer.checkWallet() + " in your wallet.");
        System.out.println("The slot machine has a starting pot of $" + slotMachine.getMoneyPot());
        System.out.println("To play, simply enter an amount you want to bet. If you want to quit, type 'quit'.");

        // Game loop
        while (true) {
            System.out.println("\nYou have $" + customer.checkWallet() + " in your wallet.");
            System.out.print("Enter amount to play (or type 'quit' to stop): ");
            String input = System.console().readLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for playing! Goodbye!");
                break;
            }

            try {
                double amount = Double.parseDouble(input);

                if (amount <= 0 || amount > customer.checkWallet()) {
                    System.out.println("Invalid amount. You can't bet more than what you have or bet a negative amount. Try again.");
                    continue;
                }

                double winnings = play(customer, slotMachine, amount);

                System.out.println("The slot machine shows: " + slotMachine.toString());
                if (winnings > 0) {
                    System.out.println("Congratulations! You won $" + winnings + ". Your current wallet balance is $" + customer.checkWallet());
                } else {
                    System.out.println("Sorry, you didn't win this time. Your current wallet balance is $" + customer.checkWallet());
                }

                // If the customer or machine is out of money, stop the game
                if (customer.checkWallet() <= 0) {
                    System.out.println("Your wallet is empty. Game over!");
                    break;
                }

                if (slotMachine.getMoneyPot() <= 0) {
                    System.out.println("The slot machine is out of money. Game over!");
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

