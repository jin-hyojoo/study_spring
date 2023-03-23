package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {  // 단축키키워드 psvm

        MemberService memberService = new MemberServiceImpl();

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
