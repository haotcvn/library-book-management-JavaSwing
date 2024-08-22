package utilities;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import views.MainJFrame2;

public class CustomButton extends JButton implements MouseListener {

	private static final long serialVersionUID = 1L;
	private ButtonClickListener buttonClickListener;

	public CustomButton(String title, ButtonClickListener listener) {
		super(title);
		this.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.setPreferredSize(new Dimension(120, 40));
		this.setBackground(CustomColor.WHITE);
		this.setForeground(CustomColor.BLACK);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(this);
		this.buttonClickListener = listener;
	}
	
	public CustomButton(String title, FlatSVGIcon icon, ButtonClickListener listener) {
		super(title);
		this.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.setPreferredSize(new Dimension(120, 40));
		this.setBackground(CustomColor.WHITE);
		this.setForeground(CustomColor.BLACK);
		this.setIcon(icon);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(this);
		this.buttonClickListener = listener;
	}
	
	public CustomButton(String title, FlatSVGIcon icon, String type, ButtonClickListener listener) {
		super(title);
		this.setFont(new Font("Tahoma", Font.BOLD, 13));
		this.setPreferredSize(new Dimension(120, 40));
		this.setBackground(CustomColor.WHITE);
		this.setForeground(CustomColor.BLACK);
		this.setIcon(icon);
		this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		this.addMouseListener(this);
		this.buttonClickListener = listener;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Custom action on mouse press
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (buttonClickListener != null) {
			buttonClickListener.onButtonClicked();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		this.setBackground(CustomColor.PRIMARY);
		this.setForeground(CustomColor.WHITE);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		this.setBackground(CustomColor.WHITE);
		this.setForeground(CustomColor.BLACK);
	}

	public interface ButtonClickListener {
		void onButtonClicked();
	}
}
