package per.jz.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import per.jz.user.config.intercepter.FeignTokenAuthInterceptor;

/**
 * @author jemmyzhang on 2018/2/25.
 */
@Configuration
public class WebConfig {

    @Bean
    public FeignTokenAuthInterceptor feignTokenAuthInterceptor(){
        return new FeignTokenAuthInterceptor();
    }
}
