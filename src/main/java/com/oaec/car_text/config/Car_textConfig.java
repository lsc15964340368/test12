package com.oaec.car_text.config;

import com.oaec.car_text.entity.Power;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.UserService;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

@Configuration
public class Car_textConfig {
    @Autowired
    UserService userService;
    @Bean
    public ShiroFilterFactoryBean factoryBean(HttpServletRequest req){
        ShiroFilterFactoryBean shiroFilter=new ShiroFilterFactoryBean();
        User user=userService.queryUserByName("1151011819");
        Set<Role> roles=user.getRoles();
        Set<Power> powers=new HashSet<Power>();
        if(roles!=null&&roles.size()>0){
            for (Role role:roles
            ) {
                Set<Power> powerSet=role.getPowers();
                if(powerSet!=null&&powerSet.size()>0){
                    for(Power p:powerSet){
                        powers.add(p);
                    }
                }
            }
        }
        shiroFilter.setLoginUrl("login.html");
        Map map=new LinkedHashMap();
        map.put("/login.html", "anon");
        map.put("/login", "anon");
        map.put("*/static/*","anon");
        map.put("/index","anon");
        map.put("/upload", "anon");
        map.put("/upload1", "anon");
        map.put("/pwd", "anon");
        map.put("/username","anon");
        map.put("/reusername", "anon");
        map.put("/querypower","role[1],role[2]");
        for(Power p:powers){
            String url = p.getURL();
            String powerdid=p.getPowerpid();
            map.put(url, "perms["+powerdid+"]");

      }
        /*anon放行，不拦截*/
        map.put("/*", "authc");
        /*authc拦截，需要认证*/
        shiroFilter.setFilterChainDefinitionMap(map);
        shiroFilter.setSecurityManager(securityManager());
        return shiroFilter;
    }
    /*安全管理*/
    @Bean
    public SecurityManager securityManager(){
        DefaultWebSecurityManager sm=new DefaultWebSecurityManager();
       sm.setRealm(myRealm());
        return sm;
    }
@Bean
    public Realm myRealm(){
        MyRealm myRealm=new MyRealm();
        myRealm.setCredentialsMatcher(credentialsMatcher());
        return myRealm;
}
public CredentialsMatcher credentialsMatcher(){
    HashedCredentialsMatcher macher=
            new HashedCredentialsMatcher();
    macher.setHashAlgorithmName("md5");
    return macher;
}
}
