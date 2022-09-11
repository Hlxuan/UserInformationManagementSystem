package top.hlxuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.hlxuan.service.UserService;
import top.hlxuan.serviceImpl.UserServiceImpl;
@WebServlet("/DelUserServlet")
public class DelUserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1、获取参数id
		String id = req.getParameter("id");
		
		// 2、调用service
		UserService service = new UserServiceImpl();
		service.deleteUser(id);
		
		// 3、跳转到查询所有数据的servlet
		resp.sendRedirect(req.getContextPath()+"/userListServlet");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	

}
