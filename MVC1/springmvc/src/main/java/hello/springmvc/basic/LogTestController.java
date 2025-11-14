package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j  // 해당 애노테이션을 넣으면 `Logger log = LoggerFactory.getLogger(getClass());` 부분 생략하고, 바로 log 사용 가능
/*
    @RestController
    - @Controller는 반환값이 String이면 뷰 이름으로 인식된다. 그래서 뷰를 찾고 뷰가 랜더링된다.
    - @RestController는 반환값으로 뷰를 찾는 것이 아니라, HTTP 메시지 바디에 바로 입력한다.
      따라서 실행 결과로 ok 메세지를 받을 수 있다. → 뒤에서 자세히 설명할 예정
 */
@RestController
public class LogTestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        /*
            - 로그에 출력되는 포맷 : 시간, 로그레벨, 프로세스 ID, 쓰레드명, 클래스명, 로그 메시지
            - 로그 레벨 설정 : TRACE > DEBUG > INFO > WARN > ERROR
            - 개발 서버는 DEBUG 레벨까지, 운영 서버는 INFO 레벨까지 출력되도록 설정할 수 있음

            올바른 로그 사용법
            - `log.debug("data = " + data) : 로그 출력 레벨에 해당하지 않아도 문자 더하기 연산이 먼저 일어나기 때문에 리소스가 낭비된다.
            - `log.debug("data = {}", data) : 로그 출력 레벨에 해당하지 않으면 아무일도 발생하지 않는다. 따라서 의미없는 연산이 발생하지 않는다.

            로그 사용시 장점
            - 쓰레드 정보, 클래스 이름 같은 부가 정보를 함께 볼 수 있고, 출력 모양을 조정할 수 있다.
            - 로그 레벨에 따라 개발 서버에서는 모드 로그를 출력하고, 운영서버에서는 출력하지 않는 등 로그를 상황에 맞게 조절할 수 있다.
            - 시스템 아웃 콘솔에만 출력하는 것이 아니라, 파일이나 네트워크 등 로그를 별도의 위치에 남길 수 있다.
              특히 파일로 남길 때는 일별, 특정 용량에 따라 로그를 분할하는 것도 가능하다.
            - 성능도 일반 System.out보다 좋다. (내부 버퍼링, 멀티 쓰레드 등) 그래서 실무에서는 꼭 로그를 사용해야 한다.
         */
        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }

}
