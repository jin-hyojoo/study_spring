package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient{
    private String url;

    public NetworkClient(){
        System.out.println("생성자를 호출, url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // 서비스 시작시 호출
    public void connet(){
        System.out.println("connet: " + url);
    }

    public void call(String message){
        System.out.println("call : " + url + "  |  message = " + message );
    }
    
    //서비스 종료시 호출
    public void disconnet(){
        System.out.println("disconnet : " + url);
    }

    @PostConstruct
    public void init() throws Exception {
        connet();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnet();
    }
}
