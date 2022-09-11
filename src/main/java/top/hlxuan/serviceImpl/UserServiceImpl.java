package top.hlxuan.serviceImpl;

import java.util.List;

import top.hlxuan.dao.UserDao;
import top.hlxuan.daoImpl.UserDaoImpl;
import top.hlxuan.domain.User;
import top.hlxuan.service.UserService;

public class UserServiceImpl implements UserService {
	
	UserDao dao = new UserDaoImpl();

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return dao.login(user.getUsername(), user.getPassword());
	}

	@Override
	public void deleteUser(String id) {
		// TODO Auto-generated method stub
		dao.delete(Integer.parseInt(id));
	}

	@Override
	public User findUserById(String id) {
		// TODO Auto-generated method stub
		return dao.findById(Integer.parseInt(id));
	}

	@Override
	public void UpdateUser(User user) {
		// TODO Auto-generated method stub
		dao.update(user);
	}

	@Override
	public void AddUser(User user) {
		// TODO Auto-generated method stub
		dao.add(user);
	}

	@Override
	public User SearchUser(User user) {
		// TODO Auto-generated method stub
		return dao.searchUser(user.getName(),user.getAddress(),user.getEmail());
	}
	
}
