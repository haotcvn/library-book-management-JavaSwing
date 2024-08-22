package models;

public class UserModel {
	private int userID;
	private AccountModel accountModel;
	private String name;
	private String address;
	private String phoneNumber;
	private String gender;
	
	public UserModel(AccountModel accountModel, String name, String address, String phoneNumber, String gender) {
		this.accountModel = accountModel;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public UserModel(int userID, AccountModel accountModel, String name, String address, String phoneNumber,
			String gender) {
		this.userID = userID;
		this.accountModel = accountModel;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public AccountModel getAccountModel() {
		return accountModel;
	}

	public void setAccountModel(AccountModel accountModel) {
		this.accountModel = accountModel;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
