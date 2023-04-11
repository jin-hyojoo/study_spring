package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // type의 경우 class level에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {

    // MyExcludeComponent가 붙은건 컴포넌트 스캔에서 제외할거야 ~!
}
