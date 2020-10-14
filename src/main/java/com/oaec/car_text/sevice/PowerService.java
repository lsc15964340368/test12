package com.oaec.car_text.sevice;

import com.oaec.car_text.entity.Power;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.powerrole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PowerService {
    //查询所有角色
    public List<Role> queryRole();
    //删除角色
    public int deleteRole(String roleid);
    //检测角色名
    public List<Role> roleByName(String rolename);
    //添加角色
    public int addRole(String rolename,int rolecode);
    //查看拥有权限
    public List<Power> queryPower(String roleid);
    //查看所有权限
    public List<Power> queryAllPower();
    //添加权限
    public int addpower(powerrole powerrole);
    //取消权限
    public int deletepower(String powerid,String roleid);
}
