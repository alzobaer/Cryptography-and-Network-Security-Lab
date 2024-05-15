import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class MD5 {
    public static void main(String[] args)throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the message: ");
        String msg = sc.nextLine();

        byte[] bytes = md.digest(msg.getBytes());
        BigInteger num = new BigInteger(1, bytes);

        String hashtext = num.toString(16);

        while(hashtext.length() < 32)
            hashtext = "0"+ hashtext;

        System.out.println("Hash Text: " + hashtext);

    }
}
