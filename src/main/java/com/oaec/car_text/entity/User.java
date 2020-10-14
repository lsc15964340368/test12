package com.oaec.car_text.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    @TableField("usercode")
    private String usercode;
    @TableField("username")
    private String username;
    private String password;
    @TableField("userstatus")
    private String userstatus;
    @TableField("userphone")
    private String userphone;
    @TableField("useraddress")
    private String useraddress;
    private String salt;
    @TableField(exist = false)
    private Set<Role> roles;
}
