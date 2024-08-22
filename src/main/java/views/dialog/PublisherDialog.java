package views.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import models.PublisherModel;
import service.PublisherService;
import views.panel.PublisherPanel;

public class PublisherDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtName, txtAddress, txtPhone;
	private JButton btnOK, btnCancel;
	private PublisherPanel publisherPanel;
	String act;
	int publisherID;

	public PublisherDialog() {
		txtName = new JTextField();
		txtAddress = new JTextField();
		txtPhone = new JTextField();
		setupContent("ityutyu");
	}

	public PublisherDialog(PublisherPanel publisherPanel, JFrame owner, String title, boolean modal, String act) {
		super(owner, title.toUpperCase(), modal);
		ImageIcon img = new FlatSVGIcon("icons/add2.svg");
		setIconImage(img.getImage());
		
		this.publisherPanel = publisherPanel;
		this.act = act;

		txtName = new JTextField();

		txtAddress = new JTextField();

		txtPhone = new JTextField();

		setupContent(title);
	}

	public PublisherDialog(PublisherPanel publisherPanel, JFrame owner, String title, boolean modal, String act, PublisherModel publisherModel) {
		super(owner, title.toUpperCase(), modal);
		this.publisherPanel = publisherPanel;
		this.act = act;
		this.publisherID = publisherModel.getPublisherID();

		txtName = new JTextField();
		txtName.setText(publisherModel.getPublisherName());
		txtAddress = new JTextField();
		txtAddress.setText(publisherModel.getAddress());

		txtPhone = new JTextField();
		txtPhone.setText(publisherModel.getPhone());
		
		if (act.equals("detail")) {
			ImageIcon img = new FlatSVGIcon("icons/detail.svg");
			setIconImage(img.getImage());
			
			txtName.setEnabled(false);
			txtAddress.setEnabled(false);
			txtPhone.setEnabled(false);
		} else {
			ImageIcon img = new FlatSVGIcon("icons/edit.svg");
			setIconImage(img.getImage());
		}
		setupContent(title);
	}

	private void setupContent(String title) {
		setBounds(100, 100, 385, 328);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(publisherPanel);
		
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
			pnlMain.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
			contentPanel.add(pnlMain, BorderLayout.CENTER);
			pnlMain.setLayout(null);

			txtName.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtName.setColumns(10);
			txtName.setBounds(125, 12, 220, 40);
			txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên nhà xuất bản...");
			txtName.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			pnlMain.add(txtName);
			
			txtAddress.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtAddress.setColumns(10);
			txtAddress.setBounds(125, 75, 220, 40);
			txtAddress.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập địa chỉ...");
			txtAddress.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			pnlMain.add(txtAddress);

			txtPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtPhone.setColumns(10);
			txtPhone.setBounds(125, 139, 220, 40);
			txtPhone.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập số điện thoại...");
			txtPhone.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			((AbstractDocument) txtPhone.getDocument()).setDocumentFilter(new NumericDocumentFilter());
			pnlMain.add(txtPhone);

			JLabel lblNewLabel = new JLabel("Tên nhà XB:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 12, 105, 40);
			pnlMain.add(lblNewLabel);

			JLabel lblThLoi = new JLabel("Địa chỉ:");
			lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi.setBounds(10, 74, 105, 40);
			pnlMain.add(lblThLoi);

			JLabel lblNhXutBn = new JLabel("Số điện thoại:");
			lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNhXutBn.setBounds(10, 138, 105, 40);
			pnlMain.add(lblNhXutBn);
		}
		{
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
			String phone = txtPhone.getText();

			if (isValidate(name)) {

				if (action.equals("insert")) {
					PublisherModel publisherModel = new PublisherModel(name, address, phone);
					PublisherService.getInstance().insert(publisherModel);
				} else if (action.equals("update")) {
					PublisherModel publisherModel = new PublisherModel(publisherID, name, address, phone);
					PublisherService.getInstance().update(publisherModel);
				}

				dispose();
				publisherPanel.reloadData();
			}
		} catch (NumberFormatException | HeadlessException e) {
			e.printStackTrace();
		}
	}

	private boolean isValidate(String name) {
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhà xuất bản");
			return false;
		}
		return true;
	}

	private static class NumericDocumentFilter extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
			StringBuilder sb = new StringBuilder(string);
			for (int i = sb.length() - 1; i >= 0; i--) {
				char ch = sb.charAt(i);
				if (!Character.isDigit(ch)) {
					sb.deleteCharAt(i);
				}
			}
			super.insertString(fb, offset, sb.toString(), attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			StringBuilder sb = new StringBuilder(text);
			for (int i = sb.length() - 1; i >= 0; i--) {
				char ch = sb.charAt(i);
				if (!Character.isDigit(ch)) {
					sb.deleteCharAt(i);
				}
			}
			super.replace(fb, offset, length, sb.toString(), attrs);
		}
	}
}
