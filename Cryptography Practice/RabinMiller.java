import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class RabinMiller {

    public static boolean check_prime(BigInteger p , int k){
        if(p.compareTo(BigInteger.valueOf(2)) == -1)
            return false;
        if(p.equals(BigInteger.valueOf(2)) || p.equals(BigInteger.valueOf(3)))
            return true;
        
        BigInteger m = p.subtract(ONE);
        int b = 0;
        
        while(m.mod(BigInteger.valueOf(2)).equals(ZERO)){
            m = m.divide(BigInteger.valueOf(2));
            b++;
        }

        for(int i = 0 ; i < k ; i++){
            BigInteger a = getRandomBase(p);
            BigInteger z = a.modPow(m,p);

            if(z.equals(ONE) || z.equals(p.subtract(ONE)))
                continue;
            boolean composite = true;
            for(int j = 1 ; j < b ; j++){
                z = z.modPow(BigInteger.valueOf(2),p);
                if(z.equals(ONE))
                    return false;
                else{
                    composite = false;
                    break;
                }
            }
            if(composite)
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

        System.out.print("Enter a number: ");
        BigInteger p = sc.nextBigInteger();

        int k = 10;
        boolean prime = check_prime(p,k);

        if(prime)
            System.out.println(p + " is a prime");
        else 
            System.out.println(p + " is not a prime");
    }
}
