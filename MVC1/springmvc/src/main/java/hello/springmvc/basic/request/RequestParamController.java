package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

/*
    HttpServletRequest의 `request.getParameter()`를 사용하면 다음 두 가지 요청 파라미터를 조회할 수 있다.
    - GET, 쿼리 파라미터 전송
    - POST, HTML Form 전송

    GET 쿼리 파라미터 전송 방식이든, POST HTML Form 전송 방식이든 둘 다 형식이 같으므로 구분없이 조회할 수 있다.
    → 이것을 간단히 요청 파라미터(request parameter) 조회라고 한다.
 */

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    // 반환 타입이 없으면서 이렇게 응답에 값을 직접 집어넣으면, view 조회X
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody       // View 조회를 무시하고, HTTP message body에 직접 해당 내용 입력
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,    // @RequestParam : 파라미터 이름으로 바인딩
            @RequestParam("age") int memberAge) {

        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,  // HTTP 파라미터 이름이 변수 이름과 같으면 @RequestParam(name="xx") 생략 가능
            @RequestParam int age) {

        log.info("username={}, age={}", username, age);
        return "ok";
    }

    /*
        String, int, Integer 등의 단순 타입이면 @RequestParam도 생략 가능
        - @RequestParam 애노테이션을 생략하면 스프링 MVC는 내부에서 `required=false`를 적용한다.
        - 이렇게 애노테이션을 완전히 생략해도 되는데, 너무 없는 건 약간 과하다는 입장도 있음.
          @RequestParam이 있으면 명확하게 요청 파라미터에서 데이터를 읽는다는 것을 알 수 있다.
     */
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) {    // String, int 등의 단순 타입이면 @RequestParam도 생략 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(
            // @RequestParam.required : 파라미터 필수 여부, 기본값이 파라미터 필수(true)이다.
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {     // defaultValue는 빈 문자의 경우에도 설정한 기본 값이 적용
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) { // 파라미터의 값이 1개가 확실하다면 Map을 사용해도 되지만, 그렇지 않다면 MultiValueMap 사용
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    /*
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }
     */

    // 위의 주석 코드와 같은 기능 수행
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        /*
            스프링MVC는 @ModelAttribute가 있으면 다음을 실행
            - HelloData 객체를 생성
            - 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다. 그리고 해당 프로퍼티의 setter를 호출해서 파라미터의 값을 입력(바인딩)한다.
              예) 파라미터 이름이 username이면 setUsername() 메서드를 찾아서 호출하면서 값을 입력한다.
         */

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData={}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {   // @ModelAttribute 생략 가능
        /*
            @ModelAttribute도 생략 가능, @RequestParam도 생략 가능
            → 스프링은 생략시 다음과 같은 규칙을 적용한다.
              - String, int, Integer 같은 단순 타입 = @RequestParam
              - 나머지 = @ModelAttribute (argument resolver로 지정해둔 타입은 예외)
         */
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
