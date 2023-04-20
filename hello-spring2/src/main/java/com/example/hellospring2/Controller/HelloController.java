package com.example.hellospring2.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data", "Spring!!");
        return "hello"; //hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
        //localhost:8080/hello-mvc?name=spring
        //hello-mvc로 접속 시 hello-template.html하면을 보여줌
        //@RequestParam("가져올 데이터의 이름") 데이터타입 변수
        //httpServletRequest 객체와 같은 역할을 함
    }

    @GetMapping("hello-string")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
        //@ResponseBody를 사용하면 뷰 리졸버(viewResolver)를 사용하지 않아도 됨.
        //viewResolver 대신에 HttpMessageConverter가 동작함.
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
        //{"name":"~~"} 형식으로 출력됨.
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
