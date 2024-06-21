package study.likelionbeweekly.week7.post;

import study.likelionbeweekly.week7.exception.BaseErrorCode;
import study.likelionbeweekly.week7.exception.CustomException;

public class PostCustomException extends CustomException {
    public PostCustomException(BaseErrorCode errorCode) {
        super(errorCode);
    }

    public static class PostNotFoundException extends PostCustomException {
        public PostNotFoundException() {
            super(PostErrorCode.POST_NOT_FOUND);
        }
    }
}
