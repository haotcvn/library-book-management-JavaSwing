package models;

import javax.swing.JButton;

public class MenuModel {
	private String kind;
	private JButton button;
	

	public MenuModel() {
	}
	
	public MenuModel(String kind, JButton button) {
		this.kind = kind;
		this.button = button;
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public JButton getButton() {
		return button;
	}

	public void setButton(JButton button) {
		this.button = button;
	}
}