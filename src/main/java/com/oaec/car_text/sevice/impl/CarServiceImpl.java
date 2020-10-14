package com.oaec.car_text.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.oaec.car_text.dao.CarMapper;
import com.oaec.car_text.entity.Car;
import com.oaec.car_text.sevice.CarService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.UUID;

@Service("carService")
public class CarServiceImpl implements CarService {
    @Resource
    private CarMapper carMapper;
    @Override
    public List<Car> queryCar() {
        return carMapper.queryCar();
    }

    @Override
    public int addCar(Car car) {
        UUID uuid=UUID.randomUUID();
        car.setCarid(uuid.toString());
        return carMapper.addCar(car);
    }

    @Override
    public int deleteCar(String car_id) {
        return carMapper.deleteCar(car_id);
    }

    @Override
    public int setCar(Car car) {
        QueryWrapper<Car> qw=new QueryWrapper<Car>();
        qw.eq("carid", car.getCarid());
        return carMapper.update(car,qw);
    }

    @Override
    public List<Car>  likecar(String m){
       return carMapper.likecar(m);
    }
}
