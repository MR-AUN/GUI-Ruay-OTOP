package ruay.model;

public class InvoiceDetailModel {
	private int invDeId;
	private int invId;
	private int invDeStatus;
	private int combinId;
	private double invDeAmount;
	private int invDeQuantity ;
	
	
	public InvoiceDetailModel(int invDeId, int invId, int invDeStatus, int combinId, double invDeAmount,
			int invDeQuantity) {
		this.invDeId = invDeId;
		this.invId = invId;
		this.combinId = combinId;
		this.invDeStatus = invDeStatus;
		this.invDeAmount = invDeAmount;
		this.invDeQuantity = invDeQuantity;
	}
	
	public InvoiceDetailModel( int invId, int invDeStatus, int combinId, double invDeAmount,
			int invDeQuantity) {
		this.invId = invId;
		this.combinId = combinId;
		this.invDeStatus = invDeStatus;
		this.invDeAmount = invDeAmount;
		this.invDeQuantity = invDeQuantity;
	}
	public InvoiceDetailModel( double invDeAmount,int invId, int invDeStatus, int combinId, 
			int invDeQuantity ) {
		this.invId = invId;
		this.combinId = combinId;
		this.invDeStatus = invDeStatus;
		this.invDeAmount = invDeAmount;
		this.invDeQuantity = invDeQuantity;
	}
	public int getInvDeId() {
		return invDeId;
	}
	public void setInvDeId(int invDeId) {
		this.invDeId = invDeId;
	}
	public int getInvId() {
		return invId;
	}
	public void setInvId(int invId) {
		this.invId = invId;
	}
	public int getInvDeStatus() {
		return invDeStatus;
	}
	public void setInvDeStatus(int invDeStatus) {
		this.invDeStatus = invDeStatus;
	}
	public double getInvDeAmount() {
		return invDeAmount;
	}
	public void setInvDeAmount(double invDeAmount) {
		this.invDeAmount = invDeAmount;
	}
	public int getInvDeQuantity() {
		return invDeQuantity;
	}
	public void setInvDeQuantity(int invDeQuantity) {
		this.invDeQuantity = invDeQuantity;
	}

	public int getCombinId() {
		return combinId;
	}

	public void setCombinId(int combinId) {
		this.combinId = combinId;
	}
	
	
	
}
