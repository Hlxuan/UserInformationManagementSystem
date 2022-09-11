package top.hlxuan.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.hlxuan.domain.User;
import top.hlxuan.service.UserService;
import top.hlxuan.serviceImpl.UserServiceImpl;
@WebServlet("/userListServlet")
public class UserListServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1.调用userService完成数据库查询
		UserService sercice = new UserServiceImpl();
		List<User> users = sercice.findAll();
		
		// 2.将list存入到request域
		req.setAttribute("users", users);
		
		// 3.转发到list.jsp
		req.getRequestDispatcher("/list.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
}
