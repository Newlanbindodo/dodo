import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;
import org.bitcoinj.core.ECKey;
import org.bitcoinj.wallet.Protos;
import sun.security.ec.ECPrivateKeyImpl;

import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.ECGenParameterSpec;

import static org.bouncycastle.asn1.sec.SECObjectIdentifiers.secp256k1;


/**
 * @Auther:刘兰斌
 * @Date: 2021/06/06/13:10
 * @Explain: 30450220141408036c0286f6175154caae9afde1170a2a0bba72c882197416b1e4b42557022100b209e1d45739dc1ee1ee5f244bc63680fe76bcee1ec4b81b4d548edf2e6f3
 * 72f
 * <p>
 * 3046022100f17624df7237ca0a5fc0bcab7c9bb9c5132df94500d01b9e98ee8a07290f14a5022100ef2d64b2cefb2252a274aac429bfd47ad7f3315da665d20d7081751f501f64fe
 * 3046022100eeb1f0cf2d707ac241415eddf5a32b1b0030463275f5cdc13b5aea35d44281b8022100ddaafad0787332db1fcf829d48b51c959821b612baa2e03bf8984f829e6649e0
 */
public class Secp256k1 {
    public static void main(String[] args) {
        Secp256k1 sp = new Secp256k1();
        String string = "zhuanzhang20yuan";
        sp.ectest(string);
    }

    public void ectest(String string) {
        try {
            //初始化密钥
            //定义并初始化一个密钥生成器

            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecgp = new ECGenParameterSpec("secp256k1");
            keyPairGenerator.initialize(ecgp, new SecureRandom());
            keyPairGenerator.initialize(256);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            PublicKey keypublic = keyPair.getPublic();
            PrivateKey keyprivate = keyPair.getPrivate();

            System.out.println(keypublic);//方法未实现
            System.out.println(keyprivate);//方法未实现
//            System.out.println(Base64.encodeBase64String(keypublic.getEncoded()));
//            System.out.println(Base64.encodeBase64String(keyprivate.getEncoded()));
//            System.out.println("public_key:"+ Hex.encodeHexString(keypublic.getEncoded()));
//            System.out.println("private_key:"+ Hex.encodeHexString(keyprivate.getEncoded()));//
            // System.out.println(keypublic);

            //进行hash计算
            String copystring = string;
            byte[] resbyte = MessageDigest.getInstance("SHA-256").digest(copystring.getBytes(StandardCharsets.UTF_8));
//            String tests = Hex.encodeHexString(resbyte);
//            System.out.println(tests);
            //开始签名
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(keyprivate);
            signature.update(resbyte);
            byte[] bytes = signature.sign();
            System.out.println("Source is " + string);
            System.out.println(" Sign = " + Hex.encodeHexString(bytes));

            //调用签名方法
            if (isVerified(copystring, bytes, keypublic)) {
                System.out.println("sign has been through validation");
            } else {
                System.out.println("The sign is not validate");
            }

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }
    }

    public boolean isVerified(String copystring, byte[] bytes, PublicKey keypublic) {
        boolean vv = false;
        //验证
        try {
            Signature verify = Signature.getInstance("SHA256withECDSA");
            //开始对原文hash，哈希后的结果与用主钥验证后的结果一致说明，签名正确，数据未被修改
            //这里给验证者两个信息，原文和签名，这里主要验证签名的公钥解密后的摘要与自己计算的摘要是否一致
            byte[] degist2 = MessageDigest.getInstance("SHA-256").digest(copystring.getBytes());
            verify.initVerify(keypublic);
            verify.update(degist2);
            vv = verify.verify(bytes);
            return vv;
        } catch (NoSuchAlgorithmException | SignatureException | InvalidKeyException e) {
            e.printStackTrace();
        }
        return vv;
    }

}

