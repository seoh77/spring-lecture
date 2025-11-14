package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@Slf4j
@Controller
//@ResponseBody // @ResponseBody를 클래스 레벨에 두면 모든 메서드에 @ResponseBody가 적용된다.

/*
    @RestController는 @Controller와 @ResponseBody 애노테이션을 둘 다 달아주는 것과 같은 역할을 한다.
    따라서 뷰 템플릿을 사용하는 것이 아니라, HTTP 메시지 바디에 직접 데이터를 입력한다.
    이름 그대로 Rest API(HTTP API)를 만들 때 사용하는 컨트롤러이다.
 */
//@RestController
public class ResponseBodyController {

    @GetMapping("/response-body-staring-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        // HttpServletResponse 객체를 통해서 HTTP 메시지 바디에 직접 ok 응답 메시지를 전달
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-staring-v2")
    public ResponseEntity<String> responseBodyV2() {
        // ResponseEntity 엔티티는 HttpEntity를 상속 받았는데, HttpEntity는 HTTP 메시지의 헤더, 바디 정보를 가지고 있다.
        // ResponseEntity는 여기에 더해서 HTTP 응답 코드를 설정할 수 있다.
        return new ResponseEntity<>("ok", HttpStatus.OK);
    }

    @ResponseBody   // @ResponseBody를 사용하면 view를 사용하지 않고, HTTP 메시지 컨버터를 통해서 HTTP 메시지를 직접 입력할 수 있다.
    @GetMapping("/response-body-staring-v3")
    public String responseBodyV3() {
        return "ok";
    }

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        // ResponseEntity를 반환한다. HTTP 메시지 컨버터를 통해서 JSON 형식으로 변환되어서 반환된다.
        return new ResponseEntity<>(helloData, HttpStatus.OK);
    }

    // ResponseEntity는 HTTP 응답 코드를 설정할 수 있는데, @ResponseBody를 사용하면 이런 것을 설정하기 까다롭다.
    // 이때, @ResponseStatus 애노테이션을 사용하면 응답 코드도 설정할 수 있다.
    // 단, 애노테이션이기 때문에 응답 코드를 동적으로 변경할 수 없다. 프로그램 조건에 따라 동적으로 변경하려면 ResponseEntity를 사용!
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2() {
        HelloData helloData = new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(20);

        return helloData;
    }
}
