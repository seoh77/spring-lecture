package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/*
    스프링(서버)에서 응답 데이터를 만드는 방법
    1. 정적 리소스
      - 웹 브라우저에 정적인 HTML, css, js를 제공할 때는 정적 리소스를 사용한다.
      - 스프링부트는 클래스패스의 다음 디렉토리에 있는 정적 리소스를 제공한다.
        `/static`, `/public`, `/resources`, `/META-INF/resources`
      - `src/main/resources`는 리소스를 보관하는 곳이고, 또 클래스패스의 시작 경로이다.
         따라서 다음 디렉토리에 리소스를 넣어두면 스프링부트가 정적 리소스로 서비스를 제공한다.

    2. 뷰 템플릿 사용
      - 웹 브라우저에 동적인 HTML을 제공할 때는 뷰 템플릿을 사용한다.
      - 뷰 템플릿 경로 : `src/main/resources/template`

    3. HTTP 메시지 사용
      - HTTP API를 제공하는 경우에는 HTML이 아니라 데이터를 전달해야 하므로, HTTP 메시지 바디에 JSON 같은 형식으로 데이터를 실어 보낸다.
 */

@Controller
public class ResponseViewController {

    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewV1() {
        ModelAndView mav = new ModelAndView("response/hello")
                .addObject("data", "hello!");

        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewV2(Model model) {
        model.addAttribute("data", "hello!");

        // @Controller를 넣은 상태에서 String을 반환하면 이게 뷰의 논리적 이름이 된다.
        // 단, @ResponseBody나 @RestController 사용하면 뷰를 찾지 않고 응답 메세지로 나가버림
        return "response/hello";
    }

    /*
        @Controller를 사용하고, HttpServletResponse, OutputStream(Writer) 같은 HTTP 메시지 바디를 처리하는 파라미터가 없으면
        요청 URL을 참고해서 논리 뷰 이름으로 사용
        → 이 방식은 명시성이 너무 떨어지고, 이렇게 딱 맞는 경우도 많이 없어서 권장하지 않는다.
     */
    @RequestMapping("/response/hello")
    public void responseViewV3(Model model) {
        model.addAttribute("data", "hello!");
    }
}
