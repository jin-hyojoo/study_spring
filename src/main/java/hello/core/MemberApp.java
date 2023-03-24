package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {  // 단축키키워드 psvm

//        MemberService memberService = new MemberServiceImpl();
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        /*
        *  (스프링의 시작) ApplicationContext
        *  스프링 컨테이너라 생각하면 됨. 모든걸 관리
        *  어노테이션으로 관리할 예정이기 때문에 AnnotationConfigApplicationContext() 생성
        *  이때 AppConfig.class를 파라미터로 넘김으로써 AppConfig에 있는 환경설정 정보를 바탕으로
        *  빈 객체를 컨테이너에 집어넣어 관리. 즉, @Bean으로 등록한 모든 객체를 관리
        *
        */

        // Spring 컨테이너 적용
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        // new뒤만 작성후 Ctrl+Alt+v 하면 변수생성
        // id : long type이라 끝에 L 붙임
        Member member = new Member(1L, "memberA", Grade.VIP);

        // 회원가입
        memberService.join(member);

        // 회원조회
        Member findMember = memberService.findMember(1L);

        // 확인,  단축키키워드 soutv
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
