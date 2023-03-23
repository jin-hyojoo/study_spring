package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.order.Order;
import hello.core.order.OrderSerivceImpl;
import hello.core.order.OrderService;

public class OrderApp {
    public static void main(String[] args) {

        MemberService memberService = new MemberServiceImpl();
        OrderService orderService = new OrderSerivceImpl();

        // 회원가입 위한 info 입력
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        
        // 회원가입
        memberService.join(member);

        // 주문
        Order order = orderService.createOrder(memberId, "itemA", 10000);
        
        // 할인 적용된 최종 결제 금액
        System.out.println("order = " + order.calculatePrice());

    }
}
