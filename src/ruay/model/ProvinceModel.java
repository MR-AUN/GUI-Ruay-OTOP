package ruay.model;

public class ProvinceModel {
	private int proviceId ;
	private String proviceCode ;
	private String proviceName ;
	private int geoId ;
	
	
	
	@Override
	public String toString() {
		return proviceName ;
	}
	public ProvinceModel(int proviceId, String proviceCode, String proviceName, int geoId) {
		super();
		this.proviceId = proviceId;
		this.proviceCode = proviceCode;
		this.proviceName = proviceName;
		this.geoId = geoId;
	}
	public int getProviceId() {
		return proviceId;
	}
	public void setProviceId(int proviceId) {
		this.proviceId = proviceId;
	}
	public String getProviceCode() {
		return proviceCode;
	}
	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}
	public String getProviceName() {
		return proviceName;
	}
	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	public int getGeoId() {
		return geoId;
	}
	public void setGeoId(int geoId) {
		this.geoId = geoId;
	}
	
	
}
