package com.hero.common.entity.stock;


import java.io.Serializable;
import java.math.BigDecimal;

public class BaseMarketEntity extends BaseStockEntity implements Serializable {

    /**
     * 开盘
     */
    protected BigDecimal o;

    /**
     * 当前价格
     */
    protected BigDecimal c ;

    /**
     * 昨收价格
     */
    protected BigDecimal yc;

    /**
     * 最低 Low
     */
    protected BigDecimal l;

    /**
     * 最高 hight
     */
    protected BigDecimal h;

    /**
     * 量
     */
    protected BigDecimal v;

    /**
     * 额
     */
    protected BigDecimal t;

    /**
     * 每天总量 sum volume
     */
    protected BigDecimal sv;

    /**
     * 每天总额 sum turnover
     */
    protected BigDecimal st;

    /**
     * 涨比  eg.-2.3
     */
    protected BigDecimal i;

    /**
     * 涨跌幅  eg.-2.3%
     */
    protected BigDecimal p;

    /**
     * 换手率 changeRate  eg.-2.3%
     */
    protected BigDecimal cr;

    /**
     * 开盘
     */
    protected Long date;

    /**
     * 时间
     */
    protected Long time;

    /**
     * 是否停牌
     */
    protected Boolean stop;


    public BigDecimal getO() {
        return o;
    }

    public void setO(BigDecimal o) {
        this.o = o;
    }

    public BigDecimal getC() {
        return c;
    }

    public void setC(BigDecimal c) {
        this.c = c;
    }

    public BigDecimal getYc() {
        return yc;
    }

    public void setYc(BigDecimal yc) {
        this.yc = yc;
    }

    public BigDecimal getL() {
        return l;
    }

    public void setL(BigDecimal l) {
        this.l = l;
    }

    public BigDecimal getH() {
        return h;
    }

    public void setH(BigDecimal h) {
        this.h = h;
    }

    public BigDecimal getV() {
        return v;
    }

    public void setV(BigDecimal v) {
        this.v = v;
    }

    public BigDecimal getT() {
        return t;
    }

    public void setT(BigDecimal t) {
        this.t = t;
    }

    public BigDecimal getSv() {
        return sv;
    }

    public void setSv(BigDecimal sv) {
        this.sv = sv;
    }

    public BigDecimal getSt() {
        return st;
    }

    public void setSt(BigDecimal st) {
        this.st = st;
    }

    public BigDecimal getI() {
        return i;
    }

    public void setI(BigDecimal i) {
        this.i = i;
    }

    public BigDecimal getP() {
        return p;
    }

    public void setP(BigDecimal p) {
        this.p = p;
    }

    public BigDecimal getCr() {
        return cr;
    }

    public void setCr(BigDecimal cr) {
        this.cr = cr;
    }

    public Long getDate() {
        return date;
    }

    public void setDate(Long date) {
        this.date = date;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public Boolean getStop() {
        return stop;
    }

    public void setStop(Boolean stop) {
        this.stop = stop;
    }
}
