import java.util.Scanner;
import java.util.Random;
import java.math.BigInteger;
import static java.math.BigInteger.ONE;
import static java.math.BigInteger.ZERO;

public class Rabin_Miller_Primality_Test {

    public static boolean check_prime(BigInteger p , int k){

        if(p.equals(ZERO) || p.equals(ONE))
            return false;
        if(p.equals(BigInteger.valueOf(2)) || p.equals(BigInteger.valueOf(3)))
            return true;

        BigInteger d = p.subtract(ONE);
        int b = 0;

        while(d.mod(BigInteger.valueOf(2)).equals(ZERO)){
            d = d.divide(BigInteger.valueOf(2));
            b++;
        }

        for(int i = 0 ; i < k ; i++){
            BigInteger a = getRandomBase(p);
            BigInteger z = a.modPow(d,p);

            if(z.equals(ONE) || z.equals(p.subtract(ONE))){
                continue;
            }
            boolean isComposite = true;
            for(int j = 1 ; j < b ; j++){
                z = z.modPow(BigInteger.valueOf(2),p);
                if(z.equals(ONE))
                    return false;
                else if(z.equals(p.subtract(ONE))){
                    isComposite = false;
                    break;
                }
            }
            if(isComposite)
                return false;
        }
        return true;
    } 

    public static BigInteger getRandomBase(BigInteger p){
        Random random = new Random();
        BigInteger a;
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
