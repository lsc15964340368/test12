package com.oaec.car_text.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    private String logid;//订单编号
    private String loguserid;//买家id
    private String logusername;//买家姓名
    private String loguseraddress;//买家住址
    private String loguserphone;//买家电话
    private Date mdate;//下单时间
    private Date mdate1;//完成订单时间
    private String logcreateid;//创建订单人id
    private String logcreatename;//创建订单人姓名
    private String logprice;//总价格
    private String logstatus;//订单状态
}
