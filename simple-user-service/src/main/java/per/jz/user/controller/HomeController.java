package per.jz.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jemmyzhang on 2018/2/22.
 */
@RestController
public class HomeController {

    @GetMapping({"/","/home","/info"})
    public String home(){
        return "User Home!";
    }

}
