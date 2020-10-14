package com.oaec.car_text.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Logl {
    private String loglid;
    private String logid;//订单编号
    private String carbrand;//品牌
    private int carprice;//单价
    private int carnum;//数量
}