package pers.jz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pers.jz.dto.RestErrorDTO;

/**
 * @author jemmyzhang on 2018/3/6.
 */

@ControllerAdvice
@ResponseBody
public class ExceptionAdviser {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RestErrorDTO handleException(Exception e) {
        if (e instanceof RestException) {
            RestException restException = (RestException) e;
            return new RestErrorDTO(restException.getErrorCode(),restException.getMessage());
        }
        return new RestErrorDTO(10086L, e.getMessage());
    }
}
