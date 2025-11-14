package hello.springmvc.basic;

import lombok.Data;

@Data   // lombok에 @Data를 사용하면 @Getter, @Setter, @ToString, @@EqualsAndHashCode, @RequiredArgsConstructor를 자동으로 적용
public class HelloData {
    private String username;
    private int age;
}
