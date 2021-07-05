import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.function.Predicate;

@Data
@AllArgsConstructor
public class Employee {
    private Integer id;
    private Integer age;
    private String gender;
    private String firstname;
    private String lastname;

    //写上谓词逻辑，为了复用
     public static final Predicate<Employee> ageOver70 = a -> a.getAge()>70;
     public static final Predicate<Employee> genderIsM = g -> g.getGender().equals("M");
}
