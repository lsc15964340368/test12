package com.oaec.car_text.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.entity.userrole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    public List<User> queryAll();
    //    查询用户
    public User queryUser(String user_code, String password);
    //    添加用户
    public int addUser(User user);
    //检测用户名是否存在
    public User username(String user_code);
    //模糊查询
    public List<User> queryLike(String m);
    //删除用户
    public int deleteuser(String id);
    //检测手机号是否存在
    public User phone(String phone);
    //修改用户类型
    public int setUserStatus(@Param("id") String id, @Param("userstatus") String userstatus);
    //查看个人信息
    public List<User> queryU(String id);
    //修改密码
    public int setpwd(@Param("id") String id,@Param("password") String password);
    public User queryUserByName(String username);
    public String queryusername(String usercode);
    public int adduserrole(userrole userrole);
    public int setuserrole(@Param("userid") String userid,@Param("roleid") String roleid);
}
