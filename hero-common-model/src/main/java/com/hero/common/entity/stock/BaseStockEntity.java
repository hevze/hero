package com.hero.common.entity.stock;


import com.hero.common.entity.base.BaseEntity;
import lombok.Data;

public class BaseStockEntity extends BaseEntity {

    private Integer sCode;
    private Integer sName;
    private Integer sPingy;
    private Integer sType;

    public Integer getsCode() {
        return sCode;
    }

    public void setsCode(Integer sCode) {
        this.sCode = sCode;
    }

    public Integer getsName() {
        return sName;
    }

    public void setsName(Integer sName) {
        this.sName = sName;
    }

    public Integer getsPingy() {
        return sPingy;
    }

    public void setsPingy(Integer sPingy) {
        this.sPingy = sPingy;
    }

    public Integer getsType() {
        return sType;
    }

    public void setsType(Integer sType) {
        this.sType = sType;
    }
}
