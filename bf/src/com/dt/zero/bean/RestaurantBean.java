package com.dt.zero.bean;

import java.io.Serializable;

public class RestaurantBean implements Serializable{

	private static final long serialVersionUID = -6453430021123563721L;
	public String logo = "";
	public String name= "";
	public int rate_numbers= 0;
	public String buy_nums= "";
	public String item_msg= "";
	public String promotion= "";
	public String location="";
	public Boolean is_rest= false;
	public Boolean is_favor= false;
	public Boolean is_half= false;
	public Boolean is_mins= false;
	
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
	public String getItem_msg() {
		return item_msg;
	}
	public void setItem_msg(String item_msg) {
		this.item_msg = item_msg;
	}
	public Boolean getIs_rest() {
		return is_rest;
	}
	public void setIs_rest(Boolean is_rest) {
		this.is_rest = is_rest;
	}
	public Boolean getIs_favor() {
		return is_favor;
	}
	public void setIs_favor(Boolean is_favor) {
		this.is_favor = is_favor;
	}
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	public String getlocation() {
		return location;
	}
	public void getlocation(String location) {
		this.location = location;
	}
	public Boolean getIs_half() {
		return is_half;
	}
	public void setIs_half(Boolean is_half) {
		this.is_half = is_half;
	}
	public Boolean getIs_mins() {
		return is_mins;
	}
	public void setIs_mins(Boolean is_mins) {
		this.is_mins = is_mins;
	}
	

}
