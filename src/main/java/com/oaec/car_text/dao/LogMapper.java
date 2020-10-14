package com.oaec.car_text.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaec.car_text.entity.Car;
import com.oaec.car_text.entity.Log;
import com.oaec.car_text.entity.Logl;
import com.oaec.car_text.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface LogMapper extends BaseMapper<Log> {
    //查看订单
    public List<Log> querylog();
    //检测用户是否存在
    public List<User> log_username(String logusername);
    //修改名字
    public int setlogusername(@Param("logusername") String logusername,@Param("loguserphone") String loguserphone,
                              @Param("loguseraddress")String loguseraddress, @Param("loguserid") String loguserid);
    //添加订单
    public int addlog(Log log);
    //添加子订单
    public int addlog_l(Logl logl);
    //检测品牌是否存在
    public List<Car> carbybrand(String carbrand);
    //查询子订单
    public List<Logl> querylog_l(String logid);
    //修改订单
    public int setlog(Log log);
    //模糊查询订单
    public List<Log> likelog(String m);
    //删除订单
    public int delete(String logid);
    //删除订单后子订单自动删除
    public int deletelog_l1(String logid);
    //删除子订单
    public int deletelog_l(String loglid);
    //修改子订单
    public int setlog_l(Logl logl);
    public List<Log> querylogByLod_id(String logid);
    //支付
    public int finish(@Param("logprice") int logprice, @Param("logstatus") String logstatus,
                      @Param("logid") String logid, @Param("mdate1")Date mdate1);
    //根据品牌修改库存
    public int setByBrand(@Param("carbrand") String carbrand,@Param("carstock") int carstock);
}
