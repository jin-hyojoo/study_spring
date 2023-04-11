package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final MyLogger myLogger;

    /*
        ScopeNotActiveException: Error creating bean with name 'myLogger': Scope 'request'
        is not active for the current thread; consider defining a scoped proxy for this bean
        if you intend to refer to it from a singleton;

        스프링 컨테이너 실행 시 빈을 등록해야하는데 리퀘스트 스코프인 myLogger는 요청이 없어 오류내뱉음
        즉, LogDemoController를 생성할 때 생성자로 아직 생성되지도 않은 myLogger 빈을 전달해야 하기 때문에 오류발생
        = http request는 없기 때문에 request 스코프 빈은 미생성 됐기 때문

        <해결> @provider
    */
    private final ObjectProvider<MyLogger> myLoggerProvider;


    // log-demo라는 요청이 들어오면
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request){

        /*
            requestURL 값 myLogger에 저장부분은 사실 컨트롤러 보다 공통처리 가능한 
            스프링 인터셉트나 서블릿 필터같은 곳을 사용하는 것이 좋음
            강의에서는 예제 단순화를 위해 컨트롤러 사용. 스프링 웹 익숙하면 인터셉터 사용해 구현
         */

        // 고객 요청정보를 받아 반환 (고객이 어떤 url로 요청했는지 알 수 있음)
        String requestURL = request.getRequestURI().toString();
        
        // ObjectProvider 관련
        MyLogger myLogger = myLoggerProvider.getObject();
        myLogger.setRequestURL(requestURL);
        myLogger.log("controller test");

        logDemoService.logic("testId");
        return "응답완료 OK";

    }

}
