package com.oaec.car_text.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oaec.car_text.entity.Car;

import java.util.List;

public interface CarMapper extends BaseMapper<Car> {
    //查看所有汽车
    public List<Car> queryCar();
    //添加汽车
    public int  addCar(Car car);
    //下架汽车
    public int  deleteCar(String car_id);

    //根据品牌查询价格
    public List<Car> bybrand(String car_brand);
    //分类查询
    public List<Car>  likecar(String m);

}
