package com.king.login.domain;

/**
 * @ProjectName: JavaEE
 * @Package: com.king.login.domain
 * @ClassName: User
 * @Author: 王团结
 * @Description: 用户实体类
 * @Date: 2019/8/1 18:07
 * @Version: 1.0
 */
public class User {
    private Integer id;
    private String  userName;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUsername(String username) {
        this.userName = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
