package per.jz.user.dto;

/**
 * @author jemmyzhang on 2018/3/6.
 */
public class ErrorDTO {
    private Long errorCode;
    private String errorMessage;

    public ErrorDTO(Long errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public ErrorDTO() {
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
