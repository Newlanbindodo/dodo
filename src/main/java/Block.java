import java.util.ArrayList;


/**
 * @Auther:刘兰斌
 * @Date: 2021/05/31/9:30
 * @Explain:
 */
public class Block {
    private String data;
    private String prehash;
    private String ownhash;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getPrehash() {
        return prehash;
    }

    public void setPrehash(String prehash) {
        this.prehash = prehash;
    }

    public String getOwnhash() {
        return ownhash;
    }

    public void setOwnhash(String ownhash) {
        this.ownhash = ownhash;
    }

    public Block(String data, String prehash, String ownhash) {
        this.data = data;
        this.prehash = prehash;
        this.ownhash = computerhash();
    }


    public String computerhash() {
        return GetSHA256Str.getsha256str(this.data + this.prehash);
    }

    @Override
    public String toString() {
        return "Block{" +
                "data='" + data + '\'' +
                ", prehash='" + prehash + '\'' +
                ", ownhash='" + ownhash + '\'' +
                '}';
    }


    public static void main(String[] args) {
        Chain chain = new Chain();
        Block Block = new Block("转账十元", "123", "");
        Block block2 = new Block("转账5元", "", "");
        chain.addBlocktochain(Block);
        chain.addBlocktochain(block2);
        System.out.println(chain);
        //尝试篡改数据
        chain.chain.get(1).data = "转账1000";
        //尝试篡改前后的hash值
        chain.chain.get(1).setOwnhash(chain.chain.get(1).computerhash());
        boolean res = chain.isValidate();
        System.out.println(res);
    }
}

class Chain {
//    Block generater = new Block("祖先节点", "", "");//祖先区块
    ArrayList<Block> chain = new ArrayList<>();

    public Chain() {
        chain.add(new Block("祖先节点", "", ""));
    }

    @Override
    public String toString() {
        return "Chain{" +
                "generater=" + new Block("祖先节点", "", "") +
                ", chain=" + chain +
                '}';
    }

    //添加一个区块到链上
    public ArrayList<Block> addBlocktochain(Block block) {
        //找到上一个区块的hash
        String lasthash = chain.get(chain.size() - 1).getOwnhash();
        block.setPrehash(lasthash);
        block.setOwnhash(block.computerhash());
        chain.add(block);
        return chain;
    }

    //验证数据是否合法
    //验证hash是否正确
    public boolean isValidate() {
        //如果为初始化链，肯定合法
        if (chain.size() == 1) {
            //    if(chain.get(0).getOwnhash() != chain.get(0).computerhash()){
            if (!chain.get(0).getOwnhash().equals(chain.get(0).computerhash())) {
                return false;
            }
            return true;
        }
        //验证完第一个数据后，开始验证后面的数据
        for (int i = 1; i < chain.size(); i++) {
            Block block = chain.get(i);

            if (!block.getOwnhash().equals(block.computerhash())) {
                System.out.println("数据发生篡改！");
                return false;
            }

            //验证此区块的的前哈希值是否等于前一个区块的哈希值
            Block preblock = chain.get(i - 1);
            if (!block.getPrehash().equals(preblock.getOwnhash())) {
                System.out.println("失败，前后数据块断裂！");
                return false;
            }
        }
        return true;
    }

}
