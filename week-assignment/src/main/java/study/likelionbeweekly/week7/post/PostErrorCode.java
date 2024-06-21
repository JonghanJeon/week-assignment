package study.likelionbeweekly.week7.post;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import study.likelionbeweekly.week7.exception.BaseErrorCode;

@AllArgsConstructor
public enum PostErrorCode implements BaseErrorCode {
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 게시글");

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
