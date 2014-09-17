package com.kaiqi.love_family.comment;

public class TableDBComment {
	private int id;
	private String company_name;// 公司名字；
	private String stars;// 星级；
	private String property;// 公司性质；
	private String company_content;// 公司简介；
	private String telephone;// 电话号码
	private String address;//公司地址
	private boolean identification;
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getStars() {
		return stars;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getProperty() {
		return property;
	}
	public void setProperty(String property) {
		this.property = property;
	}
	public String getCompany_content() {
		return company_content;
	}
	public void setCompany_content(String company_content) {
		this.company_content = company_content;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public boolean isIdentification() {
		return identification;
	}
	public void setIdentification(boolean identification) {
		this.identification = identification;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
