package controller;


import domain.User;
import exception.SysException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("/testString")
    public String testString(Model model){
        System.out.println("testString方法执行了...");
        // 模拟从数据库中查询到了对象
        User user = new User();
        user.setUname("美美");
        user.setAge(30);
        user.setDate(new Date(0));
        model.addAttribute("user",user);
        return "success";
    }

    /*请求转发一次请求，不用编写项目名称*/
    @RequestMapping("/testVoid")
    public void testVoid(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("testVoid方法执行了...");
        // 编写请求转发程序
//        request.getRequestDispatcher("/WEB-INF/pages/success.jsp").forward(request,response);
        //重定向
//        response.sendRedirect(request.getContextPath()+"/index.jsp");


        //设置中文乱码
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //直接进行响应
        response.getWriter().print("你好");
        return;
    }

    @RequestMapping("/testModelAndView")
    public ModelAndView testModelAndView(Model model){
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        System.out.println("testModelAndView方法执行了...");
        // 模拟从数据库中查询到了对象
        User user = new User();
        user.setUname("小风");
        user.setAge(29);
        user.setDate(new Date(0));
        //把user对象存储到mv对象中，也会把user对象存入到request对象
        mv.addObject("user",user);
        //跳转到哪个页面
        mv.setViewName("success");
        return mv;
    }
/*使用关键字进行转发或重定向*/
    @RequestMapping("/testForwardOrRedirect")
    public String testForwardOrRedirect(Model model){

        //请求的转发
//        return "forward:/WEB-INF/pages/success.jsp";
        //重定向
        return "redirect:/index.jsp";
    }

    /*模拟异步请求响应*/
    @RequestMapping("/testAjax")
    public @ResponseBody
    User testAjax(@RequestBody User user){
        System.out.println("testAjax方法执行了...");
        //客户端发送ajax的请求，传的是json字符串，后端把json字符串封装到user对象中
        System.out.println(user);
        //做响应，模拟查询数据库
        user.setUname("哈哈");
        user.setAge(40);
        //做相应
        return user;
    }


    @RequestMapping("/testException")
    public String testException() throws SysException {
        System.out.println("执行了..");
        try {
            //模拟异常
            int a = 10/0;
        } catch (Exception e) {
            //控制台打印异常信息
            e.printStackTrace();
            //抛出自定义异常信息
            throw new SysException("查询所有用户出现错误了...");
        }
        return "success";
    }

    @RequestMapping("/testInterceptor")
    public String testInterceptor() {
        System.out.println("Controller执行了..");
        return "success";
    }

}
