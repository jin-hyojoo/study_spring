package hello.core.beanfind;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
    
    @Test
    @DisplayName("부모 타입 조회시 자식 둘 이상이면 중복 오류 발생")
    void findBeanByParentTypeDuplicate(){
//        DiscountPolicy bean = ac.getBean(DiscountPolicy.class);
        assertThrows(NoUniqueBeanDefinitionException.class,
                ()-> ac.getBean(DiscountPolicy.class));
    }

    @Test
    @DisplayName("부모 타입 조회시 자식 둘 이상이면 빈 이름 지정")
    void findBeanByParentTypeBeanName(){
        RateDiscountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", RateDiscountPolicy.class);
        Assertions.assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    /*  (output_일부)
        key = rateDiscountPolicy
        value = hello.core.discount.RateDiscountPolicy@6035b93b
    */
    @Test
    @DisplayName("부모타입으로 모두 조회")
    void findAllBeanByParentType(){
        Map<String, DiscountPolicy> beansOfType = ac.getBeansOfType(DiscountPolicy.class);
        Assertions.assertThat(beansOfType.size()).isEqualTo(2);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "\nvalue = " + beansOfType.get(key));
            System.out.println("======================");
        }

    }


    /*  (output_일부) 
        :: spring 내부 bean까지 조회 됨
        :: Why? 자바의 모든 객체는 object 타입이기 때문에 spring bean에 등록된 all 객체 조회

        key = org.springframework.context.annotation.internalConfigurationAnnotationProcessor
        value = org.springframework.context.annotation.ConfigurationClassPostProcessor@68ead359
     */
    @Test
    @DisplayName("부모타입으로 모두 조회 (Object ver)")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "\nvalue = " + beansOfType.get(key));
            System.out.println("======================");
        }
    }

    @Configuration
    static class TestConfig{

        @Bean
        public DiscountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }

        @Bean
        public DiscountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }
    }
}
