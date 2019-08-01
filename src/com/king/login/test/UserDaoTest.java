package com.king.login.test;

import com.king.login.dao.UserDao;
import com.king.login.domain.User;
import org.junit.Test;

/**
 * @ProjectName: JavaEE
 * @Package: com.king.login.test
 * @ClassName: UserDaoTest
 * @Author: 王团结
 * @Description: 测试类
 * @Date: 2019/8/1 18:35
 * @Version: 1.0
 */
public class UserDaoTest {

    @Test
    public void testLogin(){

        User user =new User();
        user.setUsername("wang");
        user.setPassword("123456");

        UserDao dao=new UserDao();
        User login = dao.login(user);

        System.out.println(login);
    }
}
