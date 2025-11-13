package hello.servlet.web.frontcontroller.v1;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/*
    FrontController 패턴 특징
    - 프론트 컨트롤러 서블릿 하나로 클라이언트 요청을 받음
    - 프론트 컨트롤러가 요청에 맞는 컨트롤러를 찾아서 호출
    - 입구를 하나로!
    - 공통 처리 가능
    - 프론트 컨트롤러를 제외한 나머지 컨트롤러는 서블릿을 사용하지 않아도 됨
 */

public interface ControllerV1 {

    // 서블릿과 비슷한 모양의 컨트롤러 인터페이스를 도입 → 각 컨트롤러들은 이 인터페이스를 구현하면 된다.
    // 프론트 컨트롤러는 이 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 가져갈 수 있다.
    void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;

}
