package com.elite.interceptor;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.elite.vo.SessionVO;

public class AuthInterceptor extends HandlerInterceptorAdapter{

	 // preHandle() : 컨트롤러보다 먼저 수행되는 메서드
    @Override
    public boolean preHandle(HttpServletRequest request, 
    		HttpServletResponse response, Object handler)
            throws Exception {
        
    	// session 객체를 가져옴
        HttpSession session = request.getSession();
        
        // login처리를 담당하는 사용자 정보를 담고 있는 객체를 가져옴
        SessionVO svo = (SessionVO)session.getAttribute("svo");
               
        if ( svo == null ){
            // 로그인이 안되어 있는 상태임으로 로그인 폼으로 다시 돌려보냄(redirect)
        	response.setContentType("text/html; charset=UTF-8");
        	PrintWriter out = response.getWriter();
        	
        	out.print("<script> var result = confirm('로그인이 필요한 페이지입니다. 페이지를 이동하시겠습니까?');");
        	out.print("if(result){ location.href='http://211.63.89.225:9000/daangn/login.carrot'}");
        	out.print("else{ location.href='http://211.63.89.225:9000/daangn/index.carrot'} </script>");
        	
            return false; 
        }
         
        return true;
    }
 
    // 컨트롤러가 수행되고 화면이 보여지기 직전에 수행되는 메서드
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        super.postHandle(request, response, handler, modelAndView);
    }     
}
