package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

//junit5 이상부터는 public 설정 안해도 됨
class ApplicationTextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        // iter (for문 자동완성 키워드)
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);

            // key : beanName,  value : bean object
            System.out.println("####name = " + beanDefinitionName + " \n####object = " + bean);
            System.out.println("==============================================");
        }
    }

    @Test
    @DisplayName("APP 빈 출력하기")
    void findAppicationBean(){  // 스프링 내부 등록 빈 제외
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {

            // * BeanDefinition: 빈에 대한 정보들. 빈 하나하나에 대한 메타데이터 정보
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            // * Role ROLE_APPLICATION : 애플리케이션 관련 빈 (개발 위해 직접 등록한 애플리케이션 빈, 외부 라이브러리)
            // * Role ROLE_INFRASTRUCTURE : 스프링이 내부에서 사용하는 빈
            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("####name = " + beanDefinitionName + " \n####object = " + bean);
                System.out.println("==============================================");
            }
        }
    }
}
