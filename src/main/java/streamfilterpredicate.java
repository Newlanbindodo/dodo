import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class streamfilterpredicate {
    public static void main(String[] args) {
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
//       1、 List<Employee> res = employees.stream()
//                .filter(e->e.getGender().equals("M") && e.getAge()>70)
//                .collect(Collectors.toList());

//      2、  List<Employee> res = employees.stream()
//                .filter(Employee.ageOver70.and(Employee.genderIsM))
//                .collect(Collectors.toList());
//        System.out.println(res);

        List<Employee> res = employees.stream()
                .peek(e -> {//peek是一种特殊的map函数，与map不同，要直接return,实际上是引用数据类型
                    e.setAge(e.getAge() + 1);
                    e.setGender(e.getGender().equals("M") ? "Male" : "female");
                })
                .collect(Collectors.toList());
        System.out.println(res);
    }
}
