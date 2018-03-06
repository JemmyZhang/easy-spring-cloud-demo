package per.jz.user.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * @author jemmyzhang on 2018/2/25.
 */
@FeignClient("simple-movie-service")
public interface MovieService {

    @RequestMapping(method = RequestMethod.GET,value = "/movies")
    public List<String> getMovieList();

    @RequestMapping(method = RequestMethod.GET,value = "/exception")
    public String exceptionTest();
}
