package top.hlxuan.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import top.hlxuan.domain.User;
import top.hlxuan.service.UserService;
import top.hlxuan.serviceImpl.UserServiceImpl;
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.设置编码
		req.setCharacterEncoding("utf-8");
		
		// 2.获取数据
		
		// 获取用户输入的验证码
		String verifycode = req.getParameter("verifycode");
		
		// 3.验证码校验
		HttpSession session = req.getSession();
		String checkCode_server = (String) session.getAttribute("CHECKCODE_SERVER");
		
		// 确保验证码一次性有效
		session.removeAttribute("CHECKCODE_SERVER");
		
		// 如果验证码校验不通过
		if(!verifycode.equalsIgnoreCase(checkCode_server)) {
			// 提示信息
			req.setAttribute("login_msg", "验证码错误！");
			//跳转到登录页面
			req.getRequestDispatcher("/login.jsp").forward(req, resp);
		}
		// 如果验证码校验通过
		else {
			Map<String, String[]> map = req.getParameterMap();
			
			// 提供user对象来封装请求参数
			User user = new User();
			
			try {
				BeanUtils.populate(user, map);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			// 5. 调用service查询
			UserService service = new UserServiceImpl();
			User loginUser = service.login(user);
			
			// 6.判断是否登录成功
			
			// 登录成功
			if(loginUser != null) {
				
				// 将登录信息存入session
				session.setAttribute("user", loginUser);
				
				// 记录上次登录的信息
				Cookie[] cookies = req.getCookies();
				boolean flag = false;
				
				// 查询cookies里面的信息
				if(cookies != null && cookies.length>0) {
					// 遍历查询
					for(Cookie cookie : cookies) {
						String name = cookie.getName();
						// 上次登录时间
						if(name.equals("lastTime")) {
							flag = true;
							// 获取cookie的值
							String pri_date = cookie.getValue();
							pri_date = URLDecoder.decode(pri_date, "utf-8");
							// 将数据存入session
							session.setAttribute("lastTime", pri_date);
							
							// 存入新的时间
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							String str_date = sdf.format(date);
							str_date = URLEncoder.encode(str_date, "utf-8");
							cookie.setValue(str_date);
							// 设置cookie的有效期（一个月）
							cookie.setMaxAge(60*60*24*30);
							resp.addCookie(cookie);
						}
						// 上次登录用户
						if(name.equals("lastUser")) {
							flag = true;
							// 获取cookie的值
							//String lastUser = cookie.getValue();
							//lastUser = URLDecoder.decode(lastUser, "utf-8");
							
							// 换新的信息存储到cookie中
							String username = loginUser.getUsername();
							username = URLEncoder.encode(username, "utf-8");
							cookie.setValue(username);
							// 设置cookie的有效期（一个月）
							cookie.setMaxAge(60*60*24*30);
							resp.addCookie(cookie);
						}
					}
				}
				
				// 如果用户是首次访问
				if(cookies == null || cookies.length == 0 || flag == false) {
					
					// 记录上次登录时间到cookie
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String str_date = sdf.format(date);
					str_date = URLEncoder.encode(str_date, "utf-8");
					Cookie cookie = new Cookie("lastTime", str_date);
					// 设置cookie的有效期（一个月）
					cookie.setMaxAge(60*60*24*30);
					resp.addCookie(cookie);
					
					// 记录上次登录的用户到cookie
					String Username = loginUser.getUsername();
					Cookie cookie2 = new Cookie("lastUser",Username);
					// 设置cookie的有效期（一个月）
					cookie2.setMaxAge(60*60*24*30);
					resp.addCookie(cookie2);
					
				}
				
				// 跳转到index.jsp页面
				resp.sendRedirect(req.getContextPath()+"/index.jsp");
				
			}
			// 登录失败
			else {
				// 提示信息
				req.setAttribute("login_msg", "用户名或密码错误！");
				// 转发回login.jsp
				req.getRequestDispatcher("/login.jsp").forward(req, resp);
			}
			
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
