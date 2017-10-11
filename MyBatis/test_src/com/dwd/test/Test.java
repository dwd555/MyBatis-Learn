package com.dwd.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

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
	public static void selectUserByID() {
		SqlSession session=sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);
			User user=userOperation.selectUserByID(1);
			System.out.println(user);
		} finally {
			session.close();
		}
		
	}
	public static void selectAllUser() {
		SqlSession session=sqlSessionFactory.openSession();
		try {
			IUserOperation userOperation=session.getMapper(IUserOperation.class);
			List<User> users=userOperation.selectAllUser();
			for (User user:users) {
				System.out.println(user);
			}
		} finally {
			session.close();
		}
		
	}
	/**
     * 测试增加,增加后，必须提交事务，否则不会写入到数据库.
     */
	public static void insertUser(String userName,String userAge,String userAddress) {
		User user=new User();
		user.setUserName(userName);
		user.setUserAge(userAge);
		user.setUserAddress(userAddress);
		SqlSession session=sqlSessionFactory.openSession();
		try {	
			IUserOperation userOperation=session.getMapper(IUserOperation.class);
			userOperation.insertUser(user);
			session.commit();
			System.out.println("当前增加的用户 id为:"+user.getId());
		} finally {
			session.close();
		}
		
	}
	/**
     * 测试修改,修改后，必须提交事务，否则不会写入到数据库.
     */
	public static void updateUser() {
		SqlSession session=sqlSessionFactory.openSession();
		try {	
			IUserOperation userOperation=session.getMapper(IUserOperation.class);
//			先获取id为1的对象,再进行修改
			User user=userOperation.selectUserByID(1);
			user.setUserName("dwd111");
			userOperation.updateUser(user);
			session.commit();
		} finally {
			session.close();
		}
	}
    /**
     * 删除数据，删除一定要 commit.
     * @param id
     */
    public static void deleteUser(int id){
        SqlSession session = sqlSessionFactory.openSession();
        try {
            IUserOperation userOperation=session.getMapper(IUserOperation.class);           
            userOperation.deleteUser(id);
            session.commit();            
        } finally {
            session.close();
        }
    }
	
	public static void main(String[] args) {
//		selectUserByID();
//		insertUser("dwd333", "26", "chancheng");
//		updateUser();
//		deleteUser(1);
		selectAllUser();
		
	}
}
