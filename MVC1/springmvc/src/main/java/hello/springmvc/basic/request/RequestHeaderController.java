package hello.springmvc.basic.request;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,    // HTTP 메서드를 조회 (ex. httpMethod=GET)
                          Locale locale,            // Locale 정보를 조회 (ex. locale=en_KR)
                          @RequestHeader MultiValueMap<String, String> headerMap,   // 모든 HTTP 헤더를 MultiValueMap 형식으로 조회 ← Map과 유사한데 하나의 키에 여러 값을 받을 수 있다.
                          @RequestHeader("host") String host,   // 특정 HTTP 헤더를 조회
                          @CookieValue(value = "myCookie", required = false) String cookie  // 특정 쿠키를 조회
                          ) {

        log.info("request={}", request);
        log.info("response={}", response);
        log.info("httpMethod={}", httpMethod);
        log.info("locale={}", locale);
        log.info("headerMap={}", headerMap);
        log.info("header host={}", host);
        log.info("myCookie={}", cookie);

        return "ok";
    }

}
