import java.math.BigInteger;
import java.util.Random;
import java.security.SecureRandom;
import java.util.Scanner;


public class DiffieHellman {
    public static void main(String[] args) {
        int bitLength = 32; //random numbers bitLength
        Random random = new SecureRandom();
        BigInteger p = BigInteger.probablePrime(bitLength, random);
        BigInteger q = BigInteger.probablePrime(bitLength - 1, random);

        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the public key for Alice: ");  
        BigInteger a = sc.nextBigInteger();
        
        System.out.print("Enter the public key for Bob: ");  
        BigInteger b = sc.nextBigInteger();

        BigInteger x = q.modPow(a, p);
        BigInteger y = q.modPow(b, p);

        BigInteger ka = y.modPow(a, p);
        BigInteger kb = x.modPow(b, p);

        System.out.println("Alice's Private Key: " + ka);
        System.out.println("Bob's Private Key: " + kb);

        sc.close();
    }
}