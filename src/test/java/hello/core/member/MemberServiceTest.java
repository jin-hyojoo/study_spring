package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }

    @Test
    void join() {
        // given
        Member member = new Member(1L, "memberA", Grade.VIP);

        // when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);

        // then

        /* (검증단계)
         * Assertion  => 실제 test에서 검증하고자 하는 내용을 확인하는 기술
         * assertThat => method 사용시 가독성 해결 및 사용법 개선을 위해 "메소드 체이닝 패턴" 형식인 assertThat 제공
         *               메소드 체이닝 패턴 https://velog.io/@awdsza/%EB%A9%94%EC%86%8C%EB%93%9C-Chaining 참고
         *
         * 인자로는 actual(검증대상)만..
         */
        Assertions.assertThat(member).isEqualTo(findMember);
    }
}
