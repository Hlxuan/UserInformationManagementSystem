package top.hlxuan.service;

import java.util.List;

import top.hlxuan.domain.User;

public interface UserService {

	public List<User> findAll();

	public User login(User user);

	public void deleteUser(String id);

	public User findUserById(String id);

	public void UpdateUser(User user);

	public void AddUser(User user);

	public User SearchUser(User user);
	
}
