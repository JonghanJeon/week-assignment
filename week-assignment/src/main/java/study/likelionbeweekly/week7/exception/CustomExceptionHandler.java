package study.likelionbeweekly.week7.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import study.likelionbeweekly.week7.comment.CommentCustomException.CommentNotFoundException;
import study.likelionbeweekly.week7.member.MemberCustomException.DuplicatedEmailException;
import study.likelionbeweekly.week7.member.MemberCustomException.MemberNotFoundException;
import study.likelionbeweekly.week7.member.MemberCustomException.IncorrectPasswordException;
import study.likelionbeweekly.week7.post.PostCustomException.PostNotFoundException;

@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<String> handleMemberNotFoundException(CustomException e) {
        log.error("CustomException occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(DuplicatedEmailException.class)
    public ResponseEntity<String> handleDuplicatedEmailException(CustomException e) {
        log.error("CustomException occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<String> handleIncorrectPasswordException(CustomException e) {
        log.error("CustomException occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<String> handlePostNotFoundException(CustomException e) {
        log.error("CustomException occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }

    @ExceptionHandler(CommentNotFoundException.class)
    public ResponseEntity<String> handleCommentNotFoundException(CustomException e) {
        log.error("CustomException occurred: {}", e.getMessage(), e);
        return ResponseEntity.status(e.getHttpStatus()).body(e.getMessage());
    }
}
