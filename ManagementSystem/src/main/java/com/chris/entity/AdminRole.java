package com.chris.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("admin_role")
public class AdminRole {

    private Integer adminId;
    private Integer roleId;
}
