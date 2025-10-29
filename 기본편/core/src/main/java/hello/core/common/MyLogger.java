package hello.core.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

/*
    `proxyMode = ScopedProxyMode.TARGET_CLASS`
      - 적용대상이 인터페이스가 아닌 클래스면 `TARGET_CLASS` 선택
      - 적용대상이 인터페이스면 `INTERFACES` 선택

     => 이렇게 하면 MyLogger의 가짜 프록시 클래스를 만들어두고 HTTP request와 상관없이 가짜 프록시 클래스를 다른 빈에 미리 주입해 둘 수 있다.
 */

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)   // 해당 빈은 HTTP 요청당 하나씩 생성되고, HTTP 요청이 끝나는 시점에 소멸
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "] " + message);
    }

    // 빈이 생성되는 시점에 자동으로 `@PostConstruct` 초기화 메서드를 사용해서 uuid를 생성해 저장한다.
    // 이 빈은 HTTP 요청당 하나씩 생성되므로, uuid를 저장해두면 다른 HTTP 요청과 구분할 수 있다.
    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create: " + this);
    }

    @PreDestroy
    public void close() {
        System.out.println("[" + uuid + "] request scope bean close: " + this);
    }
}
