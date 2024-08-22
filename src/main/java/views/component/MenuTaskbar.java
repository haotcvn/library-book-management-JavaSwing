package views.component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import views.LoginJFrame;
import views.MainJFrame2;
import views.MainJFrame;
import views.panel.BookPanel;
import views.panel.BorrowPanel;
import views.panel.CategoryPanel;
import views.panel.HomePanel;
import views.panel.PublisherPanel;
import views.panel.ReaderPanel;
import views.panel.ReturnPanel;
import views.panel.StatisticalPanel;

public class MenuTaskbar extends JPanel {
	private static final long serialVersionUID = 1L;
	HomePanel home;
	//AccountPanel account;
	// Product product;

	String[][] getSt = { 
		{"Trang chủ", "home.svg", "home" },
		{"Sách", "book.svg", "product"},
        {"Thể loại", "books.svg", "home"},
        {"Nhà xuất bản", "building.svg", "home"},
        {"Độc giả", "reader.svg", "account"},
        {"Phiếu mượn", "borrow.svg", "account"},
        {"Phiếu trả", "return.svg", "customer"},
        {"Thống kê", "chart_pie.svg", "account"}, 
		{"Đăng xuất", "sign_out.svg", "logout" }, 
	};

	MainJFrame main;
	//AccountDTO accountModel;
	public ItemTaskbar[] listItem;

	JLabel lblTenNhomQuyen, lblUsername;
	JScrollPane scrollPane;

	JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2, bar3, bar4;

//	Color FontColor = new Color(96, 125, 139);
//	Color DefaultColor = new Color(255, 255, 255);
//	Color HowerFontColor = new Color(1, 87, 155);
//	Color HowerBackgroundColor = new Color(187, 222, 251);
	
	Color FontColor = new Color(96, 125, 139);
	Color DefaultColor = new Color(255, 255, 255);
	Color HowerFontColor = new Color(1, 87, 155);
	Color HowerBackgroundColor = CustomColor.PRIMARY;
	

//	private ArrayList<PermssionCategoryDTO> list;
//	private PermissionDTO permission;
//	public UserDTO user;
	JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

	
	public MenuTaskbar() {
		initComponent();
	}

	public MenuTaskbar(MainJFrame main) {
		this.main = main;
		initComponent();
	}

//	public MenuTaskbar(MainJFrame main, AccountDTO accountModel) {
//		this.main = main;
//		this.accountModel = accountModel;
//		this.permission = PermissionDAO.getInstance().selectById(accountModel.getPermissionID());
//		this.user = UserDAO.getInstance().selectById(Integer.toString(accountModel.getUserID()));
//		list = PermissionCategoryDAO.getInstance().selectAllById(accountModel.getPermissionID());
//		initComponent();
//	}

	private void initComponent() {
		listItem = new ItemTaskbar[getSt.length];
		this.setOpaque(true);
		this.setBackground(DefaultColor);
		this.setLayout(new BorderLayout(0, 0));

		// bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
		pnlTop = new JPanel();
		pnlTop.setPreferredSize(new Dimension(250, 80));
		pnlTop.setBackground(DefaultColor);
		pnlTop.setLayout(new BorderLayout(0, 0));
		this.add(pnlTop, BorderLayout.NORTH);

		JPanel infor = new JPanel();
		//infor.setOpaque(false);
		infor.setLayout(new BorderLayout(0, 0));
		infor.setBackground(CustomColor.PRIMARY);
		pnlTop.add(infor, BorderLayout.CENTER);

		headerMeneu(infor);

		bar1 = new JPanel();
		bar1.setBackground(new Color(204, 214, 219));
		bar1.setPreferredSize(new Dimension(1, 0));
		pnlTop.add(bar1, BorderLayout.EAST);

		bar2 = new JPanel();
		bar2.setBackground(new Color(204, 214, 219));
		bar2.setPreferredSize(new Dimension(0, 1));
		pnlTop.add(bar2, BorderLayout.SOUTH);

		pnlCenter = new JPanel();
		pnlCenter.setPreferredSize(new Dimension(230, 600));
		pnlCenter.setBackground(DefaultColor);
		pnlCenter.setLayout(new FlowLayout(0, 0, 5));

		bar3 = new JPanel();
		bar3.setBackground(new Color(204, 214, 219));
		bar3.setPreferredSize(new Dimension(1, 1));
		this.add(bar3, BorderLayout.EAST);

		scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(5, 10, 0, 0));
		this.add(scrollPane, BorderLayout.CENTER);

		pnlBottom = new JPanel();
		pnlBottom.setBorder(new EmptyBorder(0, 10, 5, 20));
		pnlBottom.setPreferredSize(new Dimension(250, 50));
		pnlBottom.setBackground(DefaultColor);
		pnlBottom.setLayout(new BorderLayout(0, 0));

//		bar4 = new JPanel();
//		bar4.setBackground(new Color(204, 214, 219));
//		bar4.setPreferredSize(new Dimension(1, 1));
//		pnlBottom.add(bar4, BorderLayout.EAST);

		this.add(pnlBottom, BorderLayout.SOUTH);

		for (int i = 0; i < getSt.length; i++) {
			if (i + 1 == getSt.length) {
				listItem[i] = new ItemTaskbar(getSt[i][1], getSt[i][0]);
				pnlBottom.add(listItem[i]);
			} else {
				listItem[i] = new ItemTaskbar(getSt[i][1], getSt[i][0]);
				pnlCenter.add(listItem[i]);
				if (i != 0) {
//					if (!checkRole(getSt[i][2])) {
//						listItem[i].setVisible(false);
//					}
				}
			}
		}

		listItem[0].setBackground(HowerBackgroundColor);
		listItem[0].setForeground(HowerFontColor);
		listItem[0].lblContent.setForeground(CustomColor.WHITE);
		listItem[0].isSelected = true;

		for (int i = 0; i < getSt.length; i++) {
			final int currentIndex = i;
			listItem[i].addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent evt) {
					pnlMenuTaskbarMousePress(evt);
					
					handleMousePressed(currentIndex);
				}
			});
		}
	}
	
	// Hàm xử lý sự kiện mousePressed
	private void handleMousePressed(int index) {
	    switch (index) {
	        case 0 -> main.setPanel(new HomePanel());
	        case 1 -> main.setPanel(new BookPanel());
	        case 2 -> main.setPanel(new CategoryPanel());
	        case 3 -> main.setPanel(new PublisherPanel());
	        case 4 -> main.setPanel(new ReaderPanel());
	        case 5 -> main.setPanel(new BorrowPanel());
	        case 6 -> main.setPanel(new ReturnPanel());
	        case 7 -> main.setPanel(new StatisticalPanel());
	        case 8 -> {
	            int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đăng xuất?", "Thông báo",
	                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
	            if (input == JOptionPane.OK_OPTION) {
	            	LoginJFrame login = new LoginJFrame();
					main.dispose();
					login.setVisible(true);
	            }
	        }
	    }
	}
	
	public void pnlMenuTaskbarMousePress(MouseEvent evt) {
		for (int i = 0; i < getSt.length; i++) {
			if (evt.getSource() == listItem[i]) {
				listItem[i].isSelected = true;
				listItem[i].setBackground(HowerBackgroundColor);
				listItem[i].setForeground(HowerFontColor);
			} else {
				listItem[i].isSelected = false;
				listItem[i].setBackground(DefaultColor);
				listItem[i].setForeground(FontColor);
				listItem[i].lblContent.setForeground(CustomColor.BLACK);
			}
		}
	}
	
//	public boolean checkRole(String s) {
//		boolean check = false;
//		for (int i = 0; i < list.size(); i++) {
//			if (list.get(i).getAct().equals("view")) {
//				if (s.equals(list.get(i).getCategoryID())) {
//					check = true;
//					return check;
//				}
//			}
//		}
//		return check;
//	}

//	public void resetChange() {
//		this.user = UserDAO.getInstance().selectById(String.valueOf(user.getUserID()));
//	}

	public void headerMeneu (JPanel infor) {
		JPanel pnlIcon = new JPanel(new FlowLayout());
		pnlIcon.setPreferredSize(new Dimension(60, 0));
		pnlIcon.setOpaque(false);
		infor.add(pnlIcon, BorderLayout.WEST);
		JLabel lblIcon = new JLabel();
		lblIcon.setPreferredSize(new Dimension(50, 70));
		lblIcon.setIcon(new FlatSVGIcon("./icons/man.svg"));
//		if (user.getGender() == 0) {
//			lblIcon.setIcon(new FlatSVGIcon("./icons/man_50px.svg"));
//		} else {
//			lblIcon.setIcon(new FlatSVGIcon("./icons/women_50px.svg"));
//		}
		pnlIcon.add(lblIcon);

		JPanel pnlInfo = new JPanel();
		pnlInfo.setOpaque(false);
		pnlInfo.setLayout(new FlowLayout(0, 10, 5));
		pnlInfo.setBorder(new EmptyBorder(15, 0, 0, 50));// Thông tin
		infor.add(pnlInfo, BorderLayout.CENTER);

		//lblUsername = new JLabel(user.getName());
		lblUsername = new JLabel("Trần Công Hào");
		lblUsername.setForeground(CustomColor.WHITE);
		lblUsername.putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
		pnlInfo.add(lblUsername);

//		lblTenNhomQuyen = new JLabel(permission.getName());
//		lblTenNhomQuyen.putClientProperty("FlatLaf.style", "font: 120% $light.font");
//		lblTenNhomQuyen.setForeground(Color.GRAY);
//		pnlInfo.add(lblTenNhomQuyen);

//		lblIcon.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mousePressed(MouseEvent evt) {
//				MyAccount ma = new MyAccount(owner, MenuTaskbar.this, "Chỉnh sửa thông tin tài khoản", true);
//			}
//		});
	}
}
