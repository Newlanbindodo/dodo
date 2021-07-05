/**
 * @Auther:刘兰斌
 * @Date: 2021/06/01/10:16
 * @Explain:
 */
public class ProofOfWork {
    public static void main(String[] args) {
        pow();
    }

    public static void pow() {
        String a = "luotuo";
        int c = 1;
        while (c<10) {
            if(GetSHA256Str.getsha256str(a+c).substring(0,2).equals("00")){
                System.out.println(GetSHA256Str.getsha256str(a+c));
                System.out.println("共执行了"+c+"次");
                break;
            }else{
                c++;
            }
        }
        System.out.println("程序结束了。。。");
    }
}
