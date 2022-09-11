package top.hlxuan.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.jni.User;
@WebFilter("/*")
public class LoginFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain filterChain)
			throws IOException, ServletException {
		
		req.setCharacterEncoding("utf-8");
		HttpServletRequest request = (HttpServletRequest)req;
		String uri = request.getRequestURI();
		
		// 判断是否符合放行条件
		if(uri.contains("/login.jsp") || 
			uri.contains("loginServlet") || 
			uri.contains("checkCodeServlet") || 
			uri.contains("/css") || 
			uri.contains("/js") || 
			uri.contains("/fonts")) {
			// 放行
			filterChain.doFilter(req, resp);
		}else {
			Object user = request.getSession().getAttribute("user");
			if(user != null && !user.equals("")) {
				// 放行
				filterChain.doFilter(req, resp);
			}else {
				req.setAttribute("login_msg", "请您先登录后再操作！");
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}

	
	
}
