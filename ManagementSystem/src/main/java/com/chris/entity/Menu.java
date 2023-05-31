package com.chris.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 菜单实体类
 */
@Data
public class Menu {

    @TableId
    private Integer menuId;
    private String menuName;
    private String menuUrl;
    private Integer pid;
}
