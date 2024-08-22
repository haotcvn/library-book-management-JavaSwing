package models;

import java.sql.Date;

public class SessionModel {
	private int sessionID;
	private String name;
	private String ip;
	private String date;
	private int status;
	
	public SessionModel() {
	}

	public SessionModel(String name, String ip, String date, int status) {
		this.name = name;
		this.ip = ip;
		this.date = date;
		this.status = status;
	}

	public int getSessionID() {
		return sessionID;
	}

	public void setSessionID(int sessionID) {
		this.sessionID = sessionID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "SessionModel [sessionID=" + sessionID + ", name=" + name + ", ip=" + ip + ", date=" + date + ", status="
				+ status + "]";
	}
}
