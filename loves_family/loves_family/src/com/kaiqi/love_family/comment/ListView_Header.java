package com.kaiqi.love_family.comment;

public class ListView_Header {

	private String company_name;// 公司名字；
	private int stars;// 星级；
	private String property;// 公司性质；
	private String company_content;// 公司简介；
	private int telephone;// 电话号码
	private int identification;

	public int getIdentification() {
		return identification;
	}

	public void setIdentification(int identification) {
		this.identification = identification;
	}

	public int getTelephone() {
		return telephone;
	}

	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public int getStars() {
		
		return stars;
	}

	public void setStars(int stars) {
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


}
