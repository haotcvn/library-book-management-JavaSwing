package views.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import models.ReaderModel;
import service.ReaderService;
import views.panel.ReaderPanel;

public class ReaderDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JTextField txtName, txtAddress, txtPhoneNumber;
	private JComboBox<String> cboGender;
	private JButton btnOK, btnCancel;
	private ReaderPanel readerPanel;
	String act;
	int readerID;

	public ReaderDialog() {
		txtName = new JTextField();
		txtAddress = new JTextField();
		txtPhoneNumber = new JTextField();
		cboGender = new JComboBox<>(new String[] { "Nam", "Nữ", "LGBT", "Khác" });
		setupContent("");
	}

	public ReaderDialog(ReaderPanel readerPanel, JFrame owner, String title, boolean modal, String act) {
		super(owner, title.toUpperCase(), modal);
		ImageIcon img = new FlatSVGIcon("icons/add2.svg");
		setIconImage(img.getImage());

		this.readerPanel = readerPanel;
		this.act = act;

		txtName = new JTextField();
		txtAddress = new JTextField();
		txtPhoneNumber = new JTextField();
		cboGender = new JComboBox<>(new String[] { "Nam", "Nữ", "LGBT", "Khác" });

		setupContent(title);
	}

	public ReaderDialog(ReaderPanel readerPanel, JFrame owner, String title, boolean modal, String act,
			ReaderModel readerModel) {
		super(owner, title.toUpperCase(), modal);
		this.readerPanel = readerPanel;
		this.act = act;
		this.readerID = readerModel.getReaderID();

		txtName = new JTextField();
		txtName.setText(readerModel.getName());

		txtAddress = new JTextField();
		txtAddress.setText(readerModel.getAddress());

		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setText(readerModel.getPhoneNumber());

		cboGender = new JComboBox<>(new String[] { "Nam", "Nữ", "LGBT", "Khác" });
		cboGender.setSelectedItem(readerModel.getGender());

		if (act.equals("detail")) {
			ImageIcon img = new FlatSVGIcon("icons/detail.svg");
			setIconImage(img.getImage());

			txtName.setEnabled(false);
			txtAddress.setEnabled(false);
			txtPhoneNumber.setEnabled(false);
			cboGender.setEnabled(false);
		} else {
			ImageIcon img = new FlatSVGIcon("icons/edit.svg");
			setIconImage(img.getImage());
		}

		setupContent(title);
	}

	private void setupContent(String title) {
		setBounds(100, 100, 748, 346);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(readerPanel);

		JPanel pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(10, 0, 10, 0));
		pnlTitle.setBackground(CustomColor.PRIMARY);
		getContentPane().add(pnlTitle, BorderLayout.NORTH);
		{
			JLabel lblTitle = new JLabel(title.toUpperCase());
			pnlTitle.add(lblTitle);
			lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
			lblTitle.setForeground(CustomColor.WHITE);
		}

		JPanel contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel pnlMain = new JPanel();
			pnlMain.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
			contentPanel.add(pnlMain, BorderLayout.CENTER);
			pnlMain.setLayout(null);

			txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtName.setColumns(10);
			txtName.setBounds(125, 12, 220, 40);
			pnlMain.add(txtName);

			txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtAddress.setColumns(10);
			txtAddress.setBounds(125, 74, 220, 40);
			pnlMain.add(txtAddress);

			txtPhoneNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtPhoneNumber.setColumns(10);
			txtPhoneNumber.setBounds(492, 12, 220, 40);
			pnlMain.add(txtPhoneNumber);

			cboGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cboGender.setBounds(492, 74, 220, 40);
			pnlMain.add(cboGender);

			JLabel lblNewLabel = new JLabel("Họ và tên:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 12, 105, 40);
			pnlMain.add(lblNewLabel);

			JLabel lblThLoi = new JLabel("Địa chỉ:");
			lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi.setBounds(10, 74, 105, 40);
			pnlMain.add(lblThLoi);

			JLabel lblNhXutBn = new JLabel("Số điện thoại:");
			lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNhXutBn.setBounds(377, 12, 105, 40);
			pnlMain.add(lblNhXutBn);

			JLabel lblThLoi_1 = new JLabel("Giới tính:");
			lblThLoi_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi_1.setBounds(377, 74, 105, 40);
			pnlMain.add(lblThLoi_1);
		}

		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.CENTER));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		{
			btnOK = new JButton("Đồng ý");
			btnOK.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnOK.addActionListener(this);
			buttonPane.add(btnOK);
			getRootPane().setDefaultButton(btnOK);
		}
		{
			btnCancel = new JButton("Hủy");
			btnCancel.setFont(new Font("Tahoma", Font.BOLD, 14));
			btnCancel.addActionListener(this);
			buttonPane.add(btnCancel);
		}

		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnOK) {
			if (act.equals("insert")) {
				performAction("insert");
			} else if (act.equals("update")) {
				performAction("update");
			}
		} else if (e.getSource() == btnCancel) {
			dispose();
		}
	}

	private void performAction(String action) {
		try {
			String name = txtName.getText();
			String address = txtAddress.getText();
			String phoneNumber = txtPhoneNumber.getText();
			String gender = cboGender.getSelectedItem().toString();

			if (isValidate(name, address, phoneNumber)) {
				if (action.equals("insert")) {
					ReaderModel readerModel = new ReaderModel(name, address, phoneNumber, gender);
					ReaderService.getInstance().insert(readerModel);
				} else if (action.equals("update")) {
					ReaderModel readerModel = new ReaderModel(readerID, name, address, phoneNumber, gender);
					ReaderService.getInstance().update(readerModel);
				}

				dispose();
				readerPanel.reloadData();
			}
		} catch (NumberFormatException | HeadlessException e) {
			e.printStackTrace();
		}
	}

	private boolean isValidate(String name, String address, String phoneNumber) {
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên độc giả");
			return false;
		}
		if (address.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập địa chỉ");
			return false;
		}
		if (phoneNumber.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số điện thoại");
			return false;
		}
		return true;
	}
}
