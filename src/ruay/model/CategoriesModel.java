package ruay.model;

public class CategoriesModel {
	private int cateId ;
	private String cateName;
	
	
	
	public CategoriesModel(int cateId, String cateName) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
	}
	@Override
	public String toString() {
		return cateName ;
	}
	public int getCateId() {
		return cateId;
	}
	public void setCateId(int cateId) {
		this.cateId = cateId;
	}
	public String getCateName() {
		return cateName;
	}
	public void setCateName(String cateName) {
		this.cateName = cateName;
	}
	
	
}
