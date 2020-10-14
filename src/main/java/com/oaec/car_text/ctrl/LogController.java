package com.oaec.car_text.ctrl;
import org.json.JSONException;
import org.json.JSONObject;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oaec.car_text.entity.Car;
import com.oaec.car_text.entity.Log;
import com.oaec.car_text.entity.Logl;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.service.LogService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class LogController {
    @Resource
    private LogService logService;
    @RequestMapping("/querylog")
    @ResponseBody
    public ModelAndView  querylog(String m,ModelAndView mv,HttpServletRequest req,
                                  @RequestParam(required=true,defaultValue="1") Integer page,
                                  @RequestParam(required=false,defaultValue="5") Integer pageSize
                                  ) {
        List<Log> list = null;
        PageHelper.startPage(page, pageSize);
        if (m == null) {
            list = logService.querylog();
        } else {
          list = logService.likelog(m);
        }
        PageInfo<Log> p = new PageInfo<Log>(list);
        mv.addObject("logs", list);
        mv.addObject("page2", p);
        mv.setViewName("log");
        return mv;
    }
    @RequestMapping("/querylog_l")
    public ModelAndView querylog_l(String logid, HttpServletRequest req,ModelAndView mv,
                                   @RequestParam(required=true,defaultValue="1") Integer page,
                                   @RequestParam(required=false,defaultValue="5") Integer pageSize){
        req.getSession().setAttribute("logid", logid);
        List<Logl> list = null;
        PageHelper.startPage(page, pageSize);
        list= logService.querylog_l(logid);
        List<Log> list1 = logService.querylogByLod_id(logid);
        String status="";
        for (Log l:list1
             ) {
            status = l.getLogstatus();
        }
        if(status.equals("已支付")){
            req.getSession().setAttribute("finish", list1);
        }else{
            req.getSession().setAttribute("finish", "");
        }
        PageInfo<Logl> p = new PageInfo<Logl>(list);
        mv.addObject("log_n", list);
        mv.addObject("page3", p);
        mv.setViewName("log_l");
        return mv;
    }
    @RequestMapping("/log_username")
    @ResponseBody
    public String log_username(String logusername) throws JSONException {
        JSONObject json=new JSONObject();
        List<User> list = logService.log_username(logusername);
        if(list!=null&&list.size()!=0){
            json.put("success",true);
        }else{
            json.put("success",false);
        }
        return json.toString();
    }
    @RequestMapping("/addlog_l")
    @ResponseBody
    public String addlog_l(Logl logl,HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        String logid = (String) req.getSession().getAttribute("logid");
        logl.setLogid(logid);
        System.out.println(logid);
        int i = logService.addlog_l(logl);
        if(i!=0&logl.getCarbrand()!=null){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/log_brand")
    @ResponseBody
    public String log_brand(String carbrand) throws JSONException {
        JSONObject json=new JSONObject();
        boolean b = logService.carbybrand(carbrand);
        if(b){
            json.put("success", true);
        }else {
            json.put("success", false);
        }
        return json.toString();
    }
    @RequestMapping("/addlog")
    @ResponseBody
        public String addlog(Log log,HttpServletRequest req) throws JSONException {
            JSONObject json=new JSONObject();
            User user = (User) req.getSession().getAttribute("user");
            log.setLogcreateid(user.getId());
            log.setLogcreatename(user.getUsername());
            User user1 = logService.queryByName(log.getLogusername());
            log.setLoguserphone(user1.getUserphone());
            log.setLoguseraddress(user1.getUseraddress());
            log.setLoguserid(user1.getId());
            int i=logService.addlog(log);
            if(i!=0){
                json.put("success", true);
            }
            return json.toString();
        }
    @RequestMapping(value = "/setlog")
    @ResponseBody
    public String   setlog(Log log, HttpServletRequest req) throws JSONException {
        JSONObject js=new JSONObject();
        req.getSession().setAttribute("setlog", log);
        Log setlog = (Log) req.getSession().getAttribute("setlog");
        if(setlog!=null){
            js.put("success", true);
        }
        return js.toString();
    }
    @RequestMapping(value = "/setlog3")
    @ResponseBody
    public String setlog3(Log log) throws JSONException {
        JSONObject json=new JSONObject();
        int i = logService.setlog(log);
        if (i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping(value = "/setlog_l")
    @ResponseBody
    public String  setlog_l(Logl logl,HttpServletRequest req) throws JSONException {
        JSONObject js=new JSONObject();
        req.getSession().setAttribute("setlog_l", logl);
        Logl setlog_l = (Logl) req.getSession().getAttribute("setlog_l");
        System.out.println(setlog_l);
        if (setlog_l!=null){
            js.put("success",true);
        }
        return js.toString();
    }

    @RequestMapping(value = "/setlog_l3")
    @ResponseBody
    public String setlog_l3(Logl logl) throws JSONException {
        JSONObject json=new JSONObject();
        int i = logService.setlog_l(logl);
        if (i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/deletelog")
    @ResponseBody
    public String  deletelog(String logid) throws JSONException {
        JSONObject json=new JSONObject();
        int delete = logService.delete(logid);
        if(delete!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/deletelog_l")
    @ResponseBody
    public String  deletelog_l(String loglid) throws JSONException {
        JSONObject json=new JSONObject();
        int delete = logService.deletelog_l(loglid);
        if(delete!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/log_finish")
    @ResponseBody
    public String log_finish(HttpServletRequest req) throws JSONException {
        JSONObject json= new JSONObject();
        String  logid = (String) req.getSession().getAttribute("logid");
        List<Logl> list = logService.querylog_l(logid);
        int finish=0;
        int i=0;
        if(list.size()!=0) {
            int price = 0;
            for (Logl l : list) {
                price = price + l.getCarprice() * l.getCarnum();
               i=logService.setByBrand(l.getCarbrand(), l.getCarnum());
            }
            if(i!=0) {
                finish = logService.finish(price, logid);
            }
        }
        if(finish!=0){
                json.put("success", true);
            }

        return json.toString();
    }
}
