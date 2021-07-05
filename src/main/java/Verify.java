/**
 * @Auther:刘兰斌
 * @Date: 2021/06/07/16:26
 * @Explain:
 */
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.org.apache.xerces.internal.impl.dv.util.HexBin;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


/**
 * Created by bqh on 2017/4/8.
 * <p>
 * E-mail:M201672845@hust.edu.cn
 */
public class Verify {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchProviderException, InvalidKeySpecException,
            InvalidAlgorithmParameterException,InvalidKeyException,SignatureException {
        args[0] = "ttwe";
        args[1] = "edwef";
        args[2] = "wefvw";
        if (args.length != 3) {
            System.out.println("args length invalid");
        }

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(Base64.decode(args[0]));
        KeyFactory fact = KeyFactory.getInstance("ECDSA", "BC");
        PublicKey publicKey = fact.generatePublic(x509EncodedKeySpec);

        Signature signature = Signature.getInstance("SHA1withECDSA");
        signature.initVerify(publicKey);
        signature.update(args[1].getBytes());
        boolean flag = signature.verify(HexBin.decode(args[2]));
        System.out.println(flag);
    }
}