import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.math.BigInteger;

public class SHA{
    public static void main(String[] args) throws NoSuchAlgorithmException{
        
        MessageDigest md = MessageDigest.getInstance("SHA-256");

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the message: ");
        String msg = sc.nextLine();

        byte[] bytes = md.digest(msg.getBytes());
        BigInteger num = new BigInteger(1, bytes);
        String hashText = num.toString(16);

        while(hashText.length() < 64)
            hashText = "0" + hashText;
        
        System.out.println("Hast Text: " + hashText);
    }
}