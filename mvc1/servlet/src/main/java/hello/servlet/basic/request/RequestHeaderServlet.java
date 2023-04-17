package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // (1)
        //printStartLine(request);

        // (2)
        //printHeaders(request);

        // (3)
        // printHeaderUtils(request);

        // (4)
        printEtc(request);

    }


    /* ============================================
       (1) < printStartLine() _결과 미리보기 >

    --- REQUEST-LINE start ---
    request.getMethod() = GET
    request.getProtocol() = HTTP/1.1
    request.getScheme() = http
    request.getRequestURL() = http://localhost:8080/request-header
    request.getRequestURI() = /request-header
    request.getQueryString() = username=NyoZu
    request.isSecure() = false
    --- REQUEST-LINE end ---
    ============================================ */
    // Extract Method 단축키 => ctrl + alt + shift + T
    // ============================================
    private static void printStartLine(HttpServletRequest request) {

        System.out.println("--- REQUEST-LINE start ---");
        System.out.println("request.getMethod() = " + request.getMethod());          // GET
        System.out.println("request.getProtocol() = " + request.getProtocol());      // HTTP/1.1
        System.out.println("request.getScheme() = " + request.getScheme());          // http

        // http://localhost:8080/request-header
        System.out.println("request.getRequestURL() = " + request.getRequestURL());

        // /request-header
        System.out.println("request.getRequestURI() = " + request.getRequestURI());

        //username=Nyozu
        System.out.println("request.getQueryString() = " + request.getQueryString());

        System.out.println("request.isSecure() = " + request.isSecure());             // https 사용유무
        System.out.println("--- REQUEST-LINE end ---\n");
    }

    /* ============================================
       (2) < Header 모든 정보 _결과 미리보기 >
        
        --- Headers start ---
        host: localhost:8080
        connection: keep-alive
        sec-ch-ua: "Chromium";v="112", "Google Chrome";v="112", "Not:A-Brand";v="99"
        sec-ch-ua-mobile: ?0
        sec-ch-ua-platform: "Windows"
        upgrade-insecure-requests: 1
        user-agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36
        accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*...(주석 충돌로 일부 문자 생략)
        purpose: prefetch
        sec-fetch-site: none
        sec-fetch-mode: navigate
        sec-fetch-user: ?1
        sec-fetch-dest: document
        accept-encoding: gzip, deflate, br
        accept-language: ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7
        --- Headers end ---
    ============================================ */
    private void printHeaders(HttpServletRequest request) {
        System.out.println("--- Headers start ---");

//         Enumeration<String> headerNames = request.getHeaderNames();
//         while (headerNames.hasMoreElements()) {
//         String headerName = headerNames.nextElement();
//         System.out.println(headerName + ": " + request.getHeader(headerName));
//         }

        request.getHeaderNames().asIterator()
                .forEachRemaining(headerName -> System.out.println(headerName + ": " + request.getHeader(headerName)));
        System.out.println("--- Headers end ---\n");
    }

    /* ============================================
       (3) < Header 편의 조회 _결과 미리보기 >

        --- Header 편의 조회 start ---
        [Host 편의 조회]
        request.getServerName() = localhost
        request.getServerPort() = 8080

        [Accept-Language 편의 조회]
        locale = ko_KR
        locale = ko
        locale = en_US
        locale = en
        request.getLocale() = ko_KR

        [cookie 편의 조회]

        [Content 편의 조회]
        request.getContentType() = null
        request.getContentLength() = -1
        request.getCharacterEncoding() = UTF-8
        --- Header 편의 조회 end ---
    ============================================ */
    private void printHeaderUtils(HttpServletRequest request) {

        System.out.println("--- Header 편의 조회 start ---");
        System.out.println("[Host 편의 조회]");
        System.out.println("request.getServerName() = " + request.getServerName()); //Host 헤더
        System.out.println("request.getServerPort() = " + request.getServerPort()); //Host 헤더
        System.out.println();

        System.out.println("[Accept-Language 편의 조회]");
        request.getLocales().asIterator()
                .forEachRemaining(locale -> System.out.println("locale = " +
                        locale));
        System.out.println("request.getLocale() = " + request.getLocale());
        System.out.println();

        System.out.println("[cookie 편의 조회]");
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                System.out.println(cookie.getName() + ": " + cookie.getValue());
            }
        } System.out.println();

        // GET은 content 안보내기 때문에 실행하면 null.
        // 결과값 얻으려면 POST로,,
        System.out.println("[Content 편의 조회]");
        System.out.println("request.getContentType() = " +
                request.getContentType());
        System.out.println("request.getContentLength() = " +
                request.getContentLength());
        System.out.println("request.getCharacterEncoding() = " +
                request.getCharacterEncoding());
        System.out.println("--- Header 편의 조회 end ---");
        System.out.println();
    }

    /* ============================================
       (4) < 기타정보 _결과 미리보기 >
       : http 메세지가 아니라
         내부에서 네트워크 커넥션이 맺어진 정보를 갖고 알 수 있는 정보

        --- 기타 조회 start ---
        [Remote 정보]
        request.getRemoteHost() = 0:0:0:0:0:0:0:1
        request.getRemoteAddr() = 0:0:0:0:0:0:0:1
        request.getRemotePort() = 61740

        [Local 정보]
        request.getLocalName() = 0:0:0:0:0:0:0:1
        request.getLocalAddr() = 0:0:0:0:0:0:0:1
        request.getLocalPort() = 8080
        --- 기타 조회 end ---
     ============================================ */
    private void printEtc(HttpServletRequest request) {
        System.out.println("--- 기타 조회 start ---");
        System.out.println("[Remote 정보]");
        System.out.println("request.getRemoteHost() = " + request.getRemoteHost());
        System.out.println("request.getRemoteAddr() = " + request.getRemoteAddr());
        System.out.println("request.getRemotePort() = " + request.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("request.getLocalName() = " + request.getLocalName());
        System.out.println("request.getLocalAddr() = " + request.getLocalAddr());
        System.out.println("request.getLocalPort() = " + request.getLocalPort());
        System.out.println("--- 기타 조회 end ---");
        System.out.println();
    }
}

