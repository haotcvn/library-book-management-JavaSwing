package views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import models.AccountModel;
import models.SessionModel;
import service.AccountService;
import service.SessionService;
import utilities.DateFormat;
import utilities.Info;

/**
 * @author HaoTC
 */
public class LoginJFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane, pnlTop, pnlCenter, pnlEnter, pnlButton;
	private JLabel lblTitle, lblDate, lblTime, lblNewLabel_2;
	private JTextField txtUsername;
	private JPasswordField txtPassword;
	private JButton btnLogin;

	public static void main(String[] args) {
		try {
			LoginJFrame frame = new LoginJFrame();
			frame.setVisible(true);
			//getTest();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LoginJFrame() {
		setResizable(false);
		try {
			FlatLightLaf.setup();
			UIManager.put("Button.arc", 20);
			UIManager.put("ProgressBar.arc", 999);
			UIManager.put("TextComponent.arc", 10);
			UIManager.put("Component.arc", 10);
			UIManager.put("Component.innerFocusWidth", 2);
			System.setProperty("flatlaf.menuBarEmbedded", "false");
		} catch (Exception e) {
			e.printStackTrace();
		}
		setTitle("ĐĂNG NHẬP VÀO HỆ THỐNG");
		ImageIcon img = new FlatSVGIcon("icons/books_library_icon.svg");
		setIconImage(img.getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setBounds(100, 100, 600, 400);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setPreferredSize(new Dimension(600, 400));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		setupTimmer();

		pnlTop = new JPanel();
		pnlTop.setBackground(CustomColor.PRIMARY);
		pnlTop.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnlTop.setPreferredSize(new Dimension(10, 50));
		contentPane.add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(0, 0));

		lblDate = new JLabel("Ngày");
		lblDate.setIcon(new ImageIcon(LoginJFrame.class.getResource("/icons/ic_calendar.png")));
		lblDate.setForeground(Color.WHITE);
		lblDate.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDate.setText(DateFormat.getCurrentDate());
		pnlTop.add(lblDate, BorderLayout.WEST);

		lblTime = new JLabel("Giờ");
		lblTime.setIcon(new ImageIcon(LoginJFrame.class.getResource("/icons/ic_time.png")));
		lblTime.setForeground(Color.WHITE);
		lblTime.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTime.setText(DateFormat.getCurrentTime());
		pnlTop.add(lblTime, BorderLayout.EAST);

		pnlCenter = new JPanel();
		pnlCenter.setBorder(new EmptyBorder(20, 0, 0, 0));
		contentPane.add(pnlCenter, BorderLayout.CENTER);

		lblTitle = new JLabel("ĐĂNG NHẬP VÀO HỆ THỐNG");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTitle.setForeground(CustomColor.LAVENDER);
		pnlCenter.add(lblTitle);

		pnlEnter = new JPanel();
		pnlEnter.setPreferredSize(new Dimension(350, 160));
		pnlCenter.add(pnlEnter);
		pnlEnter.setLayout(null);

		txtUsername = new JTextField("admin");
		txtUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtUsername.setBounds(10, 20, 330, 40);
		txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(LoginJFrame.class.getResource("/icons/user.svg")));
		txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên đăng nhập");
		txtUsername.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		pnlEnter.add(txtUsername);

		txtPassword = new JPasswordField("123");
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtPassword.setBounds(10, 80, 330, 40);
		txtPassword.putClientProperty(FlatClientProperties.TEXT_FIELD_LEADING_ICON, new FlatSVGIcon(LoginJFrame.class.getResource("/icons/lock.svg")));
		txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập mật khẩu");
		txtPassword.putClientProperty(FlatClientProperties.STYLE, "showRevealButton:true");
		pnlEnter.add(txtPassword);

		lblNewLabel_2 = new JLabel("Quên mật khẩu?");
		lblNewLabel_2.setPreferredSize(new Dimension(10, 10));
		lblNewLabel_2.setFont(new Font("Tahoma", Font.ITALIC, 13));
		lblNewLabel_2.setBounds(238, 136, 102, 13);
		pnlEnter.add(lblNewLabel_2);

		pnlButton = new JPanel();
		pnlButton.setBorder(new EmptyBorder(5, 0, 5, 0));
		pnlButton.setPreferredSize(new Dimension(400, 60));
		pnlCenter.add(pnlButton);

		btnLogin = new JButton("ĐĂNG NHẬP");
		btnLogin.setIcon(new FlatSVGIcon(LoginJFrame.class.getResource("/icons/sign_in.svg")));
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setBackground(CustomColor.PRIMARY);
		btnLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLogin.setForeground(CustomColor.WHITE);
		btnLogin.setPreferredSize(new Dimension(160, 40));
		btnLogin.addActionListener(this);

		pnlButton.add(btnLogin);
	}

	private boolean isValidate(String username, String password) {
		if (username.isEmpty() | password.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin", "Thông báo",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}

	@SuppressWarnings("deprecation")
	private void performLogin() {
		String username = txtUsername.getText();
		String password = txtPassword.getText();
		if (isValidate(username, password)) {
			AccountModel account = AccountService.getInstance().login(username);
			if (account == null) {
				JOptionPane.showMessageDialog(this, "Tài khoản của bạn không tồn tại trên hệ thống", "Thông báo",
						JOptionPane.WARNING_MESSAGE);
			} else {
				if (account.getStatus() == 0) {
					JOptionPane.showMessageDialog(this, "Tài khoản của bạn đang bị khóa", "Cảnh báo!",
							JOptionPane.WARNING_MESSAGE);
				} else {
					if (account.getPassword().equals(password)) {
						try {
							this.dispose();
							MainJFrame main = new MainJFrame();
							main.setVisible(true);
						} catch (Exception ex) {
							Logger.getLogger(LoginJFrame.class.getName()).log(Level.SEVERE, null, ex);
						}
					} else {
						JOptionPane.showMessageDialog(this, "Vui lòng kiểm tra lại mật khẩu", "Thông báo",
								JOptionPane.WARNING_MESSAGE);
					}
				}

			}
		}
	}

	private void setupTimmer() {
		Timer timer = new Timer(1000, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String currentTime = DateFormat.getCurrentTime();
				lblTime.setText(currentTime);
			}
		});
		timer.start();
	}
	
	private static void getTest() throws UnknownHostException {
		SessionService.getInstance().insert(new SessionModel(Info.getName(), Info.getIP(), DateFormat.getCurrentDateTimeToSQL(), 1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnLogin) {
			performLogin();
		}

	}
}
