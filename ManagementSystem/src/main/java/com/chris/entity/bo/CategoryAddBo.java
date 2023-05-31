package com.chris.entity.bo;

import lombok.Data;

@Data
public class CategoryAddBo {

    private String cateName;
    private Integer cateSort;
    private Integer cateParentid;
}
