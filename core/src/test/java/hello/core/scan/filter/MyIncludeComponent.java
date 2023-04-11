package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) // type의 경우 class level에 붙음
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {

    // MyIncludeComponent가 붙은건 컴포넌트 스캔에 추가할거야 ~!
}
