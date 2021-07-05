import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther:刘兰斌
 * @Date: 2021/05/30/11:01
 * @Explain:
 */
public class StreamTest {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Tommi", "Davy", "Philipp", "Henry", "Roy", "Jack", "Jerry");
        List<String> res = names.stream()
                .filter(e -> e.startsWith("J"))
                .map(String::toLowerCase)
                .sorted()
                .collect(Collectors.toList());
        System.out.println(res);
//        String[] str = {"Tommi", "Davy", "Philipp", "Henry", "Roy", "Jack", "Jerry"};
//        Stream.of(str)
//        .mapToInt(String::length)
//        .forEach(System.out::println);
    }
}
