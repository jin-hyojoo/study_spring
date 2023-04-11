package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
/*
        (23.03.23)
        구현체 없이 null이면 오류발생. 따라서 구현객체 선택 요함
        인터페이스 클래스-구현 객체 연결
        private final MemberRepository memberRepository = new MemoryMemberRepository();
*/

    private final MemberRepository memberRepository; // 23.03.24


    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
