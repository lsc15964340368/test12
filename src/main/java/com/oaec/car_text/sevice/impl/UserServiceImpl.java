package com.oaec.car_text.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oaec.car_text.dao.LogMapper;
import com.oaec.car_text.dao.PowerMapper;
import com.oaec.car_text.dao.UserMapper;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.entity.userrole;
import com.oaec.car_text.sevice.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    LogMapper logMapper;
    @Autowired
    PowerMapper powerMapper;
    @Override
    public int addUser(User user) {
      String salt=  RandomStringUtils.randomNumeric(8);
      Md5Hash md2=new Md5Hash(salt);
        if (user.getPassword()!=null) {
            Md5Hash md = new Md5Hash(user.getPassword(), salt);
            user.setPassword(md.toString());
        }
        userrole u=new userrole();
        user.setUserstatus("普通用户");
        user.setId(md2.toString());
        user.setSalt(salt);
        u.setRoleid("3");
        u.setUserid(user.getId());
        System.out.println(user);
        u.setUrid(UUID.randomUUID().toString());
        userMapper.adduserrole(u);
        return userMapper.addUser(user);
    }

    @Override
    public User queryByName(String username) {
        QueryWrapper<User> qw=new QueryWrapper<User>();
        qw.eq("usercode",username);
        return userMapper.selectOne(qw);
    }

    @Override
    public List<User> queryAll() {
        return userMapper.queryAll();
    }

    @Override
    public List<User> queryLike(String m) {
        return userMapper.queryLike(m);
    }
    @Override
    public User queryUser(String user_code, String password) {
        return userMapper.queryUser(user_code, password);
    }

    @Override
    public boolean queryByUsername(String username) {
        QueryWrapper<User> qw=new QueryWrapper<User>();
        qw.eq("username", username);
        User user = userMapper.selectOne(qw);
        if(user!=null){
            return false;
        }
        return true;
    }

    @Override
    public boolean username(String user_code) {
        User user = userMapper.username(user_code);
        if (user==null){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public boolean phone(String phone) {
        User user = userMapper.phone(phone);
        if(user!=null){
            return false;
        }else{
            return true;
        }

    }



    @Override
    public int setUser(User user) {
        logMapper.setlogusername(user.getUsername(),user.getUserphone(),user.getUseraddress(),user.getId());
        return userMapper.updateById(user);
    }

    @Override
    public int deleteuser(String  id) {
        return userMapper.deleteuser(id);
    }

    @Override
    public int setUserStatus(String id, String userstatus) {
        List<Role> roles = powerMapper.roleByName(userstatus);
        String roleid=null;
        for (Role r:roles
             ) {
           roleid=r.getRoleid();
        }
        userMapper.setuserrole(id, roleid);
        return userMapper.setUserStatus(id, userstatus);
    }

    @Override
    public List<User> queryU(String id) {
        return userMapper.queryU(id);
    }

    @Override
    public int setpwd(String id, String password) {
        return userMapper.setpwd(id, password);
    }

    @Override
    public User queryUserByName(String usercode) {
        String username = userMapper.queryusername(usercode);
        return userMapper.queryUserByName(username);
    }


}
