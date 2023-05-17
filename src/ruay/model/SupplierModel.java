package ruay.model;

public class SupplierModel {
	private int supId ;
	private String supName;
	private String supLast;
	private String supAddress;
	private String supPhone;
	
	@Override
	public String toString() {
		return supName + " " + supLast ;
	}
	public SupplierModel(int supId, String supName, String supLast, String supAddress, String supPhone) {
		super();
		this.supId = supId;
		this.supName = supName;
		this.supLast = supLast;
		this.supAddress = supAddress;
		this.supPhone = supPhone;
	}
	public SupplierModel( String supName, String supLast, String supAddress, String supPhone) {
		super();
		this.supId = supId;
		this.supName = supName;
		this.supLast = supLast;
		this.supAddress = supAddress;
		this.supPhone = supPhone;
	}
	public int getSupId() {
		return supId;
	}
	public void setSupId(int supId) {
		this.supId = supId;
	}
	public String getSupName() {
		return supName;
	}
	public void setSupName(String supName) {
		this.supName = supName;
	}
	public String getSupLast() {
		return supLast;
	}
	public void setSupLast(String supLast) {
		this.supLast = supLast;
	}
	public String getSupAddress() {
		return supAddress;
	}
	public void setSupAddress(String supAddress) {
		this.supAddress = supAddress;
	}
	public String getSupPhone() {
		return supPhone;
	}
	public void setSupPhone(String supPhone) {
		this.supPhone = supPhone;
	}
	
	
	
	
}
