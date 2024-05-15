import java.security.MessageDigest;
import java.util.Scanner;
import java.security.NoSuchAlgorithmException;
import java.math.BigInteger;

public class MD52 {
    public static void main(String[] args)throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        Scanner sc = new Scanner(System.in);
        String msg = sc.nextLine();

        byte[] bytes = md.digest(msg.getBytes());
        BigInteger num = new BigInteger(1,bytes);

        String hashText = num.toString(16);

        while(hashText.length() < 32)
            hashText = "0" + hashText;

        System.out.println("Hashed text: " + hashText);

    }
}
