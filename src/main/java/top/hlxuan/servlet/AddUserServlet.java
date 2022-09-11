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
@WebServlet("/addUserServlet")
public class AddUserServlet extends HttpServlet {

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
		
		// 3、调用service完成插入操作
		UserService service = new UserServiceImpl();
		service.AddUser(user);
		
		// 4、跳转到查询所有的servlet
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
