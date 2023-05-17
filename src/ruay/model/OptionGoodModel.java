package ruay.model;

public class OptionGoodModel {
	private int optionId;
	private int groupId;
	private String optionName;
	private double optionPrice;
	
	
	
	@Override
	public String toString() {
		return optionName ;
	}
	public OptionGoodModel() {
	}
	public OptionGoodModel(int optionId, int groupId, String optionName, double optionPrice) {
		super();
		this.optionId = optionId;
		this.groupId = groupId;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}
	
	public OptionGoodModel(int groupId) {
		super();
		this.groupId = groupId;
	}
	
	public OptionGoodModel( int groupId, String optionName, double optionPrice) {
		super();
		this.groupId = groupId;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}
	public OptionGoodModel( String optionName, double optionPrice ,int optionId) {
		super();
		this.optionId = optionId;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}
	public int getOptionId() {
		return optionId;
	}
	public void setOptionId(int optionId) {
		this.optionId = optionId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public String getOptionName() {
		return optionName;
	}
	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}
	public double getOptionPrice() {
		return optionPrice;
	}
	public void setOptionPrice(double optionPrice) {
		this.optionPrice = optionPrice;
	}
	
	
}
