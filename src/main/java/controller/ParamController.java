package controller;

import domain.Account;
import domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//请求参数绑定
@Controller
@RequestMapping(path="/param")
public class ParamController {

    @RequestMapping(path="/testParam")
    public String testParam(String username,String password){
        System.out.println("执行了...");
        System.out.println("用户名："+username);
        System.out.println("密码："+password);
        return "success";
    }

    @RequestMapping(path="/saveAccount")
    public String saveAccount(Account account){
        System.out.println(account);
        return "success";
    }


    /*自定义类型转换器*/
    @RequestMapping(path="/saveUser")
    public String saveUser(User user){
        System.out.println("执行了....");
        System.out.println(user);
        return "success";
    }

    /*原生API获取*/
    @RequestMapping(path="/testServlet")
    public String testServlet(HttpServletRequest request, HttpServletResponse response){
        System.out.println("执行了....");
        System.out.println(request);
        HttpSession session = request.getSession();
        System.out.println(session);
        ServletContext servletContext = session.getServletContext();
        System.out.println(servletContext);
        System.out.println(response);
        return "success";
    }
}
