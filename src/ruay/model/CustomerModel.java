package ruay.model;

public class CustomerModel {
	private int cusId;
	private String cusName;
	private String cusLast ;
	private String cusUsername;
	private String cusEmail;
	private String cusPass;
	private int roleId;
	@Override
	public String toString() {
		return "CustomerModel [cusId=" + cusId + ", cusName=" + cusName + ", cusLast=" + cusLast + ", cusUsername="
				+ cusUsername + ", cusEmail=" + cusEmail + ", cusPass=" + cusPass + ", roleId=" + roleId + "]";
	}
	public CustomerModel(int cusId, String cusName, String cusLast, String cusUsername, String cusEmail, String cusPass,
			int roleId) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusLast = cusLast;
		this.cusUsername = cusUsername;
		this.cusEmail = cusEmail;
		this.cusPass = cusPass;
		this.roleId = roleId;
	}
	public CustomerModel( String cusName, String cusLast, String cusUsername, String cusEmail, String cusPass,
			int roleId) {
		super();
		this.cusId = cusId;
		this.cusName = cusName;
		this.cusLast = cusLast;
		this.cusUsername = cusUsername;
		this.cusEmail = cusEmail;
		this.cusPass = cusPass;
		this.roleId = roleId;
	}
	public int getCusId() {
		return cusId;
	}
	public void setCusId(int cusId) {
		this.cusId = cusId;
	}
	public String getCusName() {
		return cusName;
	}
	public void setCusName(String cusName) {
		this.cusName = cusName;
	}
	public String getCusLast() {
		return cusLast;
	}
	public void setCusLast(String cusLast) {
		this.cusLast = cusLast;
	}
	public String getCusUsername() {
		return cusUsername;
	}
	public void setCusUsername(String cusUsername) {
		this.cusUsername = cusUsername;
	}
	public String getCusEmail() {
		return cusEmail;
	}
	public void setCusEmail(String cusEmail) {
		this.cusEmail = cusEmail;
	}
	public String getCusPass() {
		return cusPass;
	}
	public void setCusPass(String cusPass) {
		this.cusPass = cusPass;
	}
	public int getRoleId() {
		return roleId;
	}
	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
	
	
	
	
	

}
