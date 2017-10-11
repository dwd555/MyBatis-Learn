package com.dwd.mybatis.inter;

import com.dwd.mybatis.model.User;

public interface IUserOperation {
	public User selectUserByID(int id);
}
