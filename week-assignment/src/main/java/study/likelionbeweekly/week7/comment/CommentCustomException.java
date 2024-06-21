package study.likelionbeweekly.week7.comment;

import study.likelionbeweekly.week7.exception.BaseErrorCode;
import study.likelionbeweekly.week7.exception.CustomException;

public class CommentCustomException extends CustomException {
    public CommentCustomException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public static class CommentNotFoundException extends CommentCustomException {
        public CommentNotFoundException() {
            super(CommentErrorCode.COMMENT_NOT_FOUND);
        }
    }
}
