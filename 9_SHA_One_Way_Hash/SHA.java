import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class SHA {
    public static String getSHA256(String msg){
        try{
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(msg.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 64) {
                    hashtext = "0" + hashtext;
            }
            return hashtext;

        }catch(NoSuchAlgorithmException e){

            throw new RuntimeException(e);  
        }
    }
    public static void main(String[] args) throws NoSuchAlgorithmException{
        
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the message: ");
        String msg = sc.nextLine();
        System.out.println("SHA-256 generated hash code: " + getSHA256(msg));

        sc.close();
    }    
}