package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

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
}
