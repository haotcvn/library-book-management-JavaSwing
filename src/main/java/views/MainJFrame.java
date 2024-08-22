package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import views.component.MenuTaskbar;
import views.panel.HomePanel;

public class MainJFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	private MenuTaskbar menuTaskbar;
	private HomePanel home;
	Color MainColor = new Color(250, 250, 250);

//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					Test frame = new Test();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	public MainJFrame() {
		setupFrame();
		setupLookAndFeel();
		setupContentPanel();
	}

//	public Test(AccountDTO account) throws HeadlessException {
//		this.account = account;
//		setupFrame();
//        setupLookAndFeel();
//        setupContentPanel();
//	}

	private void setupLookAndFeel() {
		try {
			FlatIntelliJLaf.registerCustomDefaultsSource("style");
			FlatIntelliJLaf.setup();
			UIManager.put("Table.showVerticalLines", true);
			UIManager.put("Table.showHorizontalLines", true);
			UIManager.put("TextComponent.arc", 5);
			UIManager.put("ScrollBar.thumbArc", 999);
			UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
			UIManager.put("Button.iconTextGap", 10);
			UIManager.put("PasswordField.showRevealButton", true);
			UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
			UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
			UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
			UIManager.put("Table.rowHeight", 40);
			UIManager.put("TabbedPane.selectedBackground", Color.white);
			UIManager.put("TableHeader.height", 40);
			UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
			UIManager.put("TableHeader.background", new Color(242, 242, 242));
			UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
			UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void setupFrame() {
		setTitle("HỆ THỐNG QUẢN LÝ SÁCH CỦA THƯ VIỆN");
		ImageIcon img = new FlatSVGIcon("icons/books_library_icon.svg");
		setIconImage(img.getImage());
		setSize(new Dimension(1400, 800));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private void setupContentPanel() {
		setupMenuTaskbar();

		contentPane = new JPanel();
		contentPane.setBackground(MainColor);
		contentPane.setLayout(new BorderLayout(0, 0));

		this.add(contentPane, BorderLayout.CENTER);

		home = new HomePanel();
		contentPane.add(home).setVisible(true);
	}

	private void setupMenuTaskbar() {
		menuTaskbar = new MenuTaskbar(this);
//        if (account != null) {
//            menuTaskbar = new MenuTaskbar(this, account);
//        } else {
//            menuTaskbar = new MenuTaskbar(this);
//        }
		menuTaskbar.setPreferredSize(new Dimension(250, 1400));
		this.add(menuTaskbar, BorderLayout.WEST);
	}

	public void setPanel(JPanel jPanel) {
		contentPane.removeAll();
		contentPane.add(jPanel).setVisible(true);
		contentPane.repaint();
		contentPane.validate();
	}

}
