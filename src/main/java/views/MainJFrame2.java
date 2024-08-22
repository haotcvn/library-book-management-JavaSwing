package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import views.panel.BookPanel;
import views.panel.BorrowPanel;
import views.panel.CategoryPanel;
import views.panel.HomePanel;
import views.panel.PublisherPanel;
import views.panel.ReaderPanel;
import views.panel.ReturnPanel;

public class MainJFrame2 extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, pnlView, pnlRoot, pnlTop, pnlCenter;
	private JButton btnHome, btnBook, btnReader, btnCategory, btnReturn, btnBorrow, btnPublisher, btnThngK;
	private JLabel lblUsername;
	private JPanel pnlBottom;
	private JButton btnLogout;
	boolean isSelected;

	public MainJFrame2() {
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
		setTitle("HỆ THỐNG QUẢN LÝ SÁCH CỦA THƯ VIỆN");
		ImageIcon img = new FlatSVGIcon("icons/books_library_icon.svg");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(new Dimension(1300, 700));
		setLocationRelativeTo(null);
		contentPane = new JPanel();

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

		pnlView = new JPanel();
		pnlView.setPreferredSize(new Dimension(280, 10));
		pnlView.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		contentPane.add(pnlView, BorderLayout.WEST);
		pnlView.setLayout(new BorderLayout(0, 0));

		pnlTop = new JPanel();
		pnlTop.setBackground(CustomColor.PRIMARY);
		pnlTop.setPreferredSize(new Dimension(10, 70));
		pnlView.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(0, 0));

		lblUsername = new JLabel("Trần Công Hào");
		lblUsername.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblUsername.setForeground(CustomColor.WHITE);
		lblUsername.setHorizontalAlignment(SwingConstants.CENTER);
		lblUsername.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/avatar.svg")));
		pnlTop.add(lblUsername);

		pnlCenter = new JPanel();
		pnlCenter.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlCenter.setBackground(new Color(255, 255, 255));
		pnlView.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(null);

		btnHome = new JButton("Trang chủ");
		btnHome.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnHome.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHome.setBounds(10, 10, 256, 50);
		btnHome.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/home.svg")));
		btnHome.addMouseListener(this);
		pnlCenter.add(btnHome);

		btnBook = new JButton("Quản lý sách");
		btnBook.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBook.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBook.setBounds(10, 70, 256, 50);
		btnBook.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/book.svg")));
		btnBook.addMouseListener(this);
		pnlCenter.add(btnBook);

		pnlRoot = new JPanel();
		pnlRoot.setLayout(new BorderLayout(0, 0));
		contentPane.add(pnlRoot, BorderLayout.CENTER);

		btnCategory = new JButton("Quản lý thể loại");
		btnCategory.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnCategory.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCategory.setBounds(10, 194, 256, 50);
		btnCategory.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/books.svg")));
		btnCategory.addMouseListener(this);
		pnlCenter.add(btnCategory);

		btnPublisher = new JButton("Quản lý nhà xuất bản");
		btnPublisher.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnPublisher.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnPublisher.setBounds(10, 385, 256, 50);
		btnPublisher.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/building.svg")));
		btnPublisher.addMouseListener(this);
		pnlCenter.add(btnPublisher);

		btnBorrow = new JButton("Quản lý phiếu mượn");
		btnBorrow.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBorrow.setBounds(10, 317, 256, 50);
		btnBorrow.addMouseListener(this);
		pnlCenter.add(btnBorrow);

		btnReturn = new JButton("Quản lý phiếu trả");
		btnReturn.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReturn.setBounds(10, 255, 256, 50);
		btnReturn.addMouseListener(this);
		pnlCenter.add(btnReturn);

		btnReader = new JButton("Quản lý độc giả");
		btnReader.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/exit.svg")));
		btnReader.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnReader.setBounds(10, 132, 256, 50);
		btnReader.addMouseListener(this);
		pnlCenter.add(btnReader);

		btnThngK = new JButton("Thống kê\r\n");
		btnThngK.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThngK.setBounds(10, 447, 256, 50);
		btnThngK.addMouseListener(this);
		pnlCenter.add(btnThngK);

		pnlBottom = new JPanel();
		pnlBottom.setPreferredSize(new Dimension(10, 70));
		pnlView.add(pnlBottom, BorderLayout.SOUTH);
		pnlBottom.setLayout(null);

		btnLogout = new JButton("Đăng xuất");
		btnLogout.setIcon(new FlatSVGIcon(MainJFrame2.class.getResource("/icons/exit.svg")));
		btnLogout.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogout.setBounds(10, 10, 256, 50);
		btnLogout.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogout.addActionListener(l -> {
			int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (input == JOptionPane.OK_OPTION) {
				LoginJFrame login = new LoginJFrame();
				this.dispose();
				login.setVisible(true);
			}
		});
		pnlBottom.add(btnLogout);
		setPanel(new HomePanel());

	}

	public void setPanel(JPanel jPanel) {
		pnlRoot.removeAll();
		pnlRoot.add(jPanel).setVisible(true);
		pnlRoot.repaint();
		pnlRoot.validate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == btnHome) {
			setPanel(new HomePanel());
		} else if (e.getSource() == btnBook) {
			setPanel(new BookPanel());
		} else if (e.getSource() == btnReader) {
			setPanel(new ReaderPanel());
		} else if (e.getSource() == btnCategory) {
			setPanel(new CategoryPanel());
		} else if (e.getSource() == btnReturn) {
			setPanel(new ReturnPanel());
		} else if (e.getSource() == btnBorrow) {
			setPanel(new BorrowPanel());
		} else if (e.getSource() == btnPublisher) {
			setPanel(new PublisherPanel());
		} else if (e.getSource() == btnLogout) {
			int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (input == JOptionPane.OK_OPTION) {
				LoginJFrame login = new LoginJFrame();
				this.dispose();
				login.setVisible(true);
			}
		} else {
			setPanel(new HomePanel());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}
}
