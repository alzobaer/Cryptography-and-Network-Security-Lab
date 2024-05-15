import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.*; 

import static java.math.BigInteger.ONE;

public class RSA {

    public byte[] doEncryption(String M , BigInteger e , BigInteger n){
        // First turns M into a number m smaller than n
        BigInteger m = new BigInteger(M.getBytes());

        //Compute the ciphertext c = m^e mod n
        byte[] c = m.modPow(e, n).toByteArray();
        String x = Arrays.toString(c);
        System.out.println("c   = " + x);
        return c;
    }

    public String doDecryption(BigInteger d , BigInteger n ,byte[] c){

        BigInteger decrypted_m = new BigInteger(c).modPow(d, n);
        String decrypted_M = new String(decrypted_m.toByteArray());

        return decrypted_M;
    }

    public static void main(String[] args){

        // 1.Chosing two random prime number 
        int bitLength = 128; //random numbers bitLength
        Random rnd = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength, rnd);
        BigInteger q = BigInteger.probablePrime(bitLength, rnd);

        // 2. Calculating n = p * q
        BigInteger n = p.multiply(q);

        // 3. Calculate phi = (p - 1) * (q - 1)
        BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));

        // 4. Calculating e such that e is coprime to phi
        BigInteger e = BigInteger.valueOf(6553);
        while(e.compareTo(n) == -1 && e.gcd(phi).compareTo(ONE) != 0){
            e.add(ONE);
        }

        // 5. Compute d such that (e * d) mod phi == 1
        BigInteger d = e.modInverse(phi);

        System.out.println("Public Key = (" + e + "," + n + ")");
        System.out.println("Private Key = (" + d + "," + n + ")");

        //Input message
        // ====================
        RSA rsaObj = new RSA();

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a string: ");  
        String M = sc.nextLine(); 
       
        //Encryping a message
        byte[] c = rsaObj.doEncryption(M, e, n);

        // Decrypting a message
        String decryptedMsg = rsaObj.doDecryption(d, n , c);
        System.out.println("decrypted_M = " + decryptedMsg);

        sc.close();
    }
}