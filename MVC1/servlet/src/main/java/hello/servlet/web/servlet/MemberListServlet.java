package hello.servlet.web.servlet;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/*
    지금까지는 서블릿과 자바 코드만으로 HTML을 만들었다.
    하지만 코드에서 보듯이 이 방식은 매우 복잡하고 비효율적이다.
    자바 코드로 HTML을 만드는 것보다 차라리 HTML 문서에 동적으로 변경해야 하는 부분만 자바 코드를 넣는 것이 더 편리할 것이다.

    → 이것이 템플릿 엔진이 나온 이유다. 템플릿 엔진을 사용하면 HTML 문서에서 필요한 곳만 코드를 적용해 동적으로 변경할 수 있다.
      템플릿 엔진에는 JSP, Thymeleaf, Freemarker, Velocity 등이 있다.
      (참고로 JSP는 성능과 기능면에서 다른 템플릿 엔진과의 경쟁에서 밀렸다. 스프링과 잘 통합되는 Thymeleaf 사용 권장)
 */

@WebServlet(name = "memberListServlet", urlPatterns = "/servlet/members")
public class MemberListServlet extends HttpServlet {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Member> members = memberRepository.findAll();

        response.setContentType("text/html");
        response.setCharacterEncoding("utf-8");

        PrintWriter w = response.getWriter();
        w.write("<html>");
        w.write("<head>");
        w.write("   <meta charset=\"UTF-8\">");
        w.write("   <title>Title</title>");
        w.write("</head>");
        w.write("<body>");
        w.write("<a href=\"/index.html\">메인</a>");
        w.write("<table>");
        w.write("   <thead>");
        w.write("   <th>id</th>");
        w.write("   <th>username</th>");
        w.write("   <th>age</th>");
        w.write("   </thead>");
        w.write("   <tbody>");

        for (Member member : members) {
            w.write("   <tr>");
            w.write("       <td>"+member.getId()+"</td>");
            w.write("       <td>"+member.getUsername()+"</td>");
            w.write("       <td>"+member.getAge()+"</td>");
            w.write("   </tr>");
        }

        w.write("   </tbody>");
        w.write("</table>");
        w.write("</body>");
        w.write("</html>");
    }
}
