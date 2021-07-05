import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Auther:刘兰斌
 * @Date: 2021/05/30/12:01
 * @Explain:为了方便描述，将对象与测试方法写在一起
 * 需求：找出年龄超过60的女性职工
 */
public class FilterTest {
    public static void main(String[] args) {
        Staff staff01 = new Staff(1,23,"F","Rick","Beethoven");
        Staff staff02 = new Staff(2,13,"M","Rock","ven");
        Staff staff03 = new Staff(3,73,"M","Re","oven");
        Staff staff04 = new Staff(4,53,"F","james","Bee");
        Staff staff05 = new Staff(5,63,"M","Raty","hove");
        Staff staff06 = new Staff(6,33,"M","dtt","love");
        Staff staff07 = new Staff(7,83,"F","yu","ko");
        Staff staff08 = new Staff(8,103,"M","tu","so");
        Staff staff09 = new Staff(9,113,"F","po","bye");
        Staff staff10 = new Staff(10,123,"M","jo","all");
        List<Staff> staffs = Arrays.asList(staff01, staff02, staff03, staff04, staff05, staff06, staff07, staff08, staff09, staff10);
        List<Staff> res = staffs.stream()
                .filter(Staff.ageOver60.and(Staff.genderIsF))//这里.and代表并，表达或用.or，表达否定用negate()
                .collect(Collectors.toList());
        System.out.println(res);
    }
}
@Data
@AllArgsConstructor
class Staff{
    private int id;
    private int age;
    private String gender;
    private String firstname;
    private String lastname;
    //声明谓语，方便复用
    public static Predicate<Staff> ageOver60 = a -> a.getAge()>60;
    public static Predicate<Staff> genderIsF = g -> g.getGender().equals("F");
}