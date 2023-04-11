package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingltonWithPrototypeTest {

    @Test
    void prototypeFind(){

        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);
        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);

        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }

    @Test
    void singletonClientUsePrototype(){
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ClientBean.class, PrototypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        assertThat(count1).isEqualTo(1);

        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();
        assertThat(count2).isEqualTo(1);
    }

    // =======================
    //  싱글톤 타입 빈
    // =======================
    @Scope("singleton")
    static class ClientBean{
        
//        * ObjectProvider 추가하면서 주석처리
//        private final PrototypeBean prototypeBean; //생성 시점에 주입, 생성 시점에 주입된 동일한 프로토타입 빈을 사용함

        /*
        싱글톤 빈 등록하면서 생성자에 프로토타입 빈을 스프링 컨테이너에 요청해 반환받음
        지정한 프로토타입 빈을 컨테이너에서 대신 찾아주는, 필요시에만 요청-반환받고 싶다면
        ObjectProvider(오브젝트 펙토리에 편의기능 추가된), ObjectFactory 등으로..
        */

//        @Autowired
//        public ClientBean(PrototypeBean prototypeBean){
//            this.prototypeBean = prototypeBean;
//        }

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;
//        private ObjectProvider<PrototypeBean> prototypeBeanProvider; // 항상 새로운 프로토타입 빈 생성

        public int logic(){
//          * ObjectProvider 용
//          PrototypeBean prototypeBean = prototypeBeanProvider.getObject();

            PrototypeBean prototypeBean = prototypeBeanProvider.get();

            prototypeBean.addCount();
            int count = prototypeBean.getCount();
            return count;
        }
    }

    // =======================
    //  프로토타입 빈
    // =======================
    @Scope("prototype")
    static class PrototypeBean{
       private int count = 0;

       public void addCount(){
           count ++;
       }

       public int getCount(){
           return count;
       }

       @PostConstruct
        public void init(){
           System.out.println("PrototypeBean.init___"+ this);
       }

       @PreDestroy
        public void destroy(){
           System.out.println("PrototypeBean.destroy___" + this);
       }
    }
}
