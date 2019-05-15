package com.hero.common.entity.base;

import java.util.Date;

/**
 * 类说明 ：
 *
 * @ClassName BaseCreateEntity
 * @Author hwz.hs
 * @Date 2018/12/8 15:41
 * @Version v_1.0
 */
public class BaseCreateEntity extends BaseIdEntity{

    protected Integer createBy;

    protected Date createDate;

    protected Integer updateBy;

    protected Date updateDate;

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
}
