import java.util.Random;
import java.math.BigInteger;
import java.util.Scanner;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Lehman_primality_test {

    public static boolean check_prime(BigInteger p , int k){
        if(p.equals(ZERO) || p.equals(ONE))
            return false;
        if(p.equals(BigInteger.valueOf(2)) || p.equals(BigInteger.valueOf(3)))
            return true;

        BigInteger d = p.subtract(ONE);

        for(int i = 0 ; i < k ; i++){
            BigInteger a = getRandomBase(p);
            BigInteger x = a.modPow(d.divide(BigInteger.valueOf(2)),p);

            if(x.equals(ONE) || x.equals(d))
                continue;
            else 
                return false;
        }
        return true;
    }

    public static BigInteger getRandomBase(BigInteger p){
        BigInteger a;
        Random random = new Random();
        do{
            a = new BigInteger(p.bitLength(),random);
        }while(a.compareTo(p) >= 0 || a.compareTo(ONE) <= 0);

        return a;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number to check prime: ");
        BigInteger p = sc.nextBigInteger();

        int k = 10;
        boolean ans = check_prime(p,k);

        if(ans)
            System.out.println(p + " is prime");
        else 
            System.out.println(p + " is not prime");
    }
}
