import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;
import static java.math.BigInteger.ONE;

public class RSA {

     public BigInteger encrypt(BigInteger M , BigInteger e , BigInteger n){
        return M.modPow(e,n);
    }

    public BigInteger decrypt(BigInteger C, BigInteger d , BigInteger n){
        return C.modPow(d,n);
    }

    public BigInteger[] doEncrypt(String msg , BigInteger e, BigInteger n){
        byte[] bytes = msg.getBytes();
        BigInteger[] encrypted = new BigInteger[bytes.length];

        for(int i = 0 ; i < bytes.length ; i++){
            encrypted[i] = encrypt(BigInteger.valueOf(bytes[i]),e,n);
        }
        return encrypted;
    }

    public String doDecrypt(BigInteger[] encrypted, BigInteger d, BigInteger n){
        byte[] decBytes = new byte[encrypted.length];

        for(int i = 0 ; i < encrypted.length ; i++){
            BigInteger decrypted = decrypt(encrypted[i], d, n);
            decBytes[i] = decrypted.byteValueExact();
        }
        return new String(decBytes);
    }

    public static void main(String arg[]){
        int bitLen = 32;

        Random rnd = new Random();
        BigInteger p = BigInteger.probablePrime(bitLen / 2,rnd);
        BigInteger q = BigInteger.probablePrime(bitLen / 2,rnd);

        BigInteger n = p.multiply(q);
        BigInteger phi = (p.subtract(ONE)).multiply(q.subtract(ONE));

        BigInteger e = BigInteger.probablePrime(bitLen / 2 , rnd);
        while(e.compareTo(n) == -1 && e.gcd(phi).compareTo(ONE) != 0){
            e = e.add(ONE);
        }
        
        BigInteger d = e.modInverse(phi);

        System.out.println("Encryption key: {" + e + "," + n + "}");
        System.out.println("Decryption key: {" + d + "," + n + "}");

        String msg = "hello143";
        RSA rsa = new RSA();

        BigInteger[] enc = rsa.doEncrypt(msg,e,n);
        System.out.println("Encrypted message: " + Arrays.toString(enc));

        String dec = rsa.doDecrypt(enc,d,n);
        System.out.println("Decrypted message: " + dec);
    }
}
