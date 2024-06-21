package study.likelionbeweekly.week6;

import jakarta.persistence.EntityManager;
import java.sql.Timestamp;
import java.time.Instant;
import javax.lang.model.element.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import study.likelionbeweekly.week6.entity.Comment;
import study.likelionbeweekly.week6.entity.Member;
import study.likelionbeweekly.week6.entity.Post;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@Rollback(false)
class Repository6Test {

    @Autowired
    private MemberRepository6 memberRepo;

    @Autowired
    private PostRepository6 postRepo;

    @Autowired
    private CommentRepository6 commentRepo;

    @Test
    @DisplayName("전체 테스트")
    void allTest() {
        Member member1 = new Member();
        member1.setName("고민석");
        member1.setEmail("go@gmail.com");

        Member member2 = new Member();
        member2.setName("이재훈");
        member2.setEmail("lee@gmail.com");

        memberRepo.save(member1);
        memberRepo.save(member2);

        Post post1 = new Post();
        setPost(post1, member1, 1);

        Post post2 = new Post();
        setPost(post2, member2, 2);

        postRepo.save(post1);
        postRepo.save(post2);

        Comment comment1 = new Comment();
        setComment(comment1, post1, member1, 1);

        Comment comment2 = new Comment();
        setComment(comment2, post1, member2, 2);

        commentRepo.save(comment1);
        commentRepo.save(comment2);

        Post findPostBy1 = postRepo.findById(1L).orElseThrow();
        Post findPostBy2 = postRepo.findById(2L).orElseThrow();

        System.out.println("findPostBy1.getComments().get(0) = " + findPostBy1.getComments().get(0));
        System.out.println("findPostBy1.getComments().get(1) = " + findPostBy1.getComments().get(1));

        Assertions.assertThat(findPostBy1).isEqualTo(post1);
        Assertions.assertThat(findPostBy2).isEqualTo(post2);

        System.out.println("findPostBy1 = " + findPostBy1);
        System.out.println("findPostBy2 = " + findPostBy2);

        Comment findCommentBy1 = commentRepo.findById(1L).orElseThrow();
        Comment findCommentBy2 = commentRepo.findById(2L).orElseThrow();

        Assertions.assertThat(findCommentBy1).isEqualTo(comment1);
        Assertions.assertThat(findCommentBy2).isEqualTo(comment2);

        System.out.println("findCommentBy1 = " + findCommentBy1);
        System.out.println("findCommentBy2 = " + findCommentBy2);
    }

    private static void setComment(Comment comment1, Post post1, Member member1, int number) {
        comment1.setPost(post1);
        post1.addComment(comment1);
        comment1.setMember(member1);
        comment1.setCreateTime(Timestamp.from(Instant.now()));
        comment1.setModifyTime(Timestamp.from(Instant.now()));
        comment1.setContent(number + " 번재 댓글입니다.");
    }

    private static void setPost(Post post2, Member member2, int number) {
        post2.setTitle(number + " 번째 게시물");
        post2.setContent(number + " 번째 본문입니다.");
        post2.setMember(member2);
        post2.setCreateTime(Timestamp.from(Instant.now()));
        post2.setModifyTime(Timestamp.from(Instant.now()));
    }
}