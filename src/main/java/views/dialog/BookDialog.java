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
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;
import models.BookModel;
import models.CategoryModel;
import models.PublisherModel;
import service.BookService;
import service.CategoryService;
import service.PublisherService;
import views.panel.BookPanel;
import java.awt.Color;

public class BookDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JComboBox<Object> cboCategory;
	private JComboBox<Object> cboPublisher;
	private JTextField txtName, txtAuthor, txtYearPublisher;
	private JSpinner txtNumberPages;
	private JButton btnOK, btnCancel;
	private BookPanel bookPanel;
	String act;
	int bookID;
	List<CategoryModel> listCategory = CategoryService.getInstance().selectAll();
	List<PublisherModel> listPublisher = PublisherService.getInstance().selectAll();

	public BookDialog() {
		txtName = new JTextField();

		cboCategory = new JComboBox<Object>();
		loadDataComboBoxCat(listCategory);

		cboPublisher = new JComboBox<Object>();
		loadDataComboBoxPublisher(listPublisher);

		txtAuthor = new JTextField();

		txtYearPublisher = new JTextField();

		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
		txtNumberPages = new JSpinner(model);
		setupContent("");
	}

	public BookDialog(BookPanel bookPanel, JFrame owner, String title, boolean modal, String act) {
		super(owner, title.toUpperCase(), modal);
		ImageIcon img = new FlatSVGIcon("icons/add2.svg");
		setIconImage(img.getImage());
		
		this.bookPanel = bookPanel;
		this.act = act;

		txtName = new JTextField();

		cboCategory = new JComboBox<Object>();
		loadDataComboBoxCat(listCategory);

		cboPublisher = new JComboBox<Object>();
		loadDataComboBoxPublisher(listPublisher);

		txtAuthor = new JTextField();

		txtYearPublisher = new JTextField();

		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
		txtNumberPages = new JSpinner(model);

		setupContent(title);
	}

	public BookDialog(BookPanel bookPanel, JFrame owner, String title, boolean modal, String act, BookModel bookModel) {
		super(owner, title.toUpperCase(), modal);
		this.bookPanel = bookPanel;
		this.act = act;
		this.bookID = bookModel.getBookID();

		txtName = new JTextField();
		txtName.setText(bookModel.getName());

		cboCategory = new JComboBox<Object>();
		loadDataComboBoxCat(listCategory);
		setSelectedCombobox(cboCategory, bookModel.getCategoryName());

		cboPublisher = new JComboBox<Object>();
		loadDataComboBoxPublisher(listPublisher);
		setSelectedCombobox(cboPublisher, bookModel.getPublisherName());

		txtAuthor = new JTextField();
		txtAuthor.setText(bookModel.getAuthor());

		txtYearPublisher = new JTextField();
		txtYearPublisher.setText(bookModel.getYearPublisher() + "");

		SpinnerNumberModel model = new SpinnerNumberModel(0, 0, null, 1);
		txtNumberPages = new JSpinner(model);
		txtNumberPages.setValue(bookModel.getNumberPages());
		
		if (act.equals("detail")) {
			ImageIcon img = new FlatSVGIcon("icons/detail.svg");
			setIconImage(img.getImage());
			
			txtName.setEnabled(false);
			cboCategory.setEnabled(false);
			cboPublisher.setEnabled(false);
			txtAuthor.setEnabled(false);
			txtYearPublisher.setEnabled(false);
			txtNumberPages.setEnabled(false);
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
		setLocationRelativeTo(bookPanel);
		

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
			txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên sách...");
			txtName.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			pnlMain.add(txtName);
			
			txtAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtAuthor.setColumns(10);
			txtAuthor.setBounds(492, 12, 220, 40);
			txtAuthor.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập tên tác giả...");
			txtAuthor.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			pnlMain.add(txtAuthor);

			txtYearPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtYearPublisher.setColumns(10);
			txtYearPublisher.setBounds(492, 75, 220, 40);
			txtYearPublisher.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Nhập năm xuất bản...");
			txtYearPublisher.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
			((AbstractDocument) txtYearPublisher.getDocument()).setDocumentFilter(new NumericDocumentFilter());
			pnlMain.add(txtYearPublisher);

			cboCategory.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cboCategory.setBounds(125, 74, 220, 40);
			pnlMain.add(cboCategory);

			cboPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cboPublisher.setBounds(125, 138, 220, 40);
			pnlMain.add(cboPublisher);

			JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) txtNumberPages.getEditor();
			editor.getTextField().setHorizontalAlignment(SwingConstants.LEFT);
			txtNumberPages.setFont(new Font("Tahoma", Font.PLAIN, 14));
			txtNumberPages.setBounds(492, 139, 220, 40);

			pnlMain.add(txtNumberPages);

			JLabel lblNewLabel = new JLabel("Tên sách:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 12, 105, 40);
			pnlMain.add(lblNewLabel);

			JLabel lblThLoi = new JLabel("Thể loại:");
			lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi.setBounds(10, 74, 105, 40);
			pnlMain.add(lblThLoi);

			JLabel lblNhXutBn = new JLabel("Nhà xuất bản:");
			lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNhXutBn.setBounds(10, 138, 105, 40);
			pnlMain.add(lblNhXutBn);

			JLabel lblThLoi_2 = new JLabel("Tác giả:");
			lblThLoi_2.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi_2.setBounds(377, 11, 105, 40);
			pnlMain.add(lblThLoi_2);

			JLabel lblThLoi_1 = new JLabel("Năm xuất bản:");
			lblThLoi_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi_1.setBounds(377, 74, 105, 40);
			pnlMain.add(lblThLoi_1);

			JLabel lblThLoi_1_1 = new JLabel("Số trang:");
			lblThLoi_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi_1_1.setBounds(377, 138, 105, 40);
			pnlMain.add(lblThLoi_1_1);
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

	public void loadDataComboBoxCat(List<CategoryModel> result) {
		cboCategory.removeAllItems();
		if (result != null) {
			result.forEach(o -> {
				cboCategory.addItem(o);
			});
		}
	}

	public void loadDataComboBoxPublisher(List<PublisherModel> result) {
		cboPublisher.removeAllItems();
		if (result != null) {
			result.forEach(o -> {
				cboPublisher.addItem(o);
			});
		}
	}

	public void setSelectedCombobox(JComboBox<Object> cbb, String selected) {
		for (int i = 0; i < cbb.getItemCount(); i++) {
			String item = cbb.getItemAt(i).toString();
			if (item.equalsIgnoreCase(selected)) {
				cbb.setSelectedIndex(i);
				break;
			}
		}
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
			int categoryId = ((CategoryModel) cboCategory.getSelectedItem()).getCategoryID();
			int publisherId = ((PublisherModel) cboPublisher.getSelectedItem()).getPublisherID();
			String bookName = txtName.getText();
			String author = txtAuthor.getText();
			int yearPublisher = parseTextField(txtYearPublisher);
			int numberPages = txtNumberPages.getValue().toString().isEmpty() ? 0
					: Integer.parseInt(txtNumberPages.getValue().toString());

			if (isValidate(categoryId, publisherId, bookName, author, yearPublisher, numberPages)) {

				if (action.equals("insert")) {
					BookModel bookModel = new BookModel(categoryId, publisherId, bookName, author, yearPublisher,
							numberPages);
					BookService.getInstance().insert(bookModel);
				} else if (action.equals("update")) {
					BookModel bookModel = new BookModel(bookID, categoryId, publisherId, bookName, author, yearPublisher,
							numberPages);
					BookService.getInstance().update(bookModel);
				}

				dispose();
				bookPanel.reloadData();
			}
		} catch (NumberFormatException | HeadlessException e) {
			e.printStackTrace();
		}
	}

	private int parseTextField(JTextField textField) {
		String text = textField.getText().trim();
		return text.isEmpty() ? 0 : Integer.parseInt(text);
	}

	private boolean isValidate(int cateporyID, int publisherID, String bookName, String author, int yearPublisher,
			int numberPages) {
		if (cateporyID <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại");
			return false;
		}
		if (publisherID <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn nhà xuất bản");
			return false;
		}
		if (bookName.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên sách");
			return false;
		}
		if (author.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên tác giả");
			return false;
		}
		if (yearPublisher <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập năm xuất bản");
			return false;
		}
		if (numberPages <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập số trang");
			return false;
		}
		return true;
	}

	// Lớp DocumentFilter để chỉ cho phép nhập kí tự số
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
