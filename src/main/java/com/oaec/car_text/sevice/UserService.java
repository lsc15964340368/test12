package com.oaec.car_text.sevice;

import com.oaec.car_text.entity.User;
import com.oaec.car_text.entity.userrole;

import java.util.List;

public interface UserService {
    //    添加用户
    public int addUser(User user);
    public User queryByName(String username);
    //查询所有用户
    public List<User> queryAll();
    //模糊查询
    public List<User> queryLike(String m);
    //    查询用户
    public User queryUser( String user_code, String password);
    //
    public boolean queryByUsername(String username);
    //检测账号是否存在
    public boolean username(String user_code);
    //检测手机号是否存在
    public boolean phone(String phone);
    //修改用户信息
    public int setUser(User user);
    //删除用户
    public int deleteuser(String id);
    //修改用户类型
    public int setUserStatus(String id,String userstatus);
    //查看个人信息
    public List<User> queryU(String id);
    //修改密码
    public int setpwd(String id,String password);
    public User queryUserByName(String usercode);

}
