package com.dwd.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.dwd.mybatis.inter.IUserOperation;
import com.dwd.mybatis.model.User;

public class Test {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		String resource = "Configuration.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
	}
	public static void selectUser() {
		SqlSession session=sqlSessionFactory.openSession();
		IUserOperation userOperation=session.getMapper(IUserOperation.class);
		User user=userOperation.selectUserByID(1);
		System.out.println(user);
	}
	public static void main(String[] args) {
		selectUser();
	}
}
