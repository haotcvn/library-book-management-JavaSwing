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

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import models.CategoryModel;
import service.CategoryService;
import views.panel.CategoryPanel;

public class CategoryDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtName;
	private JButton btnOK, btnCancel;
	private CategoryPanel categoryPanel;
	String act;
	int categoryId;
	public CategoryDialog() {
		txtName = new JTextField();
		setupContent("TIÊU ĐỀ");
	}

	public CategoryDialog(CategoryPanel categoryPanel, JFrame owner, String title, boolean modal, String act) {
		super(owner, title.toUpperCase(), modal);
		ImageIcon img = new FlatSVGIcon("icons/add2.svg");
		setIconImage(img.getImage());
		
		this.categoryPanel = categoryPanel;
		this.act = act;

		txtName = new JTextField();
		setupContent(title);
	}

	public CategoryDialog(CategoryPanel categoryPanel, JFrame owner, String title, boolean modal, String act, CategoryModel categoryModel) {
		super(owner, title.toUpperCase(), modal);
		this.categoryPanel = categoryPanel;
		this.act = act;
		this.categoryId = categoryModel.getCategoryID();

		txtName = new JTextField();
		txtName.setText(categoryModel.getCategoryName());
		
		if (act.equals("detail")) {
			ImageIcon img = new FlatSVGIcon("icons/detail.svg");
			setIconImage(img.getImage());
			
			txtName.setEnabled(false);
		} else {
			ImageIcon img = new FlatSVGIcon("icons/edit.svg");
			setIconImage(img.getImage());
		}
		setupContent(title);
	}

	private void setupContent(String title) {
		setBounds(100, 100, 385, 200);
		getContentPane().setLayout(new BorderLayout());
		setResizable(false);
		setLocationRelativeTo(categoryPanel);
		
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
			txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên thể loại...");
			txtName.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			pnlMain.add(txtName);

			JLabel lblNewLabel = new JLabel("Tên thể loại:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 12, 105, 40);
			pnlMain.add(lblNewLabel);
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
			if (isValidate(name)) {
				if (action.equals("insert")) {
					CategoryModel categoryModel = new CategoryModel(name);
					CategoryService.getInstance().insert(categoryModel);
				} else if (action.equals("update")) {
					CategoryModel categoryModel = new CategoryModel(categoryId, name);
					CategoryService.getInstance().update(categoryModel);
				}

				dispose();
				categoryPanel.reloadData();
			}
		} catch (NumberFormatException | HeadlessException e) {
			e.printStackTrace();
		}
	}

	private boolean isValidate(String name) {
		if (name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên thể loại");
			return false;
		}
		return true;
	}
}
