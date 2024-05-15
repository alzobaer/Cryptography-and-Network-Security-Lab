import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class LehmanPrimalityTest {

    public static boolean isPrime(BigInteger n, int k) {
        // Check for some simple cases
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return false;
        if (n.equals(BigInteger.valueOf(2)) || n.equals(BigInteger.valueOf(3)))
            return true;

        // Perform the Lehman primality test k times
        for (int i = 0; i < k; i++) {
            BigInteger a = getRandomBase(n);
            BigInteger x = a.modPow(n.subtract(BigInteger.ONE).divide(BigInteger.valueOf(2)), n);

            if (!x.equals(BigInteger.ONE) && !x.equals(n.subtract(BigInteger.ONE)))
                return false;
        }

        return true;
    }

    private static BigInteger getRandomBase(BigInteger n) {
        Random random = new Random();
        BigInteger a;
        do {
            a = new BigInteger(n.bitLength(), random);
        } while (a.compareTo(n) >= 0 || a.compareTo(BigInteger.ONE) <= 0);
        return a;
    }

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);  
        
        System.out.print("Enter Number to check prime: ");;  
        BigInteger number = in.nextBigInteger();  
        int k = 10;

        boolean isPrime = isPrime(number, k);
        System.out.println("Is " + number + " prime? \n= " + isPrime);
    }
}
