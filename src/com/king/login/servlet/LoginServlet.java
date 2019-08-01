package com.king.login.servlet;

import com.king.login.dao.UserDao;
import com.king.login.domain.User;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @ProjectName: JavaEE
 * @Package: ${PACKAGE_NAME}
 * @ClassName: ${NAME}
 * @Author: 王团结
 * @Description: ${description}
 * @Date: 2019/8/1 20:05
 * @Version: 1.0
 */
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");

        //2.获取所有请求参数
        Map<String, String[]> map = request.getParameterMap();
        //3.创建User对象
        User loginUser = new User();
        //3.2使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //4.调用UserDao的login方法
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        //5.判断user
        if (user==null){
            //登陆失败
            request.getRequestDispatcher("/failServlet").forward(request,response);
        }else{
            //登录成功
            //存储数据
            request.setAttribute("user",user);
            //转发
            request.getRequestDispatcher("/successServlet").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
