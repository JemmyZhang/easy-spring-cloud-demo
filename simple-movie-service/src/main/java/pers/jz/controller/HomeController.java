package pers.jz.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jemmyzhang on 2018/2/22.
 */
@RestController
public class HomeController {

    @GetMapping(value = {"/", "/home"})
    public String home() {
        return "Movie Home!";
    }

    @GetMapping("/movies")
    public List<String> movies() {
        List<String> list = new ArrayList<>();
        list.add("Iron Man 1");
        list.add("Iron Man 2");
        list.add("Iron Man 3");
        list.add("Captain America");
        return list;
    }
}
