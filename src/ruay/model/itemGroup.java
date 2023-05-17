package ruay.model;

public class  itemGroup {
	Integer groupId ;
	String groupName;
	Integer optionId;
	String optionName;
	double optionPrice;

	@Override
	public String toString() {
		return optionName;
	}

	public itemGroup() {
	}
	
	public itemGroup(Integer groupId, String groupName, Integer optionId, String optionName, double optionPrice) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.optionId = optionId;
		this.optionName = optionName;
		this.optionPrice = optionPrice;
	}


	public Integer getOptionId() {
		return optionId;
	}


	public void setOptionId(Integer optionId) {
		this.optionId = optionId;
	}


	public Integer getGroupId() {
		return groupId;
	}


	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}


	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
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