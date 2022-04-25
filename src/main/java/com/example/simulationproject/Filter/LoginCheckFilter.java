package com.example.simulationproject.Filter;

import com.alibaba.fastjson.JSON;
import com.example.simulationproject.common.BaseContext;
import com.example.simulationproject.common.R;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//check if user already login
@WebFilter(filterName = "loginCheckFilter", urlPatterns = "/*")
public class LoginCheckFilter implements Filter {
    public static final AntPathMatcher PATH_MATCHER= new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        HttpServletResponse response= (HttpServletResponse) servletResponse;

        //get uri
        String requestURI = request.getRequestURI();

        //check which resource doesnt need user to login
        String [ ]  urls= new String[]{
          "/employee/login","/employee/logout","/backend/**","front/**"
        };
        //check if this url need to be processed
        boolean check = check(urls, requestURI);

        if(check){
            filterChain.doFilter(request,response);
            return;
        }

        //check if user login

        //already login
        if(request.getSession().getAttribute("employee")!=null){
            filterChain.doFilter(request,response);
            return;
        }
        //here we can get the thread id
        Long employee = (Long) request.getSession().getAttribute("employee");
        BaseContext.setCurrentId(employee);

        //not login
        //send response to client
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));
        return;

    }

    //check if the request needs login
    public boolean check(String[] urls, String requestURI){
        for (String url : urls) {
            boolean match = PATH_MATCHER.match(url, requestURI);
            if(match){
                return true;
            }

        }
        return false;
    }
}
