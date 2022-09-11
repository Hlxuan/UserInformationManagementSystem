package top.hlxuan.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import top.hlxuan.domain.User;
import top.hlxuan.service.UserService;
import top.hlxuan.serviceImpl.UserServiceImpl;
@WebServlet("/SearchUserServlet")
public class SearchUserServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1、设置编码
		req.setCharacterEncoding("utf-8");
				
		// 2、获取表单数据
		Map<String, String[]> map = req.getParameterMap();
		
		// 提供user对象作为请求参数的容器
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
		
		// 3、调用service查询
		UserService service = new UserServiceImpl();
		User users = service.SearchUser(user);
		
		// 4、将user存入request域
		req.setAttribute("users", users);
		
		// 5、转发到list.jsp
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
