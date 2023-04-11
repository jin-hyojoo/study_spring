package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

class StatefulServiceTest {

    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService",StatefulService.class);

        //ThreadA : A사용자 만원 주문
        statefulService1.order("userA", 10000);
        
        //ThreadB : B사용자 2만원 주문
        statefulService2.order("userB", 20000);
        
        //ThreadA : A사용자 주문금액 조회
        int price = statefulService1.getPrice();
        
        //공유되는 price 필드라 A사용자 주문금액을 조회하려 해도 B사용자의 주문금액으로 덮여쓰여졌기 때문에 2만원이 조회됨
        //공유필드 조심 또 조심!!! 스프링 빈은 항상 무상태로 설계
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}