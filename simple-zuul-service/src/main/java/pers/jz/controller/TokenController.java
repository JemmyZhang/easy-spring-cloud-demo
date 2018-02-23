package pers.jz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jemmyzhang on 2018/2/23.
 */
@RestController
public class TokenController {

    @GetMapping({"/","/token"})
    public String tokenGet(){
        return "Token Get!";
    }

    @PostMapping({"/","/token"})
    public String tokenPost(){
        return "Token Post!";
    }
}
