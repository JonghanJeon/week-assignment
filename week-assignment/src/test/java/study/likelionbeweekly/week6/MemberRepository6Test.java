package study.likelionbeweekly.week6;

import static org.junit.jupiter.api.Assertions.*;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Transactional;
import study.likelionbeweekly.week6.entity.Member;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
class MemberRepository6Test {

    @Autowired
    private MemberRepository6 repository;
    @PersistenceContext
    private EntityManager entityManager;

    //auto-increment 값을 재지정 해주기 위함. (IDENTITY 사용)
    @AfterEach
    void afterEach() {
        this.entityManager
                .createNativeQuery("ALTER TABLE member ALTER COLUMN `MEMBER_ID` RESTART WITH 1")
                .executeUpdate();
    }

    @Test
    void saveMember() {
        //given
        Member member = new Member();
        member.setEmail("go@gmail.com");
        member.setName("고민석");

        //when
        Member savedMember = repository.save(member);

        /**
         * isEqualTo : 동등성 비교 즉, 객체의 내용이 같은지를 비교.
         * isSameAs : 동일성 비교 즉, 객체의 메모리 주소가 같은지를 비교.
         */
        //then
        assertThat(savedMember).isEqualTo(member);
    }

    @Test
    void findMemberById() {
        //given
        Member member1 = new Member();
        member1.setEmail("lee@gmail.com");
        member1.setName("이재훈");

        Member member2 = new Member();
        member2.setEmail("go@gmail.com");
        member2.setName("고민석");

        repository.save(member1);
        repository.save(member2);

        //when
        Member findMember1 = repository.findById(1L).orElse(new Member());
        Member findMember2 = repository.findById(2L).orElse(new Member());

        //then
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);
    }

    @Test
    void update() {
        //given
        Member member = new Member();
        member.setEmail("go@gmail.com");
        member.setName("고민석");
        repository.save(member);

        //when
        member.setName("석민고");
        Member findMember = repository.findById(1L).orElse(new Member());

        //then
        assertThat(findMember.getName()).isEqualTo("석민고");
    }

    @Test
    void delete() {
        //given
        Member member = new Member();
        member.setEmail("go@gmail.com");
        member.setName("고민석");
        repository.save(member);

        //when
        repository.deleteById(1L);
        List<Member> members = repository.findAll();

        //then
        assertThat(members.size()).isEqualTo(0);
    }
}