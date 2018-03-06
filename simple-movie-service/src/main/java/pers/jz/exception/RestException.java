package pers.jz.exception;

/**
 * @author jemmyzhang on 2018/3/6.
 */
public class RestException extends RuntimeException {

    private Long errorCode;

    public RestException(Long errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public RestException(Long errorCode) {
        this.errorCode = errorCode;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return super.getMessage();
    }
}
