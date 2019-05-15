package com.hero.common.entity.base;

import lombok.Data;

/**
 * 类说明 ：
 *
 * @ClassName BaseEntity
 * @Author hwz.hs
 * @Date 2018/12/8 15:40
 * @Version v_1.0
 */
public class BaseEntity extends BaseCreateEntity {

    protected String remarks;

    protected String delFlag;

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }
}
