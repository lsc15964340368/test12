package com.oaec.car_text.ctrl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oaec.car_text.entity.Car;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.sevice.CarService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class CarController {
    @Resource
    private CarService carService;
    @RequestMapping("/querycar")
    public ModelAndView queryCar(String m, HttpServletRequest req, ModelAndView mv,
                                 HttpServletResponse resp,@RequestParam(required=true,defaultValue="1") Integer page,
                                 @RequestParam(required=false,defaultValue="4") Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Car> list=null;
        if (m==null) {
            list = carService.queryCar();
        }else {
            list = carService.likecar(m);
        }
        PageInfo<Car> p = new PageInfo<Car>(list);
        mv.addObject("car", list);
        mv.addObject("page1", p);
        mv.setViewName("car");
        return  mv;
    }
    @RequestMapping("/addcar")
    @ResponseBody
    public String addcar(Car car,HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        User user = (User) req.getSession().getAttribute("user");
        car.setCarcreateid(user.getId());
        car.setCarcreatename(user.getUsername());
        int i=carService.addCar(car);
        if(i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping("/upload")
    @ResponseBody
    public ModelAndView upload(@RequestParam("file") MultipartFile multipartFile,
                               HttpServletRequest req) throws IOException {
        ModelAndView mav=new ModelAndView("addcar");
        String realPath = "E:\\idea\\untitled\\car_text\\target\\classes\\static\\images";
        String originalFilename = multipartFile.getOriginalFilename();
        UUID uuid= UUID.randomUUID();
        String fileName=uuid+"_"+originalFilename;
        String realFileName=realPath+"/"+fileName;
        mav.addObject("fileName",fileName);
        File file=new File(realFileName);
        multipartFile.transferTo(file);
        return mav;
    }
    @RequestMapping("/upload1")
    @ResponseBody
    public ModelAndView upload1(@RequestParam("file") MultipartFile multipartFile,
                               HttpServletRequest req) throws IOException {
        ModelAndView mav=new ModelAndView("setcar");
        String realPath = "E:\\idea\\untitled\\car_text\\target\\classes\\static\\images";
        String originalFilename = multipartFile.getOriginalFilename();
        UUID uuid= UUID.randomUUID();
        String fileName=uuid+"_"+originalFilename;
        String realFileName=realPath+"/"+fileName;
        mav.addObject("fileNames",fileName);
        File file=new File(realFileName);
        multipartFile.transferTo(file);
        return mav;
    }
    @RequestMapping("/deletecar")
    @ResponseBody
    public String  deleteuser(String id) throws JSONException {
        JSONObject json=new JSONObject();
        int deleteuser = carService.deleteCar(id);
        if(deleteuser!=0){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping(value = "/setcar")
    @ResponseBody
    public String  setuser(Car car,HttpServletRequest req) throws JSONException {
        JSONObject json=new JSONObject();
        req.getSession().setAttribute("set1", car);
        Car set1 = (Car) req.getSession().getAttribute("set1");
        if (set1!=null){
            json.put("success", true);
        }
        return json.toString();
    }
    @RequestMapping(value = "/setcar3")
    @ResponseBody
    public String setcar3(Car car) throws JSONException {
        JSONObject json=new JSONObject();
        System.out.println(car);
        int i = carService.setCar(car);
        if (i!=0){
            json.put("success", true);
        }
        return json.toString();
    }
}
