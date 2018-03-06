package per.jz.user.config.decoder;

import com.coveo.feign.ReflectionErrorDecoder;
import org.springframework.stereotype.Service;
import per.jz.user.dto.ErrorDTO;
import per.jz.user.exception.RestException;

/**
 * @author jemmyzhang on 2018/3/6.
 */

public class DefaultFeignDecoder extends ReflectionErrorDecoder<ErrorDTO,RestException>{

    public DefaultFeignDecoder(Class<?> apiClass) {
        super(apiClass, ErrorDTO.class, RestException.class);
    }

    @Override
    protected String getKeyFromException(RestException exception) {
        return exception.getErrorCode().toString();
    }

    @Override
    protected String getKeyFromResponse(ErrorDTO apiResponse) {
        return apiResponse.getErrorCode().toString();
    }

    @Override
    protected String getMessageFromResponse(ErrorDTO apiResponse) {
        return apiResponse.getErrorMessage();
    }
}
