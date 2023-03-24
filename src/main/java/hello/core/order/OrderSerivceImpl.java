package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderSerivceImpl implements OrderService{

    /*
     *  할인정책이 변경됐어!! => FixDiscountPolicy에서 RateDiscountPolicy로 변경
     *  이로써 역할/구현 분리 ok, 다형성 활용 ok, 인터페이스/구현 객제 분리 ok
     *  하지만? OCP-DIP 설계원칙은 준수한 것 같아 보이나 사실은 아니였음
     *  WHY? 추상에만 의존해야 하는데(DiscountPolicy) 구체=구현객체(RateDiscountPolicy)에도 의존
     *  즉, 정책 바뀌기 전에 사용했던 FixDiscountPolicy는 DIP 위반
     *     정책 변경되어 RateDiscountPolicy로 변경하는 순간 OCP 위반
     *
     *  (개선)
     *  인터페이스에만 의존하도록 기존 코드 변경
     *  1) 인터페이스 선언 후
     *  2) 클라이언트 OrderServiceImpl에 DiscountPolicy의 구현객체를 대신 생성,주입 => 관심사의 분리
     *
     */

    //===============
    // OCP, DIP 위반
    //===============
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();  // * DIP위반
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // * OCP위반


    //===============
    //     개선
    //===============
    // 생성자 주입
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderSerivceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
