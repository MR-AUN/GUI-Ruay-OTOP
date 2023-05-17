package ruay.model;

public class SupplierGoodsModel {
	private int supgoodId ;
	private SupplierModel sup;
	private CombinGroupModel combin;
	private double supgoodPrice;
	
	
	
	
	
	@Override
	public String toString() {
		return sup.toString();
	}





	public SupplierGoodsModel(int supgoodId, SupplierModel sup, CombinGroupModel combin, double supgoodPrice) {
		super();
		this.supgoodId = supgoodId;
		this.sup = sup;
		this.combin = combin;
		this.supgoodPrice = supgoodPrice;
	}
	
	public SupplierGoodsModel( SupplierModel sup, CombinGroupModel combin, double supgoodPrice) {
		super();
		this.sup = sup;
		this.combin = combin;
		this.supgoodPrice = supgoodPrice;
	}





	public int getSupgoodId() {
		return supgoodId;
	}





	public void setSupgoodId(int supgoodId) {
		this.supgoodId = supgoodId;
	}





	public SupplierModel getSup() {
		return sup;
	}





	public void setSup(SupplierModel sup) {
		this.sup = sup;
	}





	public CombinGroupModel getCombin() {
		return combin;
	}





	public void setCombin(CombinGroupModel combin) {
		this.combin = combin;
	}





	public double getSupgoodPrice() {
		return supgoodPrice;
	}





	public void setSupgoodPrice(double supgoodPrice) {
		this.supgoodPrice = supgoodPrice;
	}
	
	
	
	
}
