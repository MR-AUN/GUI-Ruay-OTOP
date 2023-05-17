package ruay.model;

public class InvoiceDetailViewModel {
	private int invDeId;
	private InvoiceModel inv; 
	private int invDeStatus;
	private CombinGroupModel combin ;
	private double invDeAmount;
	private int invDeQuantity ;
	public InvoiceDetailViewModel(int invDeId, InvoiceModel inv, int invDeStatus, CombinGroupModel combin,
			double invDeAmount, int invDeQuantity) {
		super();
		this.invDeId = invDeId;
		this.inv = inv;
		this.invDeStatus = invDeStatus;
		this.combin = combin;
		this.invDeAmount = invDeAmount;
		this.invDeQuantity = invDeQuantity;
	}
	
	public InvoiceDetailViewModel( InvoiceModel inv, int invDeStatus, CombinGroupModel combin,
			double invDeAmount, int invDeQuantity) {
		super();
		this.inv = inv;
		this.invDeStatus = invDeStatus;
		this.combin = combin;
		this.invDeAmount = invDeAmount;
		this.invDeQuantity = invDeQuantity;
	}

	public int getInvDeId() {
		return invDeId;
	}

	public void setInvDeId(int invDeId) {
		this.invDeId = invDeId;
	}

	public InvoiceModel getInv() {
		return inv;
	}

	public void setInv(InvoiceModel inv) {
		this.inv = inv;
	}

	public int getInvDeStatus() {
		return invDeStatus;
	}

	public void setInvDeStatus(int invDeStatus) {
		this.invDeStatus = invDeStatus;
	}

	public CombinGroupModel getCombin() {
		return combin;
	}

	public void setCombin(CombinGroupModel combin) {
		this.combin = combin;
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
	
	
	
	
	
	
	
}
