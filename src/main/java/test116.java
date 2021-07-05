/**
 * @Auther:刘兰斌
 * @Date: 2021/05/30/10:40
 * @Explain:
 */
public class test116 {
    //打卡
    interface Sign{
        void sign(String people);
    }
    interface Jh{
        void jh(String name,int age);
    }
    public void info(String name,int age,Jh jj){
        jj.jh(name,age);
    }
    //员工打卡
    public void signway(String people,Sign si){
        si.sign(people);
    }

    public static void main(String[] args) {
        test116  tes6 = new test116();
        String people ="小刚";
        String name="小花";
        int age =12;
        //法一：用lambda表达式
//        tes6.signway(people,p -> System.out.println(p));
        tes6.info(name,age,(p,q)->
            System.out.println(p+"的年龄是"+q)
        );
        //法二：用匿名内部类
//        Sign s = new Sign() {
//            @Override
//            public void sign(String people) {
//                System.out.println(people);
//            }
//        };
//        tes6.signway(people,s);
    }
}
