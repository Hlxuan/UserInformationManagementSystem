package top.hlxuan.dao;

import java.util.List;

import top.hlxuan.domain.User;

public interface UserDao {
	
	// 显示用户列表
	public List<User> findAll();

	public User login(String username, String password);

	public void delete(int id);

	public User findById(int parseInt);

	public void update(User user);

	public void add(User user);

	public User searchUser(String name, String address, String email);
	
}
