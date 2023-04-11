package hello.core.common;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
@Scope(value = "request")
//@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
/* 이 빈은 HTTP 요청 당 하나씩 생성되므로 uuid 저장해두면 다른 HTTP 요청과 구분 가능 */
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    // ex. [ee03cb90-a0a1-4150-a6ee-6579e1d8c066] [/log-demo] service id = testId
    public void log(String message){
        System.out.println("[" + uuid + "] " + "[" + requestURL + "] " + message);
    }

    // ex. [ee03cb90-a0a1-4150-a6ee-6579e1d8c066] request scope bean create : hello.core.common.MyLogger@64979b0a
    @PreDestroy
    public void close(){
        System.out.println("[" + uuid + "] " + "request scope bean close : " + this);
    }

    // ex. [ee03cb90-a0a1-4150-a6ee-6579e1d8c066] request scope bean close : hello.core.common.MyLogger@64979b0a
    @PostConstruct
    public void init(){
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] " + "request scope bean create : " + this);
    }
}
