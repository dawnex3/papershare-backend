package com.itheima.token;

import com.itheima.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 验证拦截器
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Autowired
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
//        查看请求头中是否存在token
        String token = request.getHeader("Authorization");
        if(!(handle instanceof HandlerMethod)){
            return true;
        }
        if(token != null){
            token = token.substring(7);
            Integer user_id = TokenUtil.getUserIdByToken(token);
            // 获得用户id 查询密码
            boolean result = TokenUtil.verify(token, user_id, userService.getPassword(user_id));
            if(result){
                System.out.println("通过拦截器");
                return true;
            }else{
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json;charset=UTF-8");
                response.getWriter().write("{\"code\": 403, \"message\": \"禁止访问\"}");
                response.setStatus(403);
                return false;
            }
        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write("{\"code\": 400, \"message\": \"没有token\"}");
        response.setStatus(400);
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
