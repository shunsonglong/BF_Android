package com.dt.zero.bean;

import java.io.Serializable;

public class FoodBean implements Serializable {

	private static final long serialVersionUID = -6453430021123563722L;
	public String logo = "";
	public String name= "";
	public int rate_numbers= 0;
	public String buy_nums= "";
	public String type= "";
	
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRate_numbers() {
		return rate_numbers;
	}
	public void setRate_numbers(int rate_numbers) {
		this.rate_numbers = rate_numbers;
	}
	public String getBuy_nums() {
		return buy_nums;
	}
	public void setBuy_nums(String buy_nums) {
		this.buy_nums = buy_nums;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
