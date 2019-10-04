package controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(path="/user")
public class HelloController {

    @RequestMapping(path="hello")
    public String sayHello(){
        System.out.println("Hello SpringMVC");
        return "success";
    }


    @RequestMapping(path="testRequestMapping",params = {"username"},headers = {"Accept"})
    public String testRequestMapping(){
        System.out.println("测试RequesMapping");
        return "success";
    }
}
