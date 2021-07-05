import java.security.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
/**
 * @Auther:刘兰斌
 * @Date: 2021/05/31/19:39
 * @Explain:
 */

/**

 Sun EC public key, 256 bits
 public x coord: 267302852406221524818479867699951486467774126478019875262733933586326954280
 public y coord: 115497573847670246919172672509067690017618632142134932470562270937954517919126
 parameters: secp256r1 [NIST P-256, X9.62 prime256v1] (1.2.840.10045.3.1.7)
 sun.security.ec.ECPrivateKeyImpl@1257


 Sun EC public key, 256 bits
 public x coord: 94342578938257275358722655784838201993977932417067680352703428577474354217778
 public y coord: 32786269921953638925724429696201747357278019469367688820017338146371949305748
 parameters: secp256r1 [NIST P-256, X9.62 prime256v1] (1.2.840.10045.3.1.7)
 sun.security.ec.ECPrivateKeyImpl@2ba0
 */
public class Test {
    public static void main(String[] args) {
        int[] arr =  new int[]{1,2,2,1,1,3};
        uniqueOccurrences(arr);
//        ECC();
//    Transaction1 ttt1 = new Transaction1();
//    Transaction1 t11 = new Transaction1("adr1","adr2",10);
//        Transaction1 t12 = new Transaction1("adr1","adr2",5);
////        System.out.println(s);
//        ArrayList<Transaction1> arr = new ArrayList<>();
//        arr.add(t11);
//        arr.add(t12);
//        System.out.println(arr.toString());
//
//        System.out.println(JSON.toJSONString(arr));

//        String json = JSON.toJSONString(arr);
        //js弱类型语言，比如其数组中可以添加任意类型的数据
//        Object o = JSONObject.toJSON(t11);//Java对象转json对象
//        JSONObject.
//        ttt1 =(Transaction1)JSONObject.toJavaObject(o,Transaction1.class);//toBean(obj,Transaction.class)//.toJavaObject(object,Transaction.class)
//        System.err.println(ttt1.getSender());
//        Object parse = JSON.parse(JSON.toJSONString(arr));
//        System.out.println(parse);
    }
    // int[] arr =  new int[]{1,2,2,1,1,3};
    public  static boolean uniqueOccurrences(int[] arr) {
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        int left = 0, right = 1;
        while (right < arr.length) {
            if (arr[left] != arr[right]) {
                if (!set.add(right - left)) {//不能添加进入必然有重复
                    return false;
                }
                left = right;
                if (right == arr.length - 1) {
                    if (!set.add(1)) {
                        return false;
                    }
                }
            }
            right++;
        }
        return true;
    }
    public static void ECC() {
        //Create a key pair
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            keyGen.initialize(256);
            KeyPair keypair = keyGen.genKeyPair();
            PrivateKey privateKey = keypair.getPrivate();
            PublicKey publicKey = keypair.getPublic();
            System.out.println("-------------------------");
            System.out.println(privateKey + "\n" + publicKey);
            System.out.println("-------------------------");

            String input = "abc";
            //这里选用上面输出的一个签名算法
            Signature signer = Signature.getInstance("SHA256withECDSA");
            //用生成的私钥初始化签名
            signer.initSign(privateKey);
            signer.update(input.getBytes());
            byte[] sign = signer.sign();
            //得到签名，现在签名可以发给第三方了
            System.out.println("\nSource is " + input);
            System.out.println(" Sign = " + Arrays.toString(sign));

            //现在验证。第三方程序内部保存publicKey。现在拿到了签名和数据。
            Signature verifier = Signature.getInstance("SHA256withECDSA");
            verifier.initVerify(publicKey);
            //第三方的数据abc，用签名来验证是否和数据一致
            String c = "abc";
            verifier.update(c.getBytes());
            boolean b = verifier.verify(sign);
            System.out.println(" Sign result : " + b);  //输出结果

        } catch (Exception exception) {
            exception.printStackTrace();
        }

    }

    class Transaction1 {
        private String sender;
        private String receiver;
        private int amount;
// 时间戳应该是在区块产生时产生，不是产生交易的时间点

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


        public Transaction1() {
        }

        public Transaction1(String sender, String receiver, int amount) {
            this.sender = sender;
            this.receiver = receiver;
            this.amount = amount;
        }

        public String computerHash() {
            return GetSHA256Str.getsha256str(this.sender + this.receiver + this.amount);
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
}
//        String a = "12243546";
//        int c = 1;
//        String s = a + c;
//        c = c + 1;
//        String ss = a + c;
//        //    String ss = a.substring(0, 2);
//        System.out.println(ss);
//    }
//
//    public int sumOfUnique(int[] nums) {
//        Stream<int[]> numstr = Stream.of(nums);
//        int[] ints = numstr.mapToInt(s -> {
//            int ans = 0;
//            int[] arr = new int[101];
//            for (int i = 0; i < nums.length; i++) arr[nums[i]]++;
//            for (int i = 0; i < arr.length; i++) {
//                if (arr[i] == 1) {
//                    ans += i;
//                }
//            }
//            return ans;
//        }).toArray();
//        return ints[0];
//    }
/**
 * 中序（迭代）
 * public List<Integer> inorderTraversal(TreeNode root) {
 *         List<Integer> list = new ArrayList<Integer>();
 *         Deque<TreeNode> stk = new LinkedList<TreeNode>();
 *         while(root != null || !stk.isEmpty()){
 *             while(root != null){
 *                 stk.push(root);
 *                 root = root.left;
 *             }
 *             root=stk.pop();
 *             list.add(root.val);
 *             root = root.right;
 *         }
 *         return list;
 *     }
 *中序(递归)
 * class Solution {
 *     public List<Integer> inorderTraversal(TreeNode root) {
 *         List<Integer> res = new ArrayList<Integer>();
 *         inorder(root, res);
 *         return res;
 *     }
 *
 *     public void inorder(TreeNode root, List<Integer> res) {
 *         if (root == null) {
 *             return;
 *         }
 *         inorder(root.left, res);
 *         res.add(root.val);
 *         inorder(root.right, res);
 *     }
 * }
 *
 *
 * 后序（迭代）
 * public List<Integer> postorderTraversal(TreeNode root) {
 *         List<Integer> res = new ArrayList<Integer>();
 *         if (root == null) {
 *             return res;
 *         }
 *
 *         Deque<TreeNode> stack = new LinkedList<TreeNode>();
 *         TreeNode prev = null;
 *         while (root != null || !stack.isEmpty()) {
 *             while (root != null) {
 *                 stack.push(root);
 *                 root = root.left;
 *             }
 *             root = stack.pop();
 *             if (root.right == null || root.right == prev) {
 *                 res.add(root.val);
 *                 prev = root;
 *                 root = null;
 *             } else {
 *                 stack.push(root);
 *                 root = root.right;
 *             }
 *         }
 *         return res;
 *     }
 *
 *后序(递归)
 * class Solution {
 *     public List<Integer> postorderTraversal(TreeNode root) {
 *         List<Integer> res = new ArrayList<Integer>();
 *         postorder(root, res);
 *         return res;
 *     }
 *
 *     public void postorder(TreeNode root, List<Integer> res) {
 *         if (root == null) {
 *             return;
 *         }
 *         postorder(root.left, res);
 *         postorder(root.right, res);
 *         res.add(root.val);
 *     }
 * }
 */
//       System.out.println(llb.getChain().get(1).getTransactions());
//        LinkedLists sl = new LinkedLists();
//        sl.addTransactionToPool(t1);
//        sl.addTransactionToPool(t2);
//        System.out.println(sl);

//        Transaction t1 = new Transaction("1","1",1,System.currentTimeMillis());
//        Transaction t2 = new Transaction("1","1",1,System.currentTimeMillis());
//        System.out.println(String.join(",",(t1.toString()+t2.toString())));
//        Blocks block1 = new Blocks("转账十元","其实我是十元","",null);//这里的前一个区块的hash值实际上是模拟
//        Blocks block2 = new Blocks("转账二十元","其实我是二十元","",null);//这里简化只是用来计算curhash
//        LinkedLists LinkedLists = new LinkedLists();
//        LinkedLists.addNode(block1);
//        LinkedLists.addNode(block2);
//        System.out.println(LinkedLists);

//        LinkedLists.getchain().getNext().setData("转账1000元！");
//        LinkedLists.getchain().getNext().setCurhash(
//                LinkedLists.getchain().getNext().computerHash());
//


//        boolean validate = LinkedLists.isValidate();//data = "转账1000";
//        System.out.println(validate);
