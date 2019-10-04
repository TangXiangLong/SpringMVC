package exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SysExceptionResolver implements HandlerExceptionResolver {

    @Override
    //处理异常业务逻辑
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        //获取到异常对象
        SysException ex = null;
        if(e instanceof SysException){
            ex = (SysException)e;
        }else {
            ex = new SysException("系统正在维护...");
        }
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();
        mv.addObject("errorMsg",ex.getMessage());
        mv.setViewName("error");
        return mv;
    }

}
