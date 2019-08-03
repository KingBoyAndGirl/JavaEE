package com.king.login.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @ProjectName: JavaEE
 * @Package: com.king.login.util
 * @ClassName: JDBCUtils
 * @Author: 王团结
 * @Description: JDBC工具类 使用DURID连接池
 * @Date: 2019/8/1 18:16
 * @Version: 1.0
 */
public class JDBCUtils {
    private static DataSource ds;

    static {
        try {
            //1.加载配置文件
            Properties properties = new Properties();
            //使用ClassLoader加载配置文件，获取字节输入流
            InputStream resourceAsStream = JDBCUtils.class.getClassLoader().getResourceAsStream("dbconfig.properties");
            properties.load(resourceAsStream);

            //2.初始化连接池对象
            ds = DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 获取连接池对象
     */
    public static DataSource getDataSource(){
        return ds;
    }
    /**
     * 获取连接Connection对象
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
