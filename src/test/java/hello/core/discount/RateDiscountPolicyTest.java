package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;  // (static 임포트) 단축키 Alt+Enter

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_o(){

        //given
        Member memberVip = new Member(1L, "memberVIP", Grade.VIP);

        //when
        int discount = discountPolicy.discount(memberVip, 10000);

        //then
        assertThat(discount).isEqualTo(1000);

    }

    @Test
    @DisplayName("VIP가 아니면 할인이 적용되지 않아야 한다.")
    void vip_x(){

        //given
        Member memberVip = new Member(1L, "memberBASIC", Grade.BASIC);

        //when
        int discount = discountPolicy.discount(memberVip, 10000);

        //then
        assertThat(discount).isEqualTo(1000);

    }
}