package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=NyoZu&age=27
 *
 */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("======== 전체 파라미터 조회 [START] ========");
        // sout에서 paramName은 Key, request.getParameter(paramName)은 value
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> System.out.println(paramName + " = " + request.getParameter(paramName)));
        System.out.println("======== 전체 파라미터 조회 [END] ========\n");


        System.out.println("======== 단일 파라미터 조회 [START] ========");
        String username = request.getParameter("username");
        String age = request.getParameter("age");
        System.out.println("username = " + username);
        System.out.println("age = " + age);
        System.out.println("======== 단일 파라미터 조회 [END] ========\n");


        // http://localhost:8080/request-param?username=NyoZu&age=27&username=NyoZu2
        System.out.println("======== 이름이 같은 복수 파라미터 조회 [START] ========");
        String[] usernames = request.getParameterValues("username");
        for (String nm : usernames) {
            System.out.println("username = " + nm);
        }
        System.out.println("======== 이름이 같은 복수 파라미터 조회 [END] ========");

        response.getWriter().write("CHECK YOUR CONSOLE \nIF YOU SEE THE RESULT!");
    }
}
