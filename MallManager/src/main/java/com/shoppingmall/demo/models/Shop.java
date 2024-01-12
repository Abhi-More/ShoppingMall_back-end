package com.shoppingmall.demo.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int shop_id;
	
	private String name;
	private String shopCategory;
	
//	@OneToMany(mappedBy = "shop")
//	private List<Employee> employee;
	
	private String shopStatus;
	
	@OneToOne
	@JoinTable(name = "Shop_Shopowner")
	private Shopowner shopOwner;
	private String leaseStatus;
	
	public Shop() {
		super();
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShopCategory() {
		return shopCategory;
	}
	public void setShopCategory(String shopCategory) {
		this.shopCategory = shopCategory;
	}
//	public List<Employee> getEmployee() {
//		return employee;
//	}
//	public void setEmployee(List<Employee> employee) {
//		this.employee = employee;
//	}
	public String getShopStatus() {
		return shopStatus;
	}
	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}
	public Shopowner getShopOwner() {
		return shopOwner;
	}
	public void setShopOwner(Shopowner shopOwner) {
		this.shopOwner = shopOwner;
	}
	public String getLeaseStatus() {
		return leaseStatus;
	}
	public void setLeaseStatus(String leaseStatus) {
		this.leaseStatus = leaseStatus;
	}
}
