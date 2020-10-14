package com.oaec.car_text.ctrl;

import com.oaec.car_text.entity.Power;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

@Controller
public class SysController {
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    @ResponseBody
    public String login(String usercode, String password, HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();

        try {
            Subject subject= SecurityUtils.getSubject();
            AuthenticationToken token = new UsernamePasswordToken(usercode,password);
            subject.login(token);
            User user=userService.queryUserByName(usercode);
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
            req.getSession().setAttribute("powers",powers);
            json.put("success", "true");
            User user1 = userService.queryByName(usercode);
            req.getSession().setAttribute("user", user1);
            return json.toString();
        }catch (UnknownAccountException e){
            json.put("success", "false");
            return json.toString();
        }catch (Exception e){
            System.out.println(e);
            json.put("error", "true");
            return json.toString();
        }
    }

}
