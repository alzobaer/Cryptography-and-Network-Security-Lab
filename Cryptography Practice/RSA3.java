import java.util.Scanner;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import static java.math.BigInteger.ONE;

public class RSA3 {
    BigInteger e, d, n;
    public RSA3(BigInteger e , BigInteger d , BigInteger n){
        this.e = e;
        this.d = d;
        this.n = n;
    }
    public BigInteger encrypt(BigInteger msg){
        return msg.modPow(e,n);
    }
    public BigInteger decrypt(BigInteger msg){
        return msg.modPow(d,n);
    }

    public BigInteger[] doEncrypt(String msg){
        byte[] bytes = msg.getBytes();
        BigInteger[] encrypted = new BigInteger[bytes.length];

        for(int i = 0; i < bytes.length ; i++){
            encrypted[i] = encrypt(BigInteger.valueOf(bytes[i]));
        }
        return encrypted;
    }
    public String doDecrypt(BigInteger[] enc){
        byte[] bytes = new byte[enc.length];

        for(int i = 0 ; i < enc.length ; i++){
            BigInteger decrypted = decrypt(enc[i]);
            bytes[i] = decrypted.byteValueExact();
        }
        return new String(bytes);
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
        
        RSA3 rsa = new RSA3(e,d,n);
        BigInteger[] enc = rsa.doEncrypt(msg);
        System.out.println("Encrypted message: " + Arrays.toString(enc));
        System.out.println("Decrypted Message: " + rsa.doDecrypt(enc));

    }   
}
