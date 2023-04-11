package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


/*
*   프로토타입 스코프 빈은 스프링 컨테이너에서 빈을 조회할 때 생성 . 초기화 메서드도 실행
*   프로토타입 빈은 스프링 컨테이너 생성 - 의존관계 주입 - 초기화 까지만 진행하고
*   그 이후는 관여하지 X
*   따라서 @PreDestory 종료 메서드 호출 안함 = 전혀 실행되지 X
*   호출해야할 경우 ex) prototypeBean1.destroy(); 이런식으로 직접호출
*/
public class PrototypeTest {

    @Test
    void prototypeBeanFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        System.out.println("prototypeBean1 = " + prototypeBean1);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        
        // 같지 않을 시 테스트 성공
        Assertions.assertThat(prototypeBean1).isNotSameAs(prototypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class PrototypeBean{
        @PostConstruct
        public void init(){
            System.out.println("PrototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("PrototypeBean.destroy");
        }
    }
}
