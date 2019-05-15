package com.hero.common.utils.number;

import org.apache.commons.lang3.math.NumberUtils;

import java.math.BigDecimal;


/**
 * 
 * @描述: Object值转为不同类型的数值.
 * @作者: WuShuicheng.
 * @创建: 2014-9-29,下午4:31:41
 * @版本: V1.0
 * 
 */
public class NumberUtil {

	/*** 舍 */
	public final static int SCALETYPE = BigDecimal.ROUND_DOWN;
	/*** 2位小数 */
	public final  static int SCALENUMBER2 = 2;
	/*** 3位小数 */
	public final static int SCALENUMBER3 = 3;
	/*** 4位小数 */
	public final static int SCALENUMBER4 = 4;
	/*** 5位小数 */
	public final static int SCALENUMBER5 = 5;
	/*** 6位小数 */
	public final static int SCALENUMBER6 = 6;
	/*** 2位小数 */
	public final  static BigDecimal SCALEFORMART2 = new BigDecimal(0.001);
	/*** 3位小数 */
	public final  static BigDecimal SCALEFORMART3 = new BigDecimal(0.0001);
	/*** 4位小数 */
	public final  static BigDecimal SCALEFORMART4 = new BigDecimal(0.00001);
	/*** 5位小数 */
	public final  static BigDecimal SCALEFORMART5 = new BigDecimal(0.000001);
	/*** 6位小数 */
	public final  static BigDecimal SCALEFORMART6 = new BigDecimal(0.0000001);

	/***
	 * 数字精确值
	 * @param decimal
	 * @param scale
	 * @return
	 */
	public static  BigDecimal scale(BigDecimal decimal,int scale){
		switch (scale){
			case NumberUtil.SCALENUMBER2:
				return decimal.add(SCALEFORMART2).setScale(NumberUtil.SCALENUMBER2,NumberUtil.SCALETYPE);
			case NumberUtil.SCALENUMBER3:
				return  decimal.add(SCALEFORMART3).setScale(NumberUtil.SCALENUMBER3,NumberUtil.SCALETYPE);
			case NumberUtil.SCALENUMBER4:
				return  decimal.add(SCALEFORMART4).setScale(NumberUtil.SCALENUMBER4,NumberUtil.SCALETYPE);
			case NumberUtil.SCALENUMBER5:
				return  decimal.add(SCALEFORMART5).setScale(NumberUtil.SCALENUMBER4,NumberUtil.SCALETYPE);
			case NumberUtil.SCALENUMBER6:
				return  decimal.add(SCALEFORMART6).setScale(NumberUtil.SCALENUMBER4,NumberUtil.SCALETYPE);
			default:return BigDecimal.ZERO;
		}
	}


	/***
	 * 数字精确值
	 * @param decimal
	 * @param scale
	 * @return
	 */
	public static  BigDecimal scaleFormart(BigDecimal decimal,int scale){
		switch (scale){
			case NumberUtil.SCALENUMBER2:
				return decimal.setScale(NumberUtil.SCALENUMBER2,NumberUtil.SCALETYPE);
			case NumberUtil.SCALENUMBER3:
				return  decimal.setScale(NumberUtil.SCALENUMBER3,NumberUtil.SCALETYPE);
			case NumberUtil.SCALENUMBER4:
				return  decimal.setScale(NumberUtil.SCALENUMBER4,NumberUtil.SCALETYPE);
			default:return BigDecimal.ZERO;
		}
	}

	/***
	 * 数字精确值
	 * @param decimal
	 * @param scale
	 * @return
	 */
	public static  BigDecimal scaleFormart4S5R(BigDecimal decimal,int scale){
		switch (scale){
			case NumberUtil.SCALENUMBER2:
				return decimal.setScale(NumberUtil.SCALENUMBER2,BigDecimal.ROUND_HALF_UP);
			case NumberUtil.SCALENUMBER3:
				return  decimal.setScale(NumberUtil.SCALENUMBER3,BigDecimal.ROUND_HALF_UP);
			case NumberUtil.SCALENUMBER4:
				return  decimal.setScale(NumberUtil.SCALENUMBER4,BigDecimal.ROUND_HALF_UP);
			default:return BigDecimal.ZERO;
		}
	}


	/**
	 * 判断对象值是否为纯数字组成(如:001234,7899),只有纯数字的值才能转Long或Integer.
	 * @param obj 要判断的值.
	 * @return true or false.
	 */
	public static boolean isDigits(Object obj) {
		if (obj == null){
			return false;
		}
		return NumberUtils.isDigits(obj.toString());
	}
	
	/**
	 * 判断对象值是否为数字,包含整数和小数(如:001234,7899,7.99,99.00)
	 * @param obj 要判断的值.
	 * @return true or false.
	 */
	public static boolean isNumber(Object obj) {
		if (obj == null){
			return false;
		}
		return NumberUtils.isNumber(obj.toString());
	}

	/**
	 * Object对象转BigDecimal <br/>
	 * 1、如果Object为空或Object不是数值型对象:抛数字格式化异常 <br/>
	 * 2、Object为数值型对象:转为BigDecimal类型并返回 <br/>
	 * @param obj 要转换的Object对象 <br/>
	 * @return BigDecimal
	 */
	public static BigDecimal toBigDecimal(Object obj) {
		if (obj == null || !NumberUtils.isNumber(obj.toString())){
			throw new NumberFormatException("数字格式化异常");
		} else {
			return new BigDecimal(obj.toString());
		}
	}
	
	/**
	 * Object对象转Double <br/>
	 * 1、如果Object为空或Object不是数值型对象:抛数字格式化异常 <br/>
	 * 2、Object为数值型对象:转为Double类型并返回 <br/>
	 * @param obj 要转换的Object对象 <br/>
	 * @return Double
	 */
	public static Double toDouble(Object obj) {
		if (obj == null || !NumberUtils.isNumber(obj.toString())){
			throw new NumberFormatException("数字格式化异常");
		} else {
			return Double.valueOf(obj.toString());
		}
	}
	
	/**
	 * Object对象转Long <br/>
	 * 1、如果Object为空或Object不是整数型对象:抛数字格式化异常 <br/>
	 * 2、Object为整数型对象:转为Long类型并返回 <br/>
	 * @param obj 要转换的Object对象 <br/>
	 * @return Long
	 */
	public static Long toLong(Object obj) {
		if (obj == null || !NumberUtils.isDigits(obj.toString())){
			throw new NumberFormatException("数字格式化异常");
		} else {
			return Long.valueOf(obj.toString());
		}
	}
	
	/**
	 * Object对象转Integer <br/>
	 * 1、如果Object为空或Object不是整数型对象:抛数字格式化异常 <br/>
	 * 2、Object为整数型对象:转为Integer类型并返回 <br/>
	 * @param obj 要转换的Object对象 <br/>
	 * @return Integer
	 */
	public static Integer toInteger(Object obj) {
		if (obj == null || !NumberUtils.isDigits(obj.toString())){
			throw new NumberFormatException("数字格式化异常");
		} else {
			return Integer.valueOf(obj.toString());
		}
	}
	
	
//	public static void main(String[] args) {
//		BigDecimal bdcm4 = toBigDecimal("9999");
//		System.out.println("bdcm4:" + bdcm4);
//	}

}
