package top.hlxuan.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import top.hlxuan.domain.User;
import top.hlxuan.service.UserService;
import top.hlxuan.serviceImpl.UserServiceImpl;
@WebServlet("/FindUserServlet")
public class FindUserServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 1、获取id
		String id = req.getParameter("id");
		
		// 2、调用service查询
		UserService service = new UserServiceImpl();
		User user = service.findUserById(id);
		
		// 3、将user存入request域
		req.setAttribute("user", user);
		
		// 4、转发到update.jsp
		req.getRequestDispatcher("/update.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
}
