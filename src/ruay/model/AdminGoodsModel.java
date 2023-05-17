package ruay.model;

import java.io.InputStream;

public class AdminGoodsModel {
	private int goodId ;
	private String goodName;
	private double goodPrice;
	private Integer provinceId;
	private Integer categorieId;
	private Integer subId;
	private InputStream goodImg;
	private String goodDescriptio;
	
	
	
	
	
	@Override
	public String toString() {
		return  goodName ;
	}
	
	public String debug() {
		return "AdminGoodsModel [goodId=" + goodId + ", goodName=" + goodName + ", goodPrice=" + goodPrice
				+ ", provinceId=" + provinceId + ", categorieId=" + categorieId
				+ ", subId=" + subId + ", goodImg=" + goodImg + ", goodDescriptio=" + goodDescriptio + "]";
	}

	public AdminGoodsModel(int goodId, String goodName, double goodPrice, Integer provinceId,
			Integer categorieId, Integer subId, InputStream goodImg, String goodDescriptio) {
		super();
		this.goodId = goodId;
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.provinceId = provinceId;
		this.categorieId = categorieId;
		this.subId = subId;
		this.goodImg = goodImg;
		this.goodDescriptio = goodDescriptio;
	}
	
	public AdminGoodsModel(String goodName, double goodPrice, Integer provinceId,
			Integer categorieId, Integer subId, InputStream goodImg, String goodDescriptio) {
		super();
		this.goodName = goodName;
		this.goodPrice = goodPrice;
		this.provinceId = provinceId;
		this.categorieId = categorieId;
		this.subId = subId;
		this.goodImg = goodImg;
		this.goodDescriptio = goodDescriptio;
	}
	
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getGoodName() {
		return goodName;
	}
	public void setGoodName(String goodName) {
		this.goodName = goodName;
	}
	public double getGoodPrice() {
		return goodPrice;
	}
	public void setGoodPrice(double goodPrice) {
		this.goodPrice = goodPrice;
	}
	public Integer getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	public Integer getCategorieId() {
		return categorieId;
	}
	public void setCategorieId(Integer categorieId) {
		this.categorieId = categorieId;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	public InputStream getGoodImg() {
		return goodImg;
	}
	public void setGoodImg(InputStream goodImg) {
		this.goodImg = goodImg;
	}
	public String getGoodDescriptio() {
		return goodDescriptio;
	}
	public void setGoodDescriptio(String goodDescriptio) {
		this.goodDescriptio = goodDescriptio;
	}
	
	
	
	
	
	
}
