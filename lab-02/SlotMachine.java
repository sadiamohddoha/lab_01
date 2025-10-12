import java.io.*;
import java.util.Random;
import java.util.Scanner; 

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
            System.out.println("Slot machine state loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            this.moneyPot = 1000000.00; 
            System.out.println("Could not load slot machine state. Starting new pot: $" + this.moneyPot);
        }
    }
    public double pullLever(double amount) {
        Random rand = new Random();
        // Symbols: Happy face, Heart, Lucky 7
        char[] symbols = {'☺', '❤', '7'}; 
        symbol1 = symbols[rand.nextInt(3)];
        symbol2 = symbols[rand.nextInt(3)];
        symbol3 = symbols[rand.nextInt(3)];

        if (symbol1 == symbol2 && symbol2 == symbol3) {
            double prize = amount * 10; 
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

    // Getter 
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
            System.out.println("Customer wallet loaded from " + filename);
        } catch (IOException | NumberFormatException e) {
            this.wallet = 500.00; 
            System.out.println("Could not load customer wallet. Starting new wallet: $" + this.wallet);
        }
    } 
    public void spend(double amount) {
        if (wallet >= amount) {
            wallet -= amount;
        } else {
            wallet = 0; 
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
            System.out.println("Wallet saved to " + filename);
        } catch (IOException e) {
            System.out.println("Error saving wallet data.");
        }
    }
}
class GoodCasino {

    public static double play(Customer customer, SlotMachine slotMachine, double amount) {
        // 1. Customer places bet 
        customer.spend(amount); 
        // 2. Slot machine pulls lever 
        double winnings = slotMachine.pullLever(amount); 
        // 3. Customer receives winnings
        customer.receive(winnings); 
        return winnings;
    }

    public static void main(String[] args) {
        Customer customer = new Customer("customer.txt");
        SlotMachine slotMachine = new SlotMachine("slot-machine.txt");
        Scanner scanner = new Scanner(System.in); 

        System.out.println("\n=============================================");
        System.out.println("Welcome to the GoodCasino Slot Machine Game!");
        System.out.println("=============================================");
        System.out.printf("Your current wallet: $%.2f\n", customer.checkWallet());
        System.out.printf("Casino pot: $%.2f\n", slotMachine.getMoneyPot());
        System.out.println("Type 'quit' to save and stop playing.");

        // Game loop
        while (true) {
            System.out.println("\n---------------------------------------------");
            System.out.printf("Wallet: $%.2f | Pot: $%.2f\n", customer.checkWallet(), slotMachine.getMoneyPot());
            System.out.print("Enter amount to play (must be > $0.00 and <= wallet balance) or 'quit': ");
            
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("quit")) {
                System.out.println("Thank you for playing! Saving game state...");
                break;
            }

            try {
                double amount = Double.parseDouble(input);

                if (amount <= 0 || amount > customer.checkWallet()) {
                    System.out.println("Invalid bet amount. Please bet an amount greater than $0.00 and less than or equal to your wallet balance.");
                    continue;
                }
                if (slotMachine.getMoneyPot() < amount * 10) {
                     System.out.println("The casino pot is running low! Cannot cover a max win of $" + (amount * 10) + ". Try a smaller bet or quit.");
                     continue;
                }

                double winnings = play(customer, slotMachine, amount);

                System.out.println("Result: " + slotMachine.toString());
                if (winnings > 0) {
                    System.out.printf(">>> WINNER! You won $%.2f. <<<\n", winnings);
                } else {
                    System.out.println("Sorry, no win this time.");
                }

                if (customer.checkWallet() <= 0) {
                    System.out.println("=============================================");
                    System.out.println("Your wallet is empty. You're out of money. Game over!");
                    System.out.println("=============================================");
                    break;
                }

                if (slotMachine.getMoneyPot() <= 0) {
                    System.out.println("=============================================");
                    System.out.println("!!! JACKPOT BREAK !!! The slot machine is out of money. Game over!");
                    System.out.println("=============================================");
                    break;
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number or type 'quit'.");
            }
        } 

        // Save the final state
        customer.save("customer.txt");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("slot-machine.txt"));
            writer.write(String.valueOf(slotMachine.getMoneyPot()));
            writer.close();
            System.out.println("Casino pot saved to slot-machine.txt");
        } catch (IOException e) {
            System.out.println("Error saving slot machine data.");
        }
        
        scanner.close(); 
    }
}
