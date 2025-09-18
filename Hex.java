import java.math.BigInteger;

public class Hex {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("Usage: java Hex <hex-number>");
            System.exit(1);
        }

        String hex = args[0].trim();
        if (hex.startsWith("0x") || hex.startsWith("0X")) {
            hex = hex.substring(2);
        }
        if (hex.length() == 0) {
            System.err.println("Error: empty hex string");
            System.exit(2);
        }

        BigInteger result = BigInteger.ZERO;
        for (int i = 0; i < hex.length(); i++) {
            char c = hex.charAt(i);
            int digit = Character.digit(c, 16);
            if (digit == -1) {
                System.err.printf("Error: '%c' is not a hex digit%n", c);
                System.exit(3);
            }
            result = result.multiply(BigInteger.valueOf(16)).add(BigInteger.valueOf(digit));
        }

        System.out.println(result.toString());
    }
}