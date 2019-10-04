package controller;


import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/anno")
@SessionAttributes(value = {"msg"})  //把msg=美美存到session域中
public class AnnoController {


    @RequestMapping("/testRequestParam")
    public String testRequest(@RequestParam(name="name") String username){
        System.out.println("执行了...");
        System.out.println(username);
        return "success";
    }

    /*获取请求体内容*/
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody String body){
        System.out.println("执行了...");
        System.out.println(body);
        return "success";
    }



    /*PathVariable注解*/
    @RequestMapping(value = "/testPathVariable/{sid}",method = RequestMethod.POST)
    public String testPathVariable(@PathVariable(name="sid") String body){
        System.out.println("执行了...");
        System.out.println(body);
        return "success";
    }


    /*获取请求头信息*/
    @RequestMapping(value = "/testRequestHeader")
    public String testRequestHeader(@RequestHeader(value="Accept") String header){
        System.out.println("执行了...");
        System.out.println(header);
        return "success";
    }

    /*获取请求头信息*/
    @RequestMapping(value = "/testCookieValue")
    public String testCookieValue(@CookieValue(value = "JSESSIONID") String cookieValue){
        System.out.println("执行了...");
        System.out.println(cookieValue);
        return "success";
    }


    /*获取请求头信息*/
    @RequestMapping(value = "/testModelAttribute")
    public String testModelAttribute(@ModelAttribute("abc") User user){
        System.out.println("执行了...");
        System.out.println(user);
        return "success";
    }

    @ModelAttribute
    public void showUser(String uname, Map<String,User> map){
        System.out.println("showUser执行了...");
        //通过用户名查数据库（模拟）
        User user = new User();
        user.setUname(uname);
        user.setAge(20);
        user.setDate(new Date());
        map.put("abc",user);
    }

    /*该方法会先执行*/
/*    @ModelAttribute
    public User showUser(){
        System.out.println("showUser执行了...");
        //通过用户名查数据库（模拟）
        User user = new User();
        user.setDate(new Date());
        return user;
    }*/



    /*SessionAttrivutes*/
    @RequestMapping(value = "/testSessionAttributes")
    public String testSessionAttributes(ModelMap modelMap){
        System.out.println("SessionAttributes...");
        modelMap.addAttribute("msg","美美");
        String msg = modelMap.get("msg").toString();
        System.out.println(msg);
        return "success";
    }


    /*删除Session*/
    @RequestMapping(value = "/deleteSessionAttributes")
    public String deleteSessionAttributes(SessionStatus status){
        System.out.println("deleteSessionAttributes...");
        status.setComplete();
        return "success";
    }
}
