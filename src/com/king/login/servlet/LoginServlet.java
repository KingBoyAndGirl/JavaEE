package com.king.login.servlet;

import com.king.login.dao.UserDao;
import com.king.login.domain.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
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
        Map<String, String[]> map = new HashMap<>(request.getParameterMap());
        //3.1先获取生成的验证码
        HttpSession session = request.getSession();
        String checkCode_session = (String) session.getAttribute("checkCode_session");
        //删除session中存储的验证码
        session.removeAttribute("checkCode_session");
        //3.2先判断验证码是否正确
        String[] checkCodes = map.remove("checkCode");
        if(checkCode_session ==null && !checkCode_session.equalsIgnoreCase(checkCodes[0])){
            //验证码不一致
            //存储提示信息到request
            request.setAttribute("cc_error","验证码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }
        //判断用户名和密码是否一致
        //忽略大小写比较

        //4.创建User对象
        User loginUser = new User();
        //4.2使用BeanUtils封装
        try {
            BeanUtils.populate(loginUser,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //5.调用UserDao的login方法
        UserDao userDao = new UserDao();
        User user = userDao.login(loginUser);

        //6.判断user
        if (user==null){
            //登陆失败
            //存储提示信息到request
            request.setAttribute("login_error","用户名或密码错误");
            //转发到登录页面
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //登录成功
            //存储数据
            session.setAttribute("username",user.getUserName());
            //转发
           response.sendRedirect(request.getContextPath()+"/success.jsp");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
