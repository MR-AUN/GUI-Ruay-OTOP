package ruay.model;

public class GroupGoodModel {
	private int groupId ;
	private int goodId;
	private String groupName;
	public GroupGoodModel(int groupId, int goodId, String groupName) {
		super();
		this.groupId = groupId;
		this.goodId = goodId;
		this.groupName = groupName;
	}
	public GroupGoodModel( String groupName,int groupId) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
	}
	
	
	public GroupGoodModel( int goodId, String groupName) {
		super();
		this.goodId = goodId;
		this.groupName = groupName;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getGoodId() {
		return goodId;
	}
	public void setGoodId(int goodId) {
		this.goodId = goodId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	
}
