package com.oaec.car_text.ctrl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oaec.car_text.entity.Role;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.PowerService;
import com.oaec.car_text.sevice.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class UserController {
@Autowired
    UserService userService;
@Autowired
    PowerService powerService;
    @RequestMapping("/username")
    @ResponseBody
    public String username(String user_code) throws JSONException {
        JSONObject json=new JSONObject();
        User user = userService.queryByName(user_code);
        if (user==null) {
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/add")
    @ResponseBody
    public String add(User user) throws JSONException {
        JSONObject json=new JSONObject();
        if (user!=null){
        int i=0;
        i= userService.addUser(user);
        if(i!=0){
            json.put("success", true);
        }

        }
        return json.toString();
    }
    @RequestMapping("/relogin")
    @ResponseBody
    public String relogin(HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        req.getSession().removeAttribute("user");
        json.put("success", true);
        return json.toString();
    }
    @RequestMapping("/queryAllUser")
    public ModelAndView queryAll(String m, HttpServletRequest req, ModelAndView mv,
                                 @RequestParam(required=true,defaultValue="1") Integer page,
                                 @RequestParam(required=false,defaultValue="5") Integer pageSize
    ){
        List<Role> roles = powerService.queryRole();
        req.getSession().setAttribute("r1", roles);
        List<User> list=null;
        PageHelper.startPage(page, pageSize);
        if (m==null) {
            list = userService.queryAll();
        }else {
            list = userService.queryLike(m);
            System.out.println(list);
        }
        PageInfo<User> p = new PageInfo<User>(list);
        mv.addObject("users", list);
        mv.addObject("page", p);
        mv.setViewName("main");
        return  mv;
    }

    @RequestMapping("/phone")
    @ResponseBody
    public String  phone(String phone) throws JSONException {
        JSONObject json = new JSONObject();
        if (phone != null) {
            boolean b = userService.phone(phone);
            json.put("success", b);
        }
        return json.toString();
    }
    @RequestMapping("/adduser1")
    @ResponseBody
    public String adduser1(User user) throws JSONException {
        JSONObject json=new JSONObject();
        int i = userService.addUser(user);
        if(i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping(value = "/setuser")
    @ResponseBody
    public String  setuser(User user,HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        req.getSession().setAttribute("set", user);
        User set = (User) req.getSession().getAttribute("set");
        if (set!=null){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping(value = "/setuser3")
    @ResponseBody
    public String setuser3(User user) throws JSONException {
        JSONObject json=new JSONObject();
        int i = userService.setUser(user);
        if (i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/deleteuser")
    @ResponseBody
    public String  deleteuser(String id,HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        int deleteuser = userService.deleteuser(id);
        User user = (User) req.getSession().getAttribute("user");
        System.out.println(user.getId());
        if(deleteuser!=0){
                json.put("success", true);
        }

        return json.toString();
    }
    @RequestMapping("/set_user_status")
    @ResponseBody
    public String set_user_status(String id,String userstatus) throws JSONException {
        JSONObject json =new JSONObject();
        int i = userService.setUserStatus(id, userstatus);
        if(i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/queryU")
    public ModelAndView queryU(HttpServletRequest req,ModelAndView mv){
        User user = (User) req.getSession().getAttribute("user");
        List<User> users = userService.queryU(user.getId());
        mv.addObject("U", users);
        mv.setViewName("queryU");
        return mv;
    }
    @RequestMapping("/reusername")
    @ResponseBody
    public String reusername(String pwd1,String pwd2) throws JSONException {
        JSONObject json=new JSONObject();
        if (pwd1.equals(pwd2)){
            json.put("success",true);
        }
        return json.toString();
    }
    @RequestMapping("/pwd")
    @ResponseBody
    public String pwd(String pwd,HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        User user = (User) req.getSession().getAttribute("user");
        Md5Hash md=new Md5Hash(pwd,user.getSalt());
        if (user.getPassword().equals(md.toString())){
            json.put("success", true);
        }
        return json.toString();
    }

    @RequestMapping("/setpwd")
    @ResponseBody
    public String setpwd(HttpServletRequest req,String pwd) throws JSONException {
        JSONObject json=new JSONObject();
        User user = (User) req.getSession().getAttribute("user");
        Md5Hash md=new Md5Hash(pwd,user.getSalt());
        int i = userService.setpwd(user.getId(), md.toString());
        if(i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/querybyusername")
    @ResponseBody
    public String  querybyusername(String username) throws JSONException {
        JSONObject json = new JSONObject();
        if (username!= null) {
            boolean b = userService.queryByUsername(username);
            json.put("success", b);
        }
        return json.toString();
    }
}
