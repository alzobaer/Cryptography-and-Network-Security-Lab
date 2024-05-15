import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class RabinMillerPrimalityTest {

    public static boolean isPrime(BigInteger n, int k) {
        // Check for some simple cases
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE))
            return false;
        if (n.equals(BigInteger.valueOf(2)) || n.equals(BigInteger.valueOf(3)))
            return true;

        // Write n-1 as 2^r * d
        BigInteger d = n.subtract(BigInteger.ONE);
        int r = 0;
        while (d.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO)) {
            d = d.divide(BigInteger.valueOf(2));
            r++;
        }

        // Perform the Rabin-Miller primality test k times
        for (int i = 0; i < k; i++) {
            BigInteger a = getRandomBase(n);
            BigInteger x = a.modPow(d, n);

            if (!x.equals(BigInteger.ONE) && !x.equals(n.subtract(BigInteger.ONE))) {
                boolean isComposite = true;
                for (int j = 0; j < r - 1; j++) {
                    x = x.modPow(BigInteger.valueOf(2), n);
                    if (x.equals(BigInteger.ONE))
                        return false;
                    if (x.equals(n.subtract(BigInteger.ONE))) {
                        isComposite = false;
                        break;
                    }
                }
                if (isComposite)
                    return false;
            }
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
