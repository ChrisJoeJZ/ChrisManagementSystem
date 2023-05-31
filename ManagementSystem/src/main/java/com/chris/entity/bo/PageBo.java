package com.chris.entity.bo;

import lombok.Data;

/**
 * 分页业务模型
 */
@Data
public class PageBo {

    private Long startIndex = 0L;           // 起始索引
    private Integer pageSize = 10;          // 每页显示记录数
    private Long page = 1L;                 // 当前页数
    private Long resultCount;               // 总记录数
    private Long pageCount;                 // 总页数


    /**
     * 需要先手动声明页面显示记录数，再进行页数修改，否则修改页数之后的起始索引会出现错误
     * 并再次计算起始索引
     * @param pageSize
     */
    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
        setStartIndex( (getPage() - 1) * getPageSize() );
    }

    public void setPage(Long page) {
        // 设置当前页码
        this.page = page;
        // 根据当前页码和每页显示的记录数计算出起始索引
        setStartIndex( (getPage() - 1) * getPageSize() );
    }


    // 在设置总记录数的时候可以计算出总页数
    public void setResultCount(Long resultCount) {
        // 给总记录数赋值
        this.resultCount = resultCount;
        // 根据总记录数和每页显示记录数计算出总页数
        setPageCount(
                getResultCount()%getPageSize() == 0?
                        getResultCount()/getPageSize() :
                        getResultCount()/getPageSize() + 1
        );
    }


    // 在设置总页数的时候进行边界控制
    public void setPageCount(Long pageCount) {
        this.pageCount = pageCount;

        //  如果当前页码数大于总页数，则将当前页码数设置为总页数
        if(getPage() > getPageCount()){
            setPage(getPageCount());
        }
        // 如果当前页码小于1，则将当前页码设置为1
        if(getPage() < 1){
            setPage(1L);
        }
    }
}
