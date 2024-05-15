import java.util.Scanner;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import static java.math.BigInteger.ONE;

public class RSA2 {

    public BigInteger encrypt(BigInteger msg , BigInteger e, BigInteger n){
        return msg.modPow(e,n);
    }
    public BigInteger[] doEncrypt(String msg, BigInteger e, BigInteger n){
        byte[] bytes = msg.getBytes();
        BigInteger[] encrypted = new BigInteger[bytes.length];

        for(int i = 0 ; i < bytes.length ; i++){
            encrypted[i] = encrypt(BigInteger.valueOf(bytes[i]),e,n);
        }
        return encrypted;
    }

    public BigInteger decrypt(BigInteger msg , BigInteger d, BigInteger n){
        return msg.modPow(d,n);
    }

    public String doDecrypt(BigInteger[] enc, BigInteger d, BigInteger n){
        byte[] decrypted = new byte[enc.length];

        for(int i = 0 ; i < enc.length ; i++){
            BigInteger x = decrypt(enc[i],d,n);
            decrypted[i] = x.byteValueExact();
        }
        return new String(decrypted);
    }
    public static void main(String[] args) {
        Random random = new Random();

        int bitLen = 32;

        BigInteger p = BigInteger.probablePrime(bitLen/2 , random);
        BigInteger q = BigInteger.probablePrime(bitLen/2 , random);

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(ONE).multiply(q.subtract(ONE));

        BigInteger e = BigInteger.valueOf(13);
        while(e.compareTo(n) < 0 && e.gcd(phi).compareTo(ONE) != 0)
            e = e.add(ONE);

        BigInteger d = e.modInverse(phi);

        System.out.println("Encryption Key: " + e);
        System.out.println("Decryption Key: " + d);

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String msg = sc.nextLine();
        
        RSA rsa = new RSA();
        BigInteger[] enc = rsa.doEncrypt(msg,e,n);
        System.out.println("Encrypted message: " + Arrays.toString(enc));
        System.out.println("Decrypted Message: " + rsa.doDecrypt(enc,d,n));

    }   
}
