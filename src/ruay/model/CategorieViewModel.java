package ruay.model;

public class CategorieViewModel {
	private Integer cateId;
	private String cateName;
	private String subName;
	private Integer subId ;
	
	
	
	@Override
	public String toString() {
		return cateName + (subName != null ? " >> "+subName:""	);
	}
	public CategorieViewModel(int cateId, String cateName, String subName, Integer subId) {
		super();
		this.cateId = cateId;
		this.cateName = cateName;
		this.subName = subName;
		this.subId = subId;
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
	public String getSubName() {
		return subName;
	}
	public void setSubName(String subName) {
		this.subName = subName;
	}
	public Integer getSubId() {
		return subId;
	}
	public void setSubId(Integer subId) {
		this.subId = subId;
	}
	
	
	
}
