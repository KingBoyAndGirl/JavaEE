package com.king.login.dao;

import com.king.login.domain.User;
import com.king.login.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ProjectName: JavaEE
 * @Package: com.king.login.dao
 * @ClassName: UserDao
 * @Author: 王团结
 * @Description: 操作数据库中User表的类
 * @Date: 2019/8/1 18:13
 * @Version: 1.0
 */
public class UserDao {
    //声明JDBCTemplate对象共用
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 登录方法
     * @param loginUser 只有用户名和密码
     * @return  user包含用户全部数据,没有查询到，返回null
     */
    public User login(User loginUser){
        try {
            //1.编写sql
            String sql="select * from user where username = ? and password = ? ";
            //2.调用query方法
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), loginUser.getUserName(), loginUser.getPassword());
            return user;
        } catch (DataAccessException e) {
            e.printStackTrace();    //记录日志
            return null;
        }
    }
}
