package hello.servlet.basic;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name= "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet { // 서블릿 사용시 HttpServlet 상속 필수

    // ctrl + O 를 통해 잠겨있는 service 메서드 선택
    // 서블릿 호출 시 서비스 메소드 실행 됨
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("HelloServlet.service");
        System.out.println("req = " + req);
        System.out.println("resp = " + resp);

        // HTTP 요청 method => Query Parameter
        String username = req.getParameter("username");
        System.out.println("username = " + username);
        System.out.println("==================================");

        // ** http 응답 메세지에 데이터가 담겨 나감
        // http content type에 들어감 => 헤더정보
        resp.setContentType("text/plain");
        resp.setCharacterEncoding("utf-8");
        
        // http body에 message 들어감
        resp.getWriter().write("hello " + username + " !!");

    }
}
