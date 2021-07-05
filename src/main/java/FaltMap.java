import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/*
 * @Auther:刘兰斌
 * @Date: 2021/05/29/16:20
 * @Explain:
 */
public class FaltMap {
    public static void main(String[] args) {
//        List<String> list1 = Arrays.asList("hello", "FlatMap");
//        list1.stream()
//                .flatMap(e -> Arrays.stream(e.split("")))//根据“”来截取，同理若split(",")则按照，截取，结果为一行一个单词
//                .forEach(System.out::println);
        Employee e1 = new Employee(1,23,"F","Rick","Beethoven");
        Employee e2 = new Employee(2,13,"M","Rock","ven");
        Employee e3 = new Employee(3,73,"M","Re","oven");
        Employee e4 = new Employee(4,53,"F","james","Bee");
        Employee e5 = new Employee(5,63,"M","Raty","hove");
        Employee e6 = new Employee(6,33,"M","dtt","love");
        Employee e7 = new Employee(7,83,"F","yu","ko");
        Employee e8 = new Employee(8,103,"M","tu","so");
        Employee e9 = new Employee(9,113,"F","po","bye");
        Employee e10 = new Employee(10,123,"M","jo","all");
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
        //需求：按性别倒序输出，再按年龄倒序输出
//        employees.sort(Comparator.comparing(Employee::getGender)
//                .thenComparing(Employee::getAge)
//                .reversed()
//        );
//        //性别、年龄均倒序，最后加reversed
//        //性别正序，年龄倒序，在每局后面都加reversed
//        //先是倒序，然后正序，只在第一句加reversed
//        //都是正序，均不加
//        employees.forEach(System.out::println);
//        employees.sort(new Comparator<Employee>() {
//            @Override
//            public int compare(Employee o1, Employee o2) {
//                if (o1.getAge() == o2.getAge()){
//                   return 0;
//                }
//    //            return o1.getAge()-o2.getAge()<=0?1:-1;//输出倒序
//                return o2.getAge()-o1.getAge();//-1判读条件真假  o1.getAge()-o2.getAge()>0?-1:1
//            }
//        });
        employees.sort((o1,o2) ->{
                if (o1.getAge() == o2.getAge()){
                    return 0;
                }
                //            return o1.getAge()-o2.getAge()<=0?1:-1;//输出倒序
                return o2.getAge()-o1.getAge();//-1判读条件真假  o1.getAge()-o2.getAge()>0?-1:1
            });
        employees.forEach(System.out::println);
    }
}
