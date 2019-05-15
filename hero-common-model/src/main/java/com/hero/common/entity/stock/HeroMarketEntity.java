package com.hero.common.entity.stock;


import java.io.Serializable;

/**
 * k线类
 *
 * @author Administrator
 */
public class HeroMarketEntity extends BaseMarketEntity implements Serializable, Cloneable {

    /**
     * 颜色值跳动，0正常，-1绿色，1红色
     */
    private Integer color = 0;



}