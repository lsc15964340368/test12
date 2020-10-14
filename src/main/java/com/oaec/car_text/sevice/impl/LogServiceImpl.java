package com.oaec.car_text.sevice.impl;

import com.oaec.car_text.dao.CarMapper;
import com.oaec.car_text.dao.LogMapper;
import com.oaec.car_text.entity.Car;
import com.oaec.car_text.entity.Log;
import com.oaec.car_text.entity.Logl;
import com.oaec.car_text.entity.User;
import com.oaec.car_text.service.LogService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service("logService")
public class LogServiceImpl implements LogService {
    @Resource
    private LogMapper logDao;
    @Resource
    private CarMapper carDao;

    @Override
    public List<Log> querylog() {
        return logDao.querylog();
    }

    @Override
    public List<User> log_username(String logusername) {
        return logDao.log_username(logusername);
    }

    @Override
    public User queryByName(String username) {
        List<User> users = logDao.log_username(username);
        for (User user :users){
         return user;
        }
        return null;
    }

    @Override
    public List<Log> likelog(String m) {
        return logDao.likelog(m);
    }

    @Override
    public List<Logl> querylog_l(String log_id) {
        return logDao.querylog_l(log_id);
    }

    @Override
    public int addlog(Log log) {
        UUID uuid=UUID.randomUUID();
        log.setLogid(uuid.toString());
        String log_status = log.getLogstatus();
        if(log_status.equals("1")){
          log.setLogstatus("未支付");
        }
        return logDao.addlog(log);
    }

    @Override
    public boolean carbybrand(String carbrand) {
        List<Car> list = logDao.carbybrand(carbrand);
        if(list.size()!=0){
            return true;
        }
        return false;
    }

    @Override
    public int addlog_l(Logl log_l) {
        List<Car> list = logDao.carbybrand(log_l.getCarbrand());
        UUID uuid=UUID.randomUUID();
        log_l.setLoglid(uuid.toString());
        for (Car c:list
             ) {
            log_l.setCarprice(c.getCarprice());
        }
        return logDao.addlog_l(log_l);
    }

    @Override
    public int setlog(Log log) {
        List<User> users = logDao.log_username(log.getLogusername());
        for (User u:users
             ) {
            log.setLoguserid(u.getId());
            log.setLoguserphone(u.getUserphone());
            log.setLoguseraddress(u.getUseraddress());
        }
        return logDao.setlog(log);
    }

    @Override
    public int delete(String log_id) {
        List<Logl> list = logDao.querylog_l(log_id);
        if (list!=null){
            logDao.deletelog_l1(log_id);
        }
        return logDao.delete(log_id);
    }

    @Override
    public int setlog_l(Logl log_l) {
        List<Car> list = carDao.bybrand(log_l.getCarbrand());
        for (Car car:list
             ) {
            log_l.setCarprice(car.getCarprice());
        }
        return logDao.setlog_l(log_l);
    }

    @Override
    public int deletelog_l(String log_l_id) {
        return logDao.deletelog_l(log_l_id);
    }

    @Override
    public int finish(int price,String log_id) {
        String status="已支付";
        Date mdate1=new Date();
        return logDao.finish(price, status, log_id,mdate1);
    }

    @Override
    public List<Log> querylogByLod_id(String log_id) {
        return logDao.querylogByLod_id(log_id);
    }

    @Override
    public int setByBrand(String car_brand,int num) {
        List<Car> cars = logDao.carbybrand(car_brand);
        for (Car c: cars
             ) {
            if(c.getCarstock()<num){
                return 0;
            }
        }
        return logDao.setByBrand(car_brand, num);
    }
}
