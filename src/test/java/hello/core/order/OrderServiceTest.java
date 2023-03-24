package hello.core.order;

import hello.core.AppConfig;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void BeforeEach() {
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }

    @Test
    void createOrder(){

        // given :: 회원가입 위한 info 입력
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // when :: 회원가입
        memberService.join(member);
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        // then :: 주문
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);

    }
}
