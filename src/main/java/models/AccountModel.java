package models;

public class AccountModel {
	private int accountID;
	private int userID;
	private String username;
	private String password;
	private int status;

	public AccountModel() {
	}

	public AccountModel(int userID, String username, String password, int status) {
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public AccountModel(int accountID, int userID, String username, String password, int status) {
		this.accountID = accountID;
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.status = status;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccountModel [accountID=" + accountID + ", userID=" + userID + ", username=" + username + ", password="
				+ password + ", status=" + status + "]";
	}
}
