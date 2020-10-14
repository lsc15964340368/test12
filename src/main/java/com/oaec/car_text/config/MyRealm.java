package com.oaec.car_text.config;


import com.oaec.car_text.entity.Power;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.apache.shiro.util.SimpleByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.Set;

public class MyRealm extends AuthorizingRealm {
    @Autowired
    UserService userService;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info =new SimpleAuthorizationInfo();
        User user= (User) principalCollection.getPrimaryPrincipal();
        String username = user.getUsercode();
        User users = userService.queryUserByName(username);
        Set<String> newRoles=new HashSet<String>();
        Set<String> newPower=new HashSet<String>();
        Set<Role> roles = users.getRoles();
        if (roles!=null&&roles.size()>0){
            for (Role r:roles
                 ) {
                newRoles.add(r.getRolecode());
                Set<Power> powers=r.getPowers();
                if(powers!=null&&powers.size()>0){
                    for(Power power:powers){
                        newPower.add(power.getPowerpid());
                    }
                }
            }
        }
        info.addStringPermissions(newPower);
        info.addRoles(newRoles);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
       UsernamePasswordToken token= (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        User user = userService.queryByName(username);
        if(user==null){
            return null;
        }else{
            ByteSource bs=new SimpleByteSource(user.getSalt());
            SimpleAuthenticationInfo info=
                    new SimpleAuthenticationInfo(user,user.getPassword(),bs,getName());
            return info;
        }
    }
}
