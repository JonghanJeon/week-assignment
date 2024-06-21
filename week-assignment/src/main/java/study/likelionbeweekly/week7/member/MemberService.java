package study.likelionbeweekly.week7.member;

import jakarta.persistence.EntityNotFoundException;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

        Member member = memberRepository.findByEmail(loginEmail)
                .orElseThrow(EntityNotFoundException::new);

        checkLoginEmailAndPassword(loginEmail, loginPassword, member);
        return member;
    }

    private void checkLoginEmailAndPassword(String loginEmail, String loginPassword, Member member) {
        String email = member.getEmail();
        String password = member.getPassword();
        if (!Objects.equals(email, loginEmail) || !Objects.equals(password, loginEmail)) {
            throw new IllegalArgumentException("비밀번호 불일치");
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
                .orElseThrow(EntityNotFoundException::new);

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
            throw new IllegalArgumentException("중복 이메일");
        }
    }

    @Transactional
    public void deleteMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
        member.setDeleted(true);
    }
}