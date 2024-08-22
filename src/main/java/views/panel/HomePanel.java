package views.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class HomePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	private JPanel center;
	private JLabel lblNewLabel;

	public HomePanel() {
		FlatIntelliJLaf.registerCustomDefaultsSource("style");
		FlatIntelliJLaf.setup();
		this.setBackground(new Color(24, 24, 24));
		this.setBounds(0, 200, 300, 1200);
		this.setLayout(new BorderLayout(0, 0));
		this.setOpaque(true);

		center = new JPanel();
		center.setPreferredSize(new Dimension(1100, 800));

		this.add(center, BorderLayout.CENTER);
		center.setLayout(new BorderLayout(0, 0));
		
		lblNewLabel = new JLabel();
		lblNewLabel.setIcon(new ImageIcon(HomePanel.class.getResource("/images/library_books.jpg")));
		center.add(lblNewLabel);
	}
}
