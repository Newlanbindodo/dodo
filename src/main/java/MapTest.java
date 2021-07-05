import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * @Auther:刘兰斌
 * @Date: 2021/05/30/12:41
 * @Explain:
 * 需求：
 *      将所有员工的年龄加1，并且性别改为全称，如F->female;M->male
 */
public class MapTest {
    public static void main(String[] args) {
//        Staffn staff01 = new Staffn(1,23,"F","Rick","Beethoven");
//        Staffn staff02 = new Staffn(2,13,"M","Rock","ven");
//        Staffn staff03 = new Staffn(3,73,"M","Re","oven");
//        Staffn staff04 = new Staffn(4,53,"F","james","Bee");
//        Staffn staff05 = new Staffn(5,63,"M","Raty","hove");
//        Staffn staff06 = new Staffn(6,33,"M","dtt","love");
//        Staffn staff07 = new Staffn(7,83,"F","yu","ko");
//        Staffn staff08 = new Staffn(8,103,"M","tu","so");
//        Staffn staff09 = new Staffn(9,113,"F","po","bye");
//        Staffn staff10 = new Staffn(10,123,"M","jo","all");
//        List<Staffn> staffs = Arrays.asList(staff01, staff02, staff03, staff04, staff05, staff06, staff07, staff08, staff09, staff10);
//        List<Staffn> res = staffs.stream()
//                .map(s -> {
//                    s.setAge(s.getAge() + 1);
//                    s.setGender(s.getGender().equals("M") ? "male" : "female");
//                    return s;
//                })
//                .collect(Collectors.toList());
//        System.out.println(res);
//
//    }
//        Stream.of("hello", "flatmap")
//                .flatMap(fm -> Arrays.stream(fm.split("")))
//                .forEach(System.out::println);
        List<String> res = Stream.of("Tommi", "Davy", "Philipp", "Henry", "Roy", "Jack", "Jerry")
                .limit(2)//.forEach(System.out::println)
                .collect(Collectors.toList());
        System.out.println(res);
        List<String> res2 = Stream.of("Tommi", "Davy", "Philipp", "Henry", "Roy", "Jack", "Jerry")
                .skip(2)
                .collect(Collectors.toList());
        System.out.println(res2);
        List<String> res3 = Stream.of("Tommi", "Davy", "Philipp", "Henry", "Roy", "Jack", "Jerry")
                .sorted()
                .collect(Collectors.toList());
        System.out.println(res3);
        List<String> res4 = Stream.of("Tommi", "Davy", "Philipp", "Henry", "Roy", "Jack", "Jerry", "Tommi", "Davy", "Philipp", "Henry")
                .distinct()
                .collect(Collectors.toList());
        System.out.println(res4);

//        List<String> strings = Arrays.asList("hello", "flatmap");
//
//        List<String[]> res = strings.stream()
//                .map(m -> m.split(""))
//                .collect(Collectors.toList());
//        System.out.println(res);


    }

    @Data
    @AllArgsConstructor
    class Staffn {
        private int id;
        private int age;
        private String gender;
        private String firstname;
        private String lastname;
    }
}

