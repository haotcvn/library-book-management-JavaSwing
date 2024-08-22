package controllers;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import colors.CustomColor;
import models.MenuModel;
import views.panel.HomePanel;
import views.panel.CategoryPanel;
import views.panel.PublisherPanel;
import views.panel.BookPanel;

public class ReplaceWindowsController {
	private JPanel pnlRoot;
	private String kindSelected = "";
	private List<MenuModel> itemList = null;

	public ReplaceWindowsController(JPanel pnlRoot) {
		this.pnlRoot = pnlRoot;
	}

	public void setView(JButton button) {
		kindSelected = "Home";
		button.setBackground(CustomColor.LAVENDER_LIGHT);

		pnlRoot.removeAll();
		pnlRoot.setLayout(new BorderLayout());
		pnlRoot.add(new HomePanel()).setVisible(true);
		pnlRoot.repaint();
		pnlRoot.validate();

	}

	public void setEvent(List<MenuModel> itemList) {
		this.itemList = itemList;
		for (MenuModel item : itemList) {
			item.getButton().addMouseListener(new ButtonEvent(item.getKind(), item.getButton()));
		}

	}

	class ButtonEvent implements MouseListener {
		private JPanel node;
		private String kind;
		private JButton button;

		public ButtonEvent(String kind, JButton button) {
			this.kind = kind;
			this.button = button;
		}

		@Override
		public void mouseClicked(MouseEvent e) {
			
			switch (kind) {
			case "Home" -> node = new HomePanel();
			case "Book" -> node = new BookPanel();
			case "Category" -> node = new CategoryPanel();
			case "Publisher" -> node = new PublisherPanel();
			default -> throw new IllegalArgumentException("Unexpected value: " + kind);
			}

			pnlRoot.removeAll();
			pnlRoot.setLayout(new BorderLayout());
			pnlRoot.add(node).setVisible(true);
			pnlRoot.repaint();
			pnlRoot.validate();

		}

		@Override
		public void mousePressed(MouseEvent e) {
			kindSelected = kind;
			button.setBackground(CustomColor.PRIMARY);
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if (!kindSelected.equalsIgnoreCase(kind)) {
				button.setBackground(CustomColor.PRIMARY);
				button.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

		}

		@Override
		public void mouseExited(MouseEvent e) {
			if (!kindSelected.equalsIgnoreCase(kind)) {
				button.setBackground(CustomColor.WHITE);
			}

		}

	}
}
