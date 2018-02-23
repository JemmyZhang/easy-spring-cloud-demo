package per.jz.user.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author jemmyzhang on 2017/12/3.
 */

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @GetMapping({"","/"})
    public String user(){
        return "User Info!";
    }

    @RequestMapping(value = "/mapping",method = RequestMethod.GET)
    public String mapping(){
        return "mapping";
    }
}
