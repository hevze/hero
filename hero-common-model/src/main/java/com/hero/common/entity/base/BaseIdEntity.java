package com.hero.common.entity.base;

import java.io.Serializable;

/**
 * 类说明 ：
 *
 * @ClassName BaseIdEntity
 * @Author hwz.hs
 * @Date 2018/12/8 15:41
 * @Version v_1.0
 */
public class BaseIdEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    protected Integer id;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
