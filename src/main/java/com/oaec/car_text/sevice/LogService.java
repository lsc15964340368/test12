package com.oaec.car_text.service;

import com.oaec.car_text.entity.Car;
import com.oaec.car_text.entity.Log;
import com.oaec.car_text.entity.Logl;
import com.oaec.car_text.entity.User;

import java.util.List;

public interface LogService {
    //查看订单
    public List<Log> querylog();
    //检测用户是否存在
    public List<User> log_username(String log_username);
    //根据用户名查询
    public User queryByName(String username);
    //模糊查询订单
    public List<Log> likelog(String m);
    //查询子订单
    public List<Logl> querylog_l(String log_id);
    //添加订单
    public int addlog(Log log);
    //检测品牌是否存在
    public boolean carbybrand(String carbrand);
    //添加子订单
    public int addlog_l(Logl log_l);
    //修改订单
    public int setlog(Log log);
    //删除订单
    public int delete(String log_id);
    //修改子订单
    public int setlog_l(Logl log_l);
    //删除子订单
    public int deletelog_l(String log_l_id);
    //支付
    public int finish(int price,String log_id);
    public List<Log> querylogByLod_id(String log_id);
    //根据品牌修改库存
    public int setByBrand(String car_brand,int num);
}
