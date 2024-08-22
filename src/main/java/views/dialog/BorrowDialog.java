package views.dialog;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.toedter.calendar.JDateChooser;

import colors.CustomColor;
import models.BookModel;
import models.BorrowModel;
import models.ReaderModel;
import service.BookService;
import service.BorrowService;
import service.ReaderService;
import utilities.DateFormat;
import views.panel.BorrowPanel;

public class BorrowDialog extends JDialog implements ActionListener {

	private static final long serialVersionUID = 1L;

	private JComboBox<Object> cboBook, cboReader;
	private JButton btnOK, btnCancel;
	private BorrowPanel borrowPanel;
	private JDateChooser dateChooser;
	String act;
	int borrowID;
	List<ReaderModel> listReader = ReaderService.getInstance().selectAll();
	List<BookModel> listBook = BookService.getInstance().selectAll();

	public BorrowDialog() {
		cboBook = new JComboBox<Object>();
		loadDataComboBoxBook(listBook);
		
		cboReader = new JComboBox<Object>();
		loadDataComboBoxReader(listReader);
		
		dateChooser = new JDateChooser();
		setupContent("");
	}

	public BorrowDialog(BorrowPanel borrowPanel, JFrame owner, String title, boolean modal, String act) {
		super(owner, title.toUpperCase(), modal);
		ImageIcon img = new FlatSVGIcon("icons/add2.svg");
		setIconImage(img.getImage());

		this.borrowPanel = borrowPanel;
		this.act = act;

		cboBook = new JComboBox<Object>();
		cboReader = new JComboBox<Object>();
		dateChooser = new JDateChooser();
		dateChooser.setDate(Calendar.getInstance().getTime());
		dateChooser.setEnabled(false);

		loadDataComboBoxBook(listBook);
		loadDataComboBoxReader(listReader);
		setupContent(title);
	}

	public BorrowDialog(BorrowPanel borrowPanel, JFrame owner, String title, boolean modal, String act,
			BorrowModel borrowModel) {
		super(owner, title.toUpperCase(), modal);
		this.borrowPanel = borrowPanel;
		this.act = act;
		this.borrowID = borrowModel.getBorrowID();

		cboBook = new JComboBox<Object>();
		loadDataComboBoxBook(listBook);
		setSelectedCombobox(cboBook, borrowModel.getBookName());

		cboReader = new JComboBox<Object>();
		loadDataComboBoxReader(listReader);
		setSelectedCombobox(cboReader, borrowModel.getReaderModel().getName());

		dateChooser = new JDateChooser();
		dateChooser.setDate(DateFormat.DateStringToDate(borrowModel.getDate()));

		if (act.equals("detail")) {
			ImageIcon img = new FlatSVGIcon("icons/detail.svg");
			setIconImage(img.getImage());

			cboBook.setEnabled(false);
			cboReader.setEnabled(false);
			dateChooser.setEnabled(false);
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
		setLocationRelativeTo(borrowPanel);

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

			cboReader.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cboReader.setBounds(125, 74, 220, 40);
			pnlMain.add(cboReader);

			JLabel lblNewLabel = new JLabel("Sách:");
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNewLabel.setBounds(10, 12, 105, 40);
			pnlMain.add(lblNewLabel);

			JLabel lblThLoi = new JLabel("Độc giả:");
			lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblThLoi.setBounds(10, 74, 105, 40);
			pnlMain.add(lblThLoi);

			JLabel lblNhXutBn = new JLabel("Ngày mượn:");
			lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblNhXutBn.setBounds(377, 11, 105, 40);
			pnlMain.add(lblNhXutBn);

			cboBook.setFont(new Font("Tahoma", Font.PLAIN, 14));
			cboBook.setBounds(125, 11, 220, 40);
			pnlMain.add(cboBook);

			JLabel lblSLng = new JLabel("Số lượng:\r\n");
			lblSLng.setFont(new Font("Tahoma", Font.BOLD, 14));
			lblSLng.setBounds(377, 74, 105, 40);
			pnlMain.add(lblSLng);

			JSpinner spinner = new JSpinner();
			spinner.setFont(new Font("Tahoma", Font.PLAIN, 14));
			spinner.setBounds(492, 74, 220, 40);
			pnlMain.add(spinner);

			dateChooser.setBounds(492, 12, 220, 40);
			dateChooser.setDateFormatString("dd/MM/yyyy");
			dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
			pnlMain.add(dateChooser);
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

	public void loadDataComboBoxBook(List<BookModel> result) {
		cboBook.removeAllItems();
		if (result != null) {
			result.forEach(o -> {
				cboBook.addItem(o);
			});
		}
	}

	public void loadDataComboBoxReader(List<ReaderModel> result) {
		cboReader.removeAllItems();
		if (result != null) {
			result.forEach(o -> {
				cboReader.addItem(o);
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
			int bookID = ((BookModel) cboBook.getSelectedItem()).getBookID();
		
			ReaderModel reader =  new ReaderModel();
			int readerID = ((ReaderModel) cboReader.getSelectedItem()).getReaderID();
			reader.setReaderID(readerID);
			
			Date dchr = dateChooser.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String date = sdf.format(dchr);
			
			int status = 1;

			if (isValidate(bookID, readerID, date, status)) {
				if (action.equals("insert")) {
					BorrowModel borrowModel = new BorrowModel(bookID, reader, date, status);
					BorrowService.getInstance().insert(borrowModel);
				} else if (action.equals("update")) {
					BorrowModel borrowModel = new BorrowModel(borrowID, bookID, reader, date, status);
					BorrowService.getInstance().update(borrowModel);
				}

				dispose();
				borrowPanel.reloadData();
			}
		} catch (NumberFormatException | HeadlessException e) {
			e.printStackTrace();
		}
	}

	private int parseTextField(JTextField textField) {
		String text = textField.getText().trim();
		return text.isEmpty() ? 0 : Integer.parseInt(text);
	}

	private boolean isValidate(int readerID, int bookID, String date, int status) {
		if (readerID <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn độc giả");
			return false;
		}
		if (bookID <= 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ID sách");
			return false;
		}
		if (date.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày mượn");
			return false;
		}
		if (status < 0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập trạng thái hợp lệ");
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
