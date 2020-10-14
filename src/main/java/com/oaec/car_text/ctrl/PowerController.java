package com.oaec.car_text.ctrl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oaec.car_text.entity.Logl;
import com.oaec.car_text.entity.Power;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.PowerService;
import com.oaec.car_text.sevice.UserService;
import org.json.JSONException;
import org.json.JSONObject;
import com.oaec.car_text.entity.powerrole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class PowerController {
    @Resource
    private PowerService powerService;
    @Autowired
    UserService userService;
    @RequestMapping("/queryRole")
    public ModelAndView queryRole(ModelAndView mv,
                                  @RequestParam(required=true,defaultValue="1") Integer page,
                                  @RequestParam(required=false,defaultValue="5") Integer pageSize){
        PageHelper.startPage(page, pageSize);
        List<Role> roleList = powerService.queryRole();
        PageInfo<Role> p = new PageInfo<Role>(roleList);
        mv.addObject("roleList", roleList);
        mv.addObject("page4", p);
        mv.setViewName("role");
        return mv;
    }
    @RequestMapping("/deleteRole")
    @ResponseBody
    public String deleteRole(String roleid) throws JSONException {
        JSONObject json=new JSONObject();
        int i = powerService.deleteRole(roleid);
        if(i!=0){
            json.put("success",true);
        }
    return json.toString();
    }

    @RequestMapping("/addRole")
    @ResponseBody
    public String addRole(String rolename,int  rolecode) throws JSONException {
        JSONObject json=new JSONObject();
    int i = powerService.addRole(rolename, rolecode);
    if (i!=0){
        json.put("success", true);
    }
    return json.toString();
}
    @RequestMapping("/role_name")
    @ResponseBody
    public String role_name(String rolename) throws JSONException {
        JSONObject json=new JSONObject();
        List<Role> list = powerService.roleByName(rolename);
        if (list.size()==0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/querypower")
    public ModelAndView querypower(String roleid,ModelAndView mv,
                                   HttpServletRequest req,@RequestParam(required=true,defaultValue="1") Integer page,
                                   @RequestParam(required=false,defaultValue="30") Integer pageSize){
        PageHelper.startPage(page, pageSize);
//        User user1 = (User) req.getSession().getAttribute("user");
//        User user=userService.queryUserByName(user1.getUsercode());
//        Set<Role> roles=user.getRoles();
//        Set<Power> powers=new HashSet<Power>();
//        if(roles!=null&&roles.size()>0){
//            for (Role role:roles
//            ) {
//                Set<Power> powerSet=role.getPowers();
//                if(powerSet!=null&&powerSet.size()>0){
//                    for(Power p:powerSet){
//                        powers.add(p);
//                    }
//                }
//            }
//        }
        List<Power> powers1 = powerService.queryPower(roleid);
        List<String> powerid=new ArrayList<String>();
        for (Power p:powers1
        ) {
            powerid.add(p.getPowerid());
        }
        req.getSession().setAttribute("rid", roleid);
        req.getSession().setAttribute("powerid", powerid);
        List<Power> powers = powerService.queryAllPower();
        List<Power> power1=new ArrayList<Power>(powers);
        PageInfo<Power> p = new PageInfo<Power>(power1);
        mv.addObject("powers1", powers);
        mv.addObject("page5", p);
        mv.setViewName("power");
        return mv;
    }
@RequestMapping("/addpower")
@ResponseBody
    public String addpower(String powerid,HttpServletRequest req) throws JSONException {
        JSONObject js=new JSONObject();
   powerrole p=new powerrole();
   p.setPowerid(powerid);
    String  rid = (String) req.getSession().getAttribute("rid");
    p.setRoleid(rid);
    int addpower = powerService.addpower(p);
    if(addpower!=0) {
        js.put("success", true);
    }
    return js.toString();
}
    @RequestMapping("/deletepower")
    @ResponseBody
    public String deletepower(String powerid,HttpServletRequest req) throws JSONException {
        JSONObject js=new JSONObject();
        String  rid = (String) req.getSession().getAttribute("rid");
        int addpower = powerService.deletepower(powerid,rid);
        if(addpower!=0) {
            js.put("success", true);
        }
        return js.toString();
    }
}
