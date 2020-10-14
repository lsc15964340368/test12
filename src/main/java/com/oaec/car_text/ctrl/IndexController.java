package com.oaec.car_text.ctrl;

import com.oaec.car_text.entity.Car;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class IndexController {
    @RequestMapping("/login.html")
    public String loginhtml(){
        return "login";
    }
    @RequestMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/header")
    public String header(){
        return "header";
    }
    @RequestMapping("/public")
    public String public1(){
        return "public";
    }
    @RequestMapping("/menu")
    public String menu(){
        return "menu";
    }
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @RequestMapping("/adduser")
    public String  adduser(){
        return "adduser";
    }
    @RequestMapping(value = "/setuser1")
    public String setuser1(){
        return "setuser";
    }
    @RequestMapping(value = "/setuser2")
    public String setuser2(){
        return "setuser1";
    }
    @RequestMapping("/addU")
    public String addU(){
        return "addU";
    }
    @RequestMapping("/addcar1")
    public String addcar1(Car car) {
        return "addcar";
    }
    @RequestMapping(value = "/setcar1")
    public String setcar1(){
        return "setcar";
    }
    @RequestMapping("/addlog1")
    public String addlog1(){
        return "addlog";
    }
    @RequestMapping("/addlog_l1")
    public String addlog_l1(){
        return "addlog_l";
    }
    @RequestMapping(value = "/setlog1")
    public String setlog1(){
        return "setlog";
    }
    @RequestMapping(value = "/setlog_l1")
    public String setlog_l1(){
        return "setlog_l";
    }
    @RequestMapping("/addRole1")
    public String addRole1(){
        return "addRole";
    }
}
