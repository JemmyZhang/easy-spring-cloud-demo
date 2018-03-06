package pers.jz.dto;

/**
 * @author jemmyzhang on 2018/3/6.
 */
public class RestErrorDTO {
    private Long errorCode;
    private String errorMessage;

    public RestErrorDTO(Long errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public Long getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Long errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
