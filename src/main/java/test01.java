import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @Auther:刘兰斌
 * @Date: 2021/05/30/10:30
 * @Explain:
 */
public class test01 {
    public static void main(String[] args) {
        //文本转成流操作
        try {
            Stream<String> stream6 = Files.lines(Paths.get("src/main/resources/test116.txt"));
            List<String> res = stream6.filter(s -> s.equals("Haha"))
                    .map(String::toUpperCase)
                    .collect(Collectors.toList());
            System.out.println(res);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
