package ruay.model;

public class GoodGroupOptionView {
	private GroupGoodModel grop;
	private OptionGoodModel option;
	
	
	
	@Override
	public String toString() {
		return  grop.getGroupName() + " >> " + option.getOptionName() ;
	}

	public GoodGroupOptionView(GroupGoodModel grop, OptionGoodModel option) {
		super();
		this.grop = grop;
		this.option = option;
	}
	
	public GroupGoodModel getGrop() {
		return grop;
	}
	public void setGrop(GroupGoodModel grop) {
		this.grop = grop;
	}
	public OptionGoodModel getOption() {
		return option;
	}
	public void setOption(OptionGoodModel option) {
		this.option = option;
	}
	
}
