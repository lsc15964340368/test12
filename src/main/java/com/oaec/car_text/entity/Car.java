package com.oaec.car_text.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Car {
    @TableField("carid")
    private String carid;//汽车编号
    @TableField("carimg")
    private String carimg;//汽车首页图
    @TableField("carbrand")
    private String carbrand;//汽车品牌
    @TableField("carprice")
    private Integer carprice;//汽车价格
    @TableField("carcreateid")
    private String  carcreateid;//上架人id
    @TableField("carcreatename")
    private String  carcreatename;//上架人姓名
    @TableField("cartype")
    private String cartype;//汽车类型（轿车，SUV，跑车等）
    @TableField("carstock")
    private Integer carstock;//汽车库存
}
