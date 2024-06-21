package study.likelionbeweekly.week7.comment;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import study.likelionbeweekly.week7.exception.BaseErrorCode;

@AllArgsConstructor
public enum CommentErrorCode implements BaseErrorCode {

    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 댓글");

    private final HttpStatus httpStatus;
    private final String message;

    @Override
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getErrorMessage() {
        return message;
    }
}
