package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {
    public static void main(String[] args) {

//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();
//        OrderService orderService = appConfig.orderService();

        // Spring 컨테이너 적용
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        OrderService orderService = ac.getBean("orderService", OrderService.class);

        // 회원가입 위한 info 입력
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);

        // 회원가입
        memberService.join(member);

        // 주문
        Order order = orderService.createOrder(memberId, "itemA", 15000);

        // 할인 적용된 최종 결제 금액
        System.out.println("########## order info ########## " + order);
        System.out.println("########## pay        ########## " + order.calculatePrice());

    }
}
