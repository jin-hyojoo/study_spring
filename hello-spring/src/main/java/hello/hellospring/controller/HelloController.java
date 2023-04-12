package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){

        model.addAttribute("data", "SpringBoot!");

        //Thymeleaf 템플릿 엔진으로 return templates/hello.html을 의미
        return "hello";
    }

    // RequestParam의 required 속성 기본값은 true (값 넘겨야 함)
    // http://localhost:8080/hello-mvc?name=뇨주
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model) {
        model.addAttribute("name", name);
        return "hello-template";
    }


    // http://localhost:8080/hello-string?name=문자그대로 가랏
    @GetMapping("hello-string")
    @ResponseBody   // http header-body 부분 중 body부분에 해당 데이터를 직접 넣겠다
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    // ** JSON 방식 **
    // http://localhost:8080/hello-api?name=API_TEST
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi (@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

}
