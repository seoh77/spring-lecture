<%--
    서블릿과 JSP의 한계
    서블릿으로 개발할 때는 뷰(View) 화면을 위한 HTML을 만드는 작업이 자바 코드에 섞여서 지저분하고 복잡했다.
    JSP를 사용한 덕분에 뷰를 생성하는 HTML 작업을 깔끔하게 가져가고, 중간중간 동적으로 변경이 필요한 부분에만 자바 코드를 적용했다.
    하지만 코드를 잘 보면 Java 코드, 데이터를 조회하는 리포지토리 등 다양한 코드가 모두 JSP에 노출되어 있다.
    즉 JSP가 너무 많은 역할을 하고, 유지보수도 어렵다.

   → MVC 패턴 등장
     : 비즈니스 로직은 서블릿처럼 다른곳에서 처리하고, JSP는 목적에 맞게 HTML로 화면을 그리는 일에만 집중
--%>

<%@ page import="java.util.List" %>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    List<Member> members = memberRepository.findAll();
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<a href="/index.html">메인</a>
<table>
    <thead>
        <th>id</th>
        <th>username</th>
        <th>age</th>
    </thead>
    <tbody>
    <%
        for (Member member : members) {
            out.write("   <tr>");
            out.write("       <td>"+member.getId()+"</td>");
            out.write("       <td>"+member.getUsername()+"</td>");
            out.write("       <td>"+member.getAge()+"</td>");
            out.write("   </tr>");
        }
    %>
    </tbody>
</table>
</body>
</html>