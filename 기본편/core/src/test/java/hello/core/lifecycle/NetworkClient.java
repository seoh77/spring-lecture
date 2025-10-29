package hello.core.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

/*
    초기화, 소멸 인터페이스 사용
    - `InitializingBean`은 `afterPropertiesSet()` 메서드로 초기화를 지원한다.
    - `DisposableBean`은 `destroy()` 메서드로 소멸을 지원한다.

    초기화, 소멸 인터페이스 단점
    - 이 인터페이스는 스프링 전용 인터페이스로, 해당 코드가 스프링 전용 인터페이스에 의존한다.
    - 초기화, 소멸 메서드의 이름을 변경할 수 없다.
    - 내가 코드를 고칠 수 없는 외부 라이브러리에 적용할 수 없다.

    => 인터페이스를 사용하는 초기화, 종료 방법은 스프링 초창기에 나온 방법이고, 현재는 거의 사용되지 않는다.
 */
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
        connect();
        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    // 서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    /*
        @PostConstruct, @PreDestroy 애노테이션 특징
        - 최신 스프링에서 가장 권장하는 방법
        - 스프링에 종속적인 기술이 아니라 자바 표준이므로, 스프링이 아닌 다른 컨테이너에서도 동작한다.
        - 컴포넌트 스캔과 잘 어울린다.
        - 하지만 외부 라이브러리에는 적용하지 못한다.
          -> 외부 라이브러리를 초기화, 종료 해야 하면 @Bean의 기능을 사용
     */

    @PostConstruct
    public void init() {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}