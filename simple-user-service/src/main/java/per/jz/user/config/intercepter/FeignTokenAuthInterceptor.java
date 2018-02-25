package per.jz.user.config.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author jemmyzhang on 2018/2/25.
 */
@Configuration
@PropertySource(value = "classpath:/config/goblin")
public class FeignTokenAuthInterceptor implements RequestInterceptor {

    @Value("${goblin}")
    private String serviceToken;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", serviceToken);
    }
}
