package com.babymate.shop.model;

import java.sql.Date;

public class ProductVO implements java.io.Serializable{
	private Integer product_id;
	private String product_no;
	private String product_name;
	private Integer category_id;
	private Double price;
	private Integer status;
	private Date status_update_time;
	private byte[]  product_icon;
	private String feature_desc;
	private String spec_desc;
	private String note;
	private String remark;
	private Date update_time;
	
	public Integer getProduct_id() {
		return product_id;
	}
	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}
	public String getProduct_no() {
		return product_no;
	}
	public void setProduct_no(String product_no) {
		this.product_no = product_no;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getStatus_update_time() {
		return status_update_time;
	}
	public void setStatus_update_time(Date status_update_time) {
		this.status_update_time = status_update_time;
	}
	public byte[] getProduct_icon() {
		return product_icon;
	}
	public void setProduct_icon(byte[] product_icon) {
		this.product_icon = product_icon;
	}
	public String getFeature_desc() {
		return feature_desc;
	}
	public void setFeature_desc(String feature_desc) {
		this.feature_desc = feature_desc;
	}
	public String getSpec_desc() {
		return spec_desc;
	}
	public void setSpec_desc(String spec_desc) {
		this.spec_desc = spec_desc;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
}
