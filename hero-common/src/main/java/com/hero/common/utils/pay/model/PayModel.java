package com.hero.common.utils.pay.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ali订单支付信息
 * 
 * @author Administrator
 *
 */
public class PayModel implements Serializable {

	private Integer state;
	/**
	 * 订单支付有效时间
	 */
	private String timeout_express;
	/**
	 * 产品编码
	 */
	private String product_code; // TT_PAYHERO_VIP:开通华尔vip，REWARD_HUAER:打赏华尔,REWARD_PERSON:打赏个人
	/**
	 * 订单总价格
	 */
	private BigDecimal total_amount;

	/**
	 * 订单描述
	 */
	private String subject;
	/**
	 * 订单说明
	 */
	private String body;
	/**
	 * 订单编号
	 */
	private String out_trade_no;

	/**
	 * 第三方订单编号
	 */
	private String third_trade_no;

	/**
	 * 回调地址
	 */
	private String local_addr;

	/**
	 * 回调地址IP
	 */
	private String local_ip;

	/**
	 * 订单类型
	 */
	private Integer orderType;// 订单类型：0：打赏用户;1:充值XX私人笔记;2:充值华尔通量化报告VIP

	/**
	 * 收益人
	 */
	private Long  toUserId;// 获得这笔钱的人

	/**
	 * 用户昵称
	 */
	public String userName;

	/**
	 * 用户id
	 */
	public Long userId;

	/**
	 *  支付类型
	 */
	public Integer orderPayType;

	public String getTimeout_express() {
		return timeout_express;
	}

	public void setTimeout_express(String timeout_express) {
		this.timeout_express = timeout_express;
	}

	public String getProduct_code() {
		return product_code;
	}

	public void setProduct_code(String product_code) {
		this.product_code = product_code;
	}

	public BigDecimal getTotal_amount() {
		return total_amount;
	}

	public void setTotal_amount(BigDecimal total_amount) {
		this.total_amount = total_amount;
	}
	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getOut_trade_no() {
		return out_trade_no;
	}

	public void setOut_trade_no(String out_trade_no) {
		this.out_trade_no = out_trade_no;
	}

	public String getLocal_addr() {
		return local_addr;
	}

	public void setLocal_addr(String local_addr) {
		this.local_addr = local_addr;
	}

	public String getLocal_ip() {
		return local_ip;
	}

	public void setLocal_ip(String local_ip) {
		this.local_ip = local_ip;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getToUserId() {
		return toUserId;
	}

	public void setToUserId(Long toUserId) {
		this.toUserId = toUserId;
	}

	public Integer getOrderPayType() {
		return orderPayType;
	}

	public void setOrderPayType(Integer orderPayType) {
		this.orderPayType = orderPayType;
	}

	public String getThird_trade_no() {
		return third_trade_no;
	}

	public void setThird_trade_no(String third_trade_no) {
		this.third_trade_no = third_trade_no;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}
}
