package com.oaec.car_text.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Role {
    private String roleid;
    private String rolename;
    private String rolecode;
    @TableField(exist = false)
    private Set<Power> powers;
}
