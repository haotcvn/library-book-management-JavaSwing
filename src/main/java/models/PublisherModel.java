package models;

public class PublisherModel {
	private int publisherID;
	private String publisherName;
	private String address;
	private String phone;

	public PublisherModel(String publisherName, String address, String phone) {
		this.publisherName = publisherName;
		this.address = address;
		this.phone = phone;
	}

	public PublisherModel(int publisherID, String publisherName, String address, String phone) {
		this.publisherID = publisherID;
		this.publisherName = publisherName;
		this.address = address;
		this.phone = phone;
	}

	public int getPublisherID() {
		return publisherID;
	}

	public void setPublisherID(int publisherID) {
		this.publisherID = publisherID;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return publisherName.toString();
	}
}
