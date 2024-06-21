package study.likelionbeweekly.week7.member;

import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.likelionbeweekly.week7.member.MemberCustomException.DuplicatedEmailException;
import study.likelionbeweekly.week7.member.MemberCustomException.MemberNotFoundException;
import study.likelionbeweekly.week7.member.MemberCustomException.IncorrectPasswordException;
import study.likelionbeweekly.week7.member.dto.JoinMemberRequest;
import study.likelionbeweekly.week7.member.dto.LoginMemberRequest;
import study.likelionbeweekly.week7.member.dto.UpdateMemberRequest;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;

    public Member loginMember(LoginMemberRequest request) {
        String loginEmail = request.email();
        String loginPassword = request.password();

        System.out.println("loginEmail = " + loginEmail);
        System.out.println("loginPassword = " + loginPassword);

        Member member = memberRepository.findByEmail(loginEmail)
                .orElseThrow(MemberNotFoundException::new);

        System.out.println("#################################################");

        checkLoginEmailAndPassword(loginEmail, loginPassword, member);
        return member;
    }

    private void checkLoginEmailAndPassword(String loginEmail, String loginPassword, Member member) {
        String email = member.getEmail();
        String password = member.getPassword();
        if (!Objects.equals(email, loginEmail) || !Objects.equals(password, loginEmail)) {
            throw new IncorrectPasswordException();
        }
    }

    @Transactional
    public void joinMember(JoinMemberRequest request) {
        String joinName = request.name();
        String joinEmail = request.email();
        String joinPassword = request.password();

        Optional<Member> optionalMember = memberRepository.findByEmail(joinEmail);
        checkDuplicateEmail(optionalMember);

        Member member = new Member(joinName, joinEmail, joinPassword);
        memberRepository.save(member);
    }

    @Transactional
    public void updateMember(Long id, UpdateMemberRequest request) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);

        String updateName = request.name();
        String updateEmail = request.email();
        String updatePassword = request.password();

        Optional<Member> optionalMember = memberRepository.findByEmail(updateEmail);
        checkDuplicateEmail(optionalMember);

        member.setName(updateName);
        member.setEmail(updateEmail);
        member.setPassword(updatePassword);
    }

    private void checkDuplicateEmail(Optional<Member> optionalMember) {
        if (optionalMember.isPresent()) {
            throw new DuplicatedEmailException();
        }
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(MemberNotFoundException::new);
        member.setDeleted(true);
    }
}
