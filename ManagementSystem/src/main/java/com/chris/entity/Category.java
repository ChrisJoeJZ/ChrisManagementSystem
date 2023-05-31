package com.chris.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {

    @TableId
    private Integer cateId;
    private String cateName;
    private Integer cateSort;
    private LocalDateTime createtime;
    private LocalDateTime updatetime;
    private String cateChannel;
    private Integer cateParentid;

    //父级分类
//    private Category category;
}
