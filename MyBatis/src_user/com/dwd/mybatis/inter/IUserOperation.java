package com.dwd.mybatis.inter;

import java.util.List;

import com.dwd.mybatis.model.User;

//接口中每个方法名必须与User.xml中的id一致
public interface IUserOperation {
	public User selectUserByID(int id);
	public List<User> selectAllUser();
	public void insertUser(User user);
	public void updateUser(User user);
	public void deleteUser(int id);
}
