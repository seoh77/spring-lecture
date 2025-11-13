package hello.servlet.web.springmvc.v1;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller     // 스프링이 자동으로 스프링 빈으로 등록한다. (내부에 @Component 애노테이션이 있어서 컴포넌트 스캔의 대상이 됨)
// 과거에는 가능했지만, 현재는 클래스 레벨에 @RequestMapping이 있어도 스프링 컨트롤러로 인식하지 않는다. @Controller가 있어야만 스프링 컨트롤러로 인식!
public class SpringMemberFormControllerV1 {

    @RequestMapping("/springmvc/v1/members/new-form")   // 요청 정보를 매핑한다. 해당 URL이 호출되면 이 메서드가 호출된다.
    public ModelAndView process() {                // 에노테이션 기반으로 동작하기 때문에, 메서드의 이름은 임의로 지으면 된다.
        return new ModelAndView("new-form");
    }
}
