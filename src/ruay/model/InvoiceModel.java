package ruay.model;

public class InvoiceModel {
	private int invId ;
	private int supId;
	private String invDate ;
	private double invTotal ;
	
	public InvoiceModel(int invId, int supId, String invDate, double invTotal) {
		this.invId = invId;
		this.supId = supId;
		this.invDate = invDate;
		this.invTotal = invTotal;
	}
	public InvoiceModel(int invId, int supId, String invDate) {
		this.invId = invId;
		this.supId = supId;
		this.invDate = invDate;
	}
	
	public InvoiceModel(int supId, String invDate, double invTotal) {
		this.supId = supId;
		this.invDate = invDate;
		this.invTotal = invTotal;
	}
	
	
	public int getInvId() {
		return invId;
	}
	public void setInvId(int invId) {
		this.invId = invId;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public String getInvDate() {
		return invDate;
	}
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	public double getInvTotal() {
		return invTotal;
	}
	public void setInvTotal(double invTotal) {
		this.invTotal = invTotal;
	}
	
	
}
