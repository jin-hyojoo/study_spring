package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){

        model.addAttribute("data", "SpringBoot!");

        //Thymeleaf 템플릿 엔진으로 return templates/hello.html을 의미
        return "hello";
    }
}
