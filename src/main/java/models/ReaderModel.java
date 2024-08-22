package models;

public class ReaderModel {
	private int readerID;
	private String name;
	private String address;
	private String phoneNumber;
	private String gender;

	public ReaderModel() {
	}

	public ReaderModel(String name, String address, String phoneNumber, String gender) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public ReaderModel(int readerID, String name, String address, String phoneNumber, String gender) {
		this.readerID = readerID;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.gender = gender;
	}

	public int getReaderID() {
		return readerID;
	}

	public void setReaderID(int readerID) {
		this.readerID = readerID;
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

	@Override
	public String toString() {
		return name.toString();
	}

}
