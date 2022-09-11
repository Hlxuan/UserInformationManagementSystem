package top.hlxuan.daoImpl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import top.hlxuan.dao.UserDao;
import top.hlxuan.domain.User;
import top.hlxuan.utils.JDBCUtils;

public class UserDaoImpl implements UserDao {
	
	private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		
		String sql = "select * from user";
		
		List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));
		
		return users;
	}

	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user where username = ? and password = ?";
			User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),username,password);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void delete(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from user where id = ?";
		template.update(sql,id);
	}

	@Override
	public User findById(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from user where id = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),id);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		String sql = "update user set name = ? , gender = ? , age = ? , address = ? , qq = ? , email = ? where id = ?";
		template.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
	}

	@Override
	public void add(User user) {
		// TODO Auto-generated method stub
		String sql = "insert into user values(null,?,?,?,?,?,?,null,null)";
		template.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
	}

	@Override
	public User searchUser(String name, String address, String email) {
		// TODO Auto-generated method stub
		try {
			String sql = "select * from user where name = ? or address = ? or email = ?";
			User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class),name,address,email);
			return user;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	

}
