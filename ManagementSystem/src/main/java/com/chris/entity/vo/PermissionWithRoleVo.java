package com.chris.entity.vo;

import lombok.Data;

@Data
public class PermissionWithRoleVo {

    private Integer permissionId;
    private String permissionName;
    private Integer pid;
    private Integer roleId;
}
