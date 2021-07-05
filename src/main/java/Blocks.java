
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.codec.binary.Hex;


import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;


/**
 * @Auther:刘兰斌
 * @Date: 2021/05/31/20:33
 * @Explain:
 */
public class Blocks {
    //区块中存放交易信息类，更具体的是区块的成员变量transaction中存放着Transaction的类
    private ArrayList<Transaction> transactions;
    private String prehash;
    private String curhash;
    private Blocks next;
    private int nonce;
    private long timestamp;

    public Blocks(ArrayList<Transaction> transaction, String prehash) {
        this.transactions = transaction;
        this.prehash = prehash;
        this.curhash = this.computerHash();
        this.next = next;
        this.nonce = 1;
        this.timestamp = System.currentTimeMillis();
    }


    //新建一个方法将Transaction[]转变为String
    public String turnoff(ArrayList<Transaction> transactionarr) {
        return JSON.toJSONString(transactionarr);
    }


    public String getPrehash() {
        return prehash;
    }

    public void setPrehash(String prehash) {
        this.prehash = prehash;
    }

    public String getCurhash() {
        return curhash;
    }

    public void setCurhash(String curhash) {
        this.curhash = curhash;
    }

    public Blocks getNext() {
        return next;
    }

    public void setNext(Blocks next) {
        this.next = next;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

    //导入阿里巴巴的Fastjson。实现json字符串与Java对象的相互转化
    public String computerHash() {
        return GetSHA256Str.getsha256str(JSON.toJSONString(this.transactions) + this.prehash + this.nonce + this.timestamp);
    }

    //获取难度值
    public String getDificult(int dificulnum) {
        String res = "";
        for (int i = 0; i < dificulnum; i++) {
            res += "0";
        }
        return res;
    }

    //开始挖矿
    public String mine(int dificulnum,DSAPublicKey dsaPublicKey,byte[] sign) throws Exception {
        //验证从交易池中取到的数据的合法性
        for (Transaction trans: this.transactions) {
            if (!trans.Verify(dsaPublicKey,sign)){//transaction,dsaPublicKey,sign)
                throw  new Exception("交易池中数据发生改变，交易发生篡改！");
            }
        }
        System.out.println("交易打包入块时一切正常！");
        while (true) {
            //           System.out.println(this.curhash);
            this.curhash = this.computerHash();
            if (!this.curhash.substring(0, dificulnum).equals(this.getDificult(dificulnum))) {
                this.nonce++;
                this.curhash = this.computerHash();
            } else {
                break;
            }
        }
        return this.curhash;

    }



    @Override
    public String toString() {
        return "Blocks{" +
                "transactions=" + transactions +
                ", prehash='" + prehash + '\'' +
                ", curhash='" + curhash + '\'' +
                ", next=" + next +
                ", nonce=" + nonce +
                ", timestamp=" + timestamp +
                '}';
    }

    public static void main(String[] args) throws Exception {

        LinkedLists llb = new LinkedLists();

//        Transaction t1 = new Transaction("addr1", "addr2", 10);
//        Transaction t2 = new Transaction("addr2", "addr1", 5);
//        llb.addTransactionToPool(t1);
//        llb.addTransactionToPool(t2);
//        System.out.println(llb);

//        llb.minetransaction("addr3");
//        System.out.println(llb);
//        //获取交易信息的测试
//        String transaction = llb.fromListToBlockToTransaction(llb.getChain());
//        System.out.println(transaction);
//        System.out.println("--------------------------");
//        Transaction t5 = new Transaction("addr5", "addr6", 333);
//        Transaction t6 = new Transaction("addr6", "addr5", 122);
//        llb.addTransactionToPool(t5);
//        llb.addTransactionToPool(t6);
//        llb.minetransaction("addr66");
//        System.out.println(llb);
        //new对象时引用
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(512);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        DSAPublicKey dsaPublicKey = (DSAPublicKey) keyPair.getPublic();
        DSAPrivateKey dsaPrivateKey = (DSAPrivateKey) keyPair.getPrivate();

        KeyPairGenerator keyPairGenerator2 = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator2.initialize(512);
        KeyPair keyPair2 = keyPairGenerator2.generateKeyPair();
        DSAPublicKey dsaPublicKey2 = (DSAPublicKey) keyPair2.getPublic();
        DSAPrivateKey dsaPrivateKey2 = (DSAPrivateKey) keyPair2.getPrivate();

        Transaction transaction1 = new Transaction(Hex.encodeHexString(dsaPublicKey.getEncoded()),Hex.encodeHexString(dsaPublicKey2.getEncoded()),10);
        System.out.println(transaction1);//dsaPublicKey
        byte[] sign = transaction1.sign(dsaPrivateKey);
        String signature = Hex.encodeHexString(sign);
        System.out.println("得到的签名是"+signature);
        transaction1.isVerified(dsaPublicKey,sign);
 //       transaction1.setAmount(12);
        llb.addTransactionToPool(transaction1,dsaPublicKey,sign);
        llb.minetransaction("addr3",dsaPublicKey,sign);
        System.out.println(llb);
//        //获取交易信息的测试
        String transaction = llb.fromListToBlockToTransaction(llb.getChain());
        System.out.println(transaction);
    }

}

class LinkedLists {
    //初始化第一个结点
    private LinkedList<Blocks> chain;
    private ArrayList<Transaction> transactionpool;//交易池
    private int mineReward;
    //引入工作量证明机制
    private int dificultnum;

    public LinkedList<Blocks> getChain() {
        return chain;
    }

    public void setChain(LinkedList<Blocks> chain) {
        this.chain = chain;
    }

    public LinkedLists() {
        this.chain = new LinkedList<Blocks>();
        //初始化第一个块上链
        chain.add(transactionToBlock());
        this.transactionpool = new ArrayList<>();
        this.mineReward = 50;
        this.dificultnum = 5;
    }

    //通过链表找到指定的块,并查看区块中的交易,以字符串接受
    public String fromListToBlockToTransaction(LinkedList<Blocks> list) {
        Blocks next = list.get(0).getNext();
        ArrayList<Transaction> transactions = next.getTransactions();//
        return JSON.toJSONString(transactions);
    }

    //创建第一笔创世交易
    public ArrayList<Transaction> firsttransaction() {
        //js的数组可以存放任意js类型,故其对象数组中可以放String,可以放对象
        ArrayList<Transaction> firsttransaction = new ArrayList<>();
        firsttransaction.add(new Transaction(null, "祖先", 50));
//        firsttransaction.add(new Transaction(null, "祖先", 50));
        return firsttransaction;
    }

    //将第一笔交易放到块中
    public Blocks transactionToBlock() {
        return new Blocks(firsttransaction(), "");
    }

    public ArrayList<Transaction> getTransactionpool() {
        return transactionpool;
    }

    public void setTransactionpool(ArrayList<Transaction> transactionpool) {
        this.transactionpool = transactionpool;
    }

    @Override
    public String toString() {
        return "LinkedLists{" +
                "chain=" + chain +
                ", transactionpool=" + transactionpool +
                ", mineReward=" + mineReward +
                ", dificultnum=" + dificultnum +
                '}';
    }

    //添加一个将交易对象的Java字符串转变为交易对象数组
//    public Transaction[] stringToArr(String transactionfrompool){
//        JSONArray array = JSON.parseArray(transactionfrompool);
////        (Transaction[])array;
//        return array;
//    }
    //矿
    public void minetransaction(String mineadress,DSAPublicKey dsaPublicKey,byte[] sign) throws Exception {
        //发放矿工奖励
        Transaction transactionreward = new Transaction("", mineadress, this.mineReward);
        this.transactionpool.add(transactionreward);
        //注意当调用 this.transactionpool.clear();方法时，下面所有的transaction均为0，问题的关键是怎么解决这个引用地址问题
        ArrayList<Transaction> copypooladress = new ArrayList<>();
        for (int i = 0; i <= transactionpool.size() - 1; i++) {
            copypooladress.add(transactionpool.get(i));
        }
        Blocks blocks = new Blocks(copypooladress, this.getLastBlock().getCurhash());//
        //blocks.mine(this.dificultnum);
        String curhash = blocks.mine(this.dificultnum,dsaPublicKey,sign);
        if (curhash != null) {
            System.out.println("挖矿结束：" + curhash);
            addNode(blocks, copypooladress, curhash);
            //添加区块到链
            //更改地址引用。为了删除交易池中数据。
            //清空交易池
            this.transactionpool.clear();
        }
    }

    //添加交易到交易池
    public void addTransactionToPool(Transaction transaction,DSAPublicKey dsaPublicKey,byte[] sign) throws Exception {
        try {
            if (!transaction.Verify(dsaPublicKey,sign)){
                throw new Exception("不合法的交易,公钥错误或者数据被篡改,交易不能添加到交易池中！");
            }
            System.out.println("合法的交易！");
            if (transaction.toString() == null) {
                System.out.println("传入的数据为空");
            } else {
                this.transactionpool.add(transaction);
            }
        } catch (Exception n) {
            n.printStackTrace();
        }

    }

    //获取链中最后一个区块
    public Blocks getLastBlock() {
        Blocks tempblock = this.chain.get(0);
        while (tempblock.getNext() != null) {
            tempblock = tempblock.getNext();
        }
        return tempblock;
    }

    //添加未知节点到区块链上中
    public void addNode(Blocks blocks, ArrayList<Transaction> coppy, String curhash) {//,ArrayList<Transaction> copypooladress
        Blocks temp = getLastBlock();
        blocks.setPrehash(temp.getCurhash());//prehash赋值
//        blocks.mine(dificultnum);//curhash赋值
        blocks.setCurhash(curhash);
        temp.setNext(blocks);
        blocks.setTransactions(coppy);
        blocks.setNext(null);
    }

    //    验证数据合法性
    //验证数据是否合法
    //验证hash是否正确
    public boolean isValidate(DSAPublicKey dsaPublicKey,byte[] sign) throws Exception {
        //如果为初始化链，肯定合法

        if (chain.size() == 1) {//chain.size() == 1
            //    if(chain.get(0).getOwnhash() != chain.get(0).computerhash()){
            if (!chain.get(0).getCurhash().equals(chain.get(0).computerHash())) {
                //!chain.get(0).getOwnhash().equals(chain.get(0).computerhash())
                return false;
            }
            return true;
        }
        //验证完第一个数据后，开始验证后面的数据
        while (chain.get(0).getNext() != null) {
            //看区块是否有非法交易



            ArrayList<Transaction> transactions = chain.get(0).getNext().getTransactions();
            for (Transaction trans: transactions) {
                if (!trans.Verify(dsaPublicKey,sign)){//transaction,dsaPublicKey,sign)
                    throw  new Exception("交易池中数据发生改变，交易发生篡改！");
                }
            }
            Blocks nextblocks = chain.get(0).getNext();
            if (!nextblocks.getCurhash().equals(nextblocks.computerHash())) {
                System.out.println("数据发生篡改！");
                return false;
            }

            //验证此区块的的前哈希值是否等于前一个区块的哈希值
            Blocks preblocks = chain.get(0);
            if (!nextblocks.getPrehash().equals(preblocks.getCurhash())) {
                System.out.println("失败，前后数据块断裂！");
                return false;
            }
            nextblocks = nextblocks.getNext();
            preblocks = preblocks.getNext();
        }
        return true;
    }

}

class Transaction {
    private String sender;
    private String receiver;
    private int amount;

    // 时间戳应该是在区块产生时产生，不是产生交易的时间点
    //添加数字签名到区块链
    public String computerHash() {
        return GetSHA256Str.getsha256str(this.sender + this.receiver + this.amount);
    }

    //签名
    public byte[] sign(DSAPrivateKey privatekeyPair) {
        // String signature = DSA.dsatest(this.sender + this.receiver + this.amount);
      //  String res = "";
        byte[] result=null;
        try {

            //2.执行签名 dsaPrivateKey
            PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(privatekeyPair.getEncoded());
            KeyFactory keyFactory = KeyFactory.getInstance("DSA");
            PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
            Signature signature = Signature.getInstance("SHA1withDSA");
            //    byte[] digest = MessageDigest.getInstance("SHA-256").digest(data.getBytes(StandardCharsets.UTF_8));
            signature.initSign(privateKey);
            signature.update(computerHash().getBytes(StandardCharsets.UTF_8));
            result = signature.sign();
           // res = Hex.encodeHexString(result);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        return result;
    }

    //验证
    public void isVerified(DSAPublicKey dsapublickey, byte[] signature) throws NoSuchAlgorithmException {
        if(this.sender ==""){
            return;
        }
        DSA.isVerfy(computerHash(),signature,dsapublickey);
    }
    //获取上面验证的一个bool值、实际上是对验证方法的一种封装
    public boolean Verify(DSAPublicKey dsapublickey, byte[] signature){
        if(this.sender ==""){
            return true;
        }
        boolean verfy2 = DSA.isVerfy2(computerHash(),signature,dsapublickey);
        return verfy2;
    }


    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public Transaction(String sender, String receiver, int amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "sender='" + sender + '\'' +
                ", receiver='" + receiver + '\'' +
                ", amount=" + amount +
                '}';
    }
}