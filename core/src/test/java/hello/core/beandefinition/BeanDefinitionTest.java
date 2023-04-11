package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BeanDefinitionTest {

    //AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
    // xml로 주입한 bean의 경우 beanDefinition의 factory관련 값은 null
    // 자바코드로 bean을 주입할 때 팩토리 메서드 사용
    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");
    
    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean(){

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();

        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        "\nbeanDefinition = " + beanDefinition);
                System.out.println("================================");
            }
        }
    }
}
