package per.jz.user.controller;

import org.springframework.web.bind.annotation.*;
import per.jz.user.service.UserService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jemmyzhang on 2017/12/3.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping({"","/"})
    public String user(){
        return "User Info!";
    }

    @GetMapping("/{id}")
    public String findUser(@PathVariable Long id){
        return "get user id = "+id;
    }

    @GetMapping("/favors")
    public List<String> findUserFavorites(){
        return userService.findUserFavoriteMovies();
    }

    @GetMapping("/exception")
    public String exceptionTest(){
        return userService.exceptionTest();
    }
}
