

import java.nio.charset.StandardCharsets;
import org.apache.commons.codec.binary.Hex;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Auther:刘兰斌
 * @Date: 2021/05/31/9:07
 * @Explain:
 */
public class GetSHA256Str {
    public static void main(String[] args) {
        String t = "luotuo1";//刘兰斌
        String s = getsha256str(t);
        System.out.println(s);
    }
    public static String getsha256str(String string){
        String encoderes="";
        try {//初始化
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            byte[] hash = messageDigest.digest(string.getBytes(StandardCharsets.UTF_8));
            encoderes = Hex.encodeHexString(hash);


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encoderes;
    }
}
