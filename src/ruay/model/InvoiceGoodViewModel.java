package ruay.model;

public class InvoiceGoodViewModel {
	private int goodId ;
	private String goodNmae;
	private double invdeAmount;
	private int invdeQuantity;
	
	
	
	public InvoiceGoodViewModel(int goodId, String goodNmae, double invdeAmount, int invdeQuantity) {
		this.goodId = goodId;
		this.goodNmae = goodNmae;
		this.invdeAmount = invdeAmount;
		this.invdeQuantity = invdeQuantity;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getGoodNmae() {
		return goodNmae;
	}
	public void setGoodNmae(String goodNmae) {
		this.goodNmae = goodNmae;
	}
	public double getInvdeAmount() {
		return invdeAmount;
	}
	public void setInvdeAmount(double invdeAmount) {
		this.invdeAmount = invdeAmount;
	}
	public int getInvdeQuantity() {
		return invdeQuantity;
	}
	public void setInvdeQuantity(int invdeQuantity) {
		this.invdeQuantity = invdeQuantity;
	}
	
}
