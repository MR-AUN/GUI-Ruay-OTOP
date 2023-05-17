package ruay.model;

public class CombinGroupModel {
	private int combinId ;
	private String combinName ;
	private double combinPrice;
	private int combinQua ;
	private AdminGoodsModel good ;
	
	
	@Override
	public String toString() {
		return combinName == null ? good.getGoodName():combinName ;
	}
	public CombinGroupModel(int combinId, String combinName, double combinPrice, int combinQua, AdminGoodsModel good) {
		super();
		this.combinId = combinId;
		this.combinName = combinName;
		this.combinPrice = combinPrice;
		this.combinQua = combinQua;
		this.good = good;
	}
	public CombinGroupModel( String combinName, double combinPrice, int combinQua, AdminGoodsModel good) {
		super();
		this.combinName = combinName;
		this.combinPrice = combinPrice;
		this.combinQua = combinQua;
		this.good = good;
	}
	public CombinGroupModel( double combinPrice, int combinQua, AdminGoodsModel good) {
		super();
		this.combinPrice = combinPrice;
		this.combinQua = combinQua;
		this.good = good;
	}
	public int getCombinId() {
		return combinId;
	}
	public void setCombinId(int combinId) {
		this.combinId = combinId;
	}
	public String getCombinName() {
		return combinName;
	}
	public void setCombinName(String combinName) {
		this.combinName = combinName;
	}
	public double getCombinPrice() {
		return combinPrice;
	}
	public void setCombinPrice(double combinPrice) {
		this.combinPrice = combinPrice;
	}
	public int getCombinQua() {
		return combinQua;
	}
	public void setCombinQua(int combinQua) {
		this.combinQua = combinQua;
	}
	public AdminGoodsModel getGood() {
		return good;
	}
	public void setGood(AdminGoodsModel good) {
		this.good = good;
	}
	
	
}
