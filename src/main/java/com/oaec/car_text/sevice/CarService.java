package com.oaec.car_text.sevice;

import com.oaec.car_text.entity.Car;

import java.util.List;

public interface CarService {
    //查看所有汽车
    public List<Car> queryCar();

    //添加汽车
    public int addCar(Car car);

    //下架汽车
    public int deleteCar(String car_id);

    //修改汽车
    public int setCar(Car car);

    //分类查询
    public List<Car> likecar(String m);
}
