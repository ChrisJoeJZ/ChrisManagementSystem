package com.chris.entity.bo;

import lombok.Data;

@Data
public class CategorySearchBo {

    private Integer cateId;
    private String cateName;
    private Integer cateParentid;
}
