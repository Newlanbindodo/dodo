import java.util.Arrays;
import java.util.List;
import java.util.Optional;


/**
 * @Auther:刘兰斌
 * @Date: 2021/05/30/13:34
 * @Explain:

 *
 */
public class SortTest {
    public static void main(String[] args) {
        //集合元素规约reduce
        List<Integer> integers = Arrays.asList(24, 8, 35, 23, 11, 0, 6, 3);
        Integer res = integers.stream().reduce(0, (already, curr) ->  already + curr );
        //等价于
        Integer res2 = integers.stream().reduce(0, Integer::sum);
        System.out.println(res+" "+res2);
        List<String> strings = Arrays.asList("a", "sd", "b", "sb");
        String res3 = strings.stream().reduce("", (al, cur) -> al.concat(cur));
        System.out.println(res3);


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
        Integer res5 = employees
                .parallelStream()//数据多时可以用并行流
                .map(s -> s.getAge())
                .reduce(0, Integer::sum);
        System.out.println(res5);
        //上面写法与下面等价
        Integer res6 = employees.parallelStream()
                .reduce(0, (subtotal, cur) -> subtotal + cur.getAge(), Integer::sum);
        System.out.println(res6);


    }
}
