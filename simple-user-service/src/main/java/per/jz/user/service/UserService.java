package per.jz.user.service;

import org.springframework.stereotype.Component;
import per.jz.user.feign.MovieService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jemmyzhang on 2017/12/3.
 */

@Component
public class UserService {

    @Resource
    MovieService movieService;

    public List<String> findUserFavoriteMovies(){
        return movieService.getMovieList();
    }
}
