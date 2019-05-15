package com.hero.common.entity.stock;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 五档
 *
 * @author Administrator
 */
public class BuyHandEntity implements Serializable {
    // 不存储数据库中的字段

    /**
     * 昨收价
     */
    private BigDecimal yc;

    /**
     * 买1 - 手 申请4695股，即47手；
     */
    private BigDecimal b1H;
    /**
     * 买1
     */
    private BigDecimal b1;
    /**
     * 买2 - 手
     */
    private BigDecimal b2H;
    /**
     * 买2
     */
    private BigDecimal b2;
    /**
     * 买3 - 手
     */
    private BigDecimal b3H;
    /**
     * 买3
     */
    private BigDecimal b3;
    /**
     * 买4 - 手
     */
    private BigDecimal b4H;
    /**
     * 买4
     */
    private BigDecimal b4;
    /**
     * 买5 - 手
     */
    private BigDecimal b5H;
    /**
     * 买5
     */
    private BigDecimal b5;

    /**
     * 买6
     */
    private BigDecimal b6;
    /**
     * 买6 - 手
     */
    private BigDecimal b6H;
    /**
     * 买7
     */
    private BigDecimal b7;
    /**
     * 买7 - 手
     */
    private BigDecimal b7H;
    /**
     * 买8
     */
    private BigDecimal b8;
    /**
     * 买7 - 手
     */
    private BigDecimal b8H;
    /**
     * 买9 - 手
     */
    private BigDecimal b9H;
    /**
     * 买9
     */
    private BigDecimal b9;
    /**
     * 买10 - 手
     */
    private BigDecimal b10H;
    /**
     * 买10
     */
    private BigDecimal b10;



    /**
     * 卖1 - 手
     */
    private BigDecimal s1H;
    /**
     * 卖1
     */
    private BigDecimal s1;
    /**
     * 卖2 - 手
     */
    private BigDecimal s2H;
    /**
     * 卖2
     */
    private BigDecimal s2;
    /**
     * 卖3 - 手
     */
    private BigDecimal s3H;
    /**
     * 卖3
     */
    private BigDecimal s3;
    /**
     * 卖4 - 手
     */
    private BigDecimal s4H;
    /**
     * 卖4
     */
    private BigDecimal s4;
    /**
     * 卖5 - 手
     */
    private BigDecimal s5H;
    /**
     * 卖5
     */
    private BigDecimal s5;

    /**
     * 卖5 - 手
     */
    private BigDecimal s6H;
    /**
     * 卖6
     */
    private BigDecimal s6;
    /**
     * 卖7 - 手
     */
    private BigDecimal s7H;
    /**
     * 卖7
     */
    private BigDecimal s7;
    /**
     * 卖8 - 手
     */
    private BigDecimal s8H;
    /**
     * 卖8
     */
    private BigDecimal s8;
    /**
     * 卖9 - 手
     */
    private BigDecimal s9H;
    /**
     * 卖9
     */
    private BigDecimal s9;
    /**
     * 卖5 - 手
     */
    private BigDecimal s10H;
    /**
     * 卖10
     */
    private BigDecimal s10;


    public BigDecimal getYc() {
        return yc;
    }

    public void setYc(BigDecimal yc) {
        this.yc = yc;
    }

    public BigDecimal getB1H() {
        return b1H;
    }

    public void setB1H(BigDecimal b1H) {
        this.b1H = b1H;
    }

    public BigDecimal getB1() {
        return b1;
    }

    public void setB1(BigDecimal b1) {
        this.b1 = b1;
    }

    public BigDecimal getB2H() {
        return b2H;
    }

    public void setB2H(BigDecimal b2H) {
        this.b2H = b2H;
    }

    public BigDecimal getB2() {
        return b2;
    }

    public void setB2(BigDecimal b2) {
        this.b2 = b2;
    }

    public BigDecimal getB3H() {
        return b3H;
    }

    public void setB3H(BigDecimal b3H) {
        this.b3H = b3H;
    }

    public BigDecimal getB3() {
        return b3;
    }

    public void setB3(BigDecimal b3) {
        this.b3 = b3;
    }

    public BigDecimal getB4H() {
        return b4H;
    }

    public void setB4H(BigDecimal b4H) {
        this.b4H = b4H;
    }

    public BigDecimal getB4() {
        return b4;
    }

    public void setB4(BigDecimal b4) {
        this.b4 = b4;
    }

    public BigDecimal getB5H() {
        return b5H;
    }

    public void setB5H(BigDecimal b5H) {
        this.b5H = b5H;
    }

    public BigDecimal getB5() {
        return b5;
    }

    public void setB5(BigDecimal b5) {
        this.b5 = b5;
    }

    public BigDecimal getB6() {
        return b6;
    }

    public void setB6(BigDecimal b6) {
        this.b6 = b6;
    }

    public BigDecimal getB6H() {
        return b6H;
    }

    public void setB6H(BigDecimal b6H) {
        this.b6H = b6H;
    }

    public BigDecimal getB7() {
        return b7;
    }

    public void setB7(BigDecimal b7) {
        this.b7 = b7;
    }

    public BigDecimal getB7H() {
        return b7H;
    }

    public void setB7H(BigDecimal b7H) {
        this.b7H = b7H;
    }

    public BigDecimal getB8() {
        return b8;
    }

    public void setB8(BigDecimal b8) {
        this.b8 = b8;
    }

    public BigDecimal getB8H() {
        return b8H;
    }

    public void setB8H(BigDecimal b8H) {
        this.b8H = b8H;
    }

    public BigDecimal getB9H() {
        return b9H;
    }

    public void setB9H(BigDecimal b9H) {
        this.b9H = b9H;
    }

    public BigDecimal getB9() {
        return b9;
    }

    public void setB9(BigDecimal b9) {
        this.b9 = b9;
    }

    public BigDecimal getB10H() {
        return b10H;
    }

    public void setB10H(BigDecimal b10H) {
        this.b10H = b10H;
    }

    public BigDecimal getB10() {
        return b10;
    }

    public void setB10(BigDecimal b10) {
        this.b10 = b10;
    }

    public BigDecimal getS1H() {
        return s1H;
    }

    public void setS1H(BigDecimal s1H) {
        this.s1H = s1H;
    }

    public BigDecimal getS1() {
        return s1;
    }

    public void setS1(BigDecimal s1) {
        this.s1 = s1;
    }

    public BigDecimal getS2H() {
        return s2H;
    }

    public void setS2H(BigDecimal s2H) {
        this.s2H = s2H;
    }

    public BigDecimal getS2() {
        return s2;
    }

    public void setS2(BigDecimal s2) {
        this.s2 = s2;
    }

    public BigDecimal getS3H() {
        return s3H;
    }

    public void setS3H(BigDecimal s3H) {
        this.s3H = s3H;
    }

    public BigDecimal getS3() {
        return s3;
    }

    public void setS3(BigDecimal s3) {
        this.s3 = s3;
    }

    public BigDecimal getS4H() {
        return s4H;
    }

    public void setS4H(BigDecimal s4H) {
        this.s4H = s4H;
    }

    public BigDecimal getS4() {
        return s4;
    }

    public void setS4(BigDecimal s4) {
        this.s4 = s4;
    }

    public BigDecimal getS5H() {
        return s5H;
    }

    public void setS5H(BigDecimal s5H) {
        this.s5H = s5H;
    }

    public BigDecimal getS5() {
        return s5;
    }

    public void setS5(BigDecimal s5) {
        this.s5 = s5;
    }

    public BigDecimal getS6H() {
        return s6H;
    }

    public void setS6H(BigDecimal s6H) {
        this.s6H = s6H;
    }

    public BigDecimal getS6() {
        return s6;
    }

    public void setS6(BigDecimal s6) {
        this.s6 = s6;
    }

    public BigDecimal getS7H() {
        return s7H;
    }

    public void setS7H(BigDecimal s7H) {
        this.s7H = s7H;
    }

    public BigDecimal getS7() {
        return s7;
    }

    public void setS7(BigDecimal s7) {
        this.s7 = s7;
    }

    public BigDecimal getS8H() {
        return s8H;
    }

    public void setS8H(BigDecimal s8H) {
        this.s8H = s8H;
    }

    public BigDecimal getS8() {
        return s8;
    }

    public void setS8(BigDecimal s8) {
        this.s8 = s8;
    }

    public BigDecimal getS9H() {
        return s9H;
    }

    public void setS9H(BigDecimal s9H) {
        this.s9H = s9H;
    }

    public BigDecimal getS9() {
        return s9;
    }

    public void setS9(BigDecimal s9) {
        this.s9 = s9;
    }

    public BigDecimal getS10H() {
        return s10H;
    }

    public void setS10H(BigDecimal s10H) {
        this.s10H = s10H;
    }

    public BigDecimal getS10() {
        return s10;
    }

    public void setS10(BigDecimal s10) {
        this.s10 = s10;
    }
}
