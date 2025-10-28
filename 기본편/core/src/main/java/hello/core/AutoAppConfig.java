package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(     // `@ComponentScan`은 `@Component`가 붙은 모든 클래스를 스프링 빈으로 등록
        /*
            컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 등록되기 때문에,
            AppConfig, TestConfig 등 앞서 만들어두었던 설정 정보도 함께 등록되고, 실행되어 버린다.
            따라서 excludeFilters를 이용해서 설정정보는 컴포넌트 스캔 대상에서 제외한다.
         */
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
