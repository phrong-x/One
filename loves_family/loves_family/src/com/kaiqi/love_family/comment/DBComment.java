package com.kaiqi.love_family.comment;

public class DBComment {
	//数据库名称
	public final static String DB_NAME = "company.db";
	//数据库版本
	public final static int DB_VERSION = 1;
	/**
	 * 数据库存储内容
	 */	
	public final static String TABLE_NAME="company_detail";//表名
	public final static String C_ID="_id";//数据ID
	public final static String C_NAME="_name";//公司名字
	public final static String C_INTRODUCTION="_introduction";//公司简介
	public final static String C_STAR="_star";//公司星级
	public final static String C_PROPERTY="_property";//公司性质
	public final static String C_ADDRESS="_address";//公司地址
	public final static String C_TELEPHONE="_telephone";//公司电话
	public final static int REQUEST_CODE=18;
	public final static String CREATE_TABLE_HEAD = "create table if not exists ";
	public final static String KEY = " integer primary key autoincrement, ";
	public final static String DROP_TABLE = "DROP TABLE IF EXISTS ";
}
