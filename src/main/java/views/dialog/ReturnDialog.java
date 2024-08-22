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
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.toedter.calendar.JDateChooser;

import colors.CustomColor;
import models.BorrowModel;
import models.ReturnModel;
import service.BorrowService;
import service.ReturnService;
import utilities.DateFormat;
import views.panel.ReturnPanel;

public class ReturnDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;

    private JComboBox<Object> cboBorrow;
    private JComboBox<String> cboStatus;
    private JTextField txtFine;
    private JButton btnOK, btnCancel;
    private ReturnPanel returnPanel;
    private JDateChooser dateChooser;
    String act;
    int returnID;
    
    List<BorrowModel> listBorrowAll = BorrowService.getInstance().selectAll();
    List<BorrowModel> listBorrowOff = BorrowService.getInstance().selectStatusOff();

    public ReturnDialog() {
        txtFine = new JTextField();
        cboBorrow = new JComboBox<Object>();
        loadDataComboBoxBorrow(listBorrowAll);
        
        cboStatus = new JComboBox<>(new String[] { "Nam", "Nữ" });
        dateChooser = new JDateChooser();
        setupContent("");
    }

    public ReturnDialog(ReturnPanel returnPanel, JFrame owner, String title, boolean modal, String act) {
        super(owner, title.toUpperCase(), modal);
        ImageIcon img = new FlatSVGIcon("icons/add2.svg");
        setIconImage(img.getImage());

        this.returnPanel = returnPanel;
        this.act = act;

        txtFine = new JTextField();
        cboBorrow = new JComboBox<Object>();
        loadDataComboBoxBorrow(listBorrowAll);
        
        cboStatus = new JComboBox<>(new String[] { "Nam", "Nữ" });
        
        dateChooser = new JDateChooser();
		dateChooser.setDate(Calendar.getInstance().getTime());
		
        setupContent(title);
    }

    public ReturnDialog(ReturnPanel returnPanel, JFrame owner, String title, boolean modal, String act,
            ReturnModel returnModel) {
        super(owner, title.toUpperCase(), modal);
        this.returnPanel = returnPanel;
        this.act = act;
        this.returnID = returnModel.getReturnID();

        txtFine = new JTextField();
        txtFine.setText(String.valueOf(returnModel.getStatus()));

        cboBorrow = new JComboBox<Object>();
        loadDataComboBoxBorrow(listBorrowOff);
        setSelectedCombobox(cboBorrow, String.valueOf(returnModel.getBorrowID()));
        
        cboStatus = new JComboBox<>(new String[] { "Nam", "Nữ" });
        cboStatus.setSelectedItem(returnModel.getStatus());
        
        dateChooser = new JDateChooser();
		dateChooser.setDate(DateFormat.DateStringToDate(returnModel.getDate()));
		
        if (act.equals("detail")) {
			ImageIcon img = new FlatSVGIcon("icons/detail.svg");
			setIconImage(img.getImage());

			cboStatus.setEnabled(false);
			cboBorrow.setEnabled(false);
			dateChooser.setEnabled(false);
			txtFine.setEnabled(false);
		} else {
			ImageIcon img = new FlatSVGIcon("icons/edit.svg");
			setIconImage(img.getImage());
			
			cboBorrow.setEnabled(false);
		}
        
        setupContent(title);
    }

    private void setupContent(String title) {
        setBounds(100, 100, 748, 346);
        getContentPane().setLayout(new BorderLayout());
        setResizable(false);
        setLocationRelativeTo(returnPanel);

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

            txtFine.setFont(new Font("Tahoma", Font.PLAIN, 14));
            txtFine.setColumns(10);
            txtFine.setBounds(492, 13, 220, 40);
            pnlMain.add(txtFine);

            cboBorrow.setFont(new Font("Tahoma", Font.PLAIN, 14));
            cboBorrow.setBounds(125, 12, 220, 40);
            pnlMain.add(cboBorrow);

            JLabel lblNewLabel = new JLabel("Ngày trả:");
            lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblNewLabel.setBounds(10, 74, 105, 40);
            pnlMain.add(lblNewLabel);

            JLabel lblThLoi = new JLabel("Tiền phạt:");
            lblThLoi.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblThLoi.setBounds(377, 12, 105, 40);
            pnlMain.add(lblThLoi);

            JLabel lblNhXutBn = new JLabel("Phiếu mượn:");
            lblNhXutBn.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblNhXutBn.setBounds(10, 12, 105, 40);
            pnlMain.add(lblNhXutBn);
            
            JLabel lblThLoi_1 = new JLabel("Trạng thái:");
            lblThLoi_1.setFont(new Font("Tahoma", Font.BOLD, 14));
            lblThLoi_1.setBounds(377, 75, 105, 40);
            pnlMain.add(lblThLoi_1);
            
            cboStatus.setFont(new Font("Tahoma", Font.PLAIN, 14));
            cboStatus.setBounds(492, 75, 220, 40);
            pnlMain.add(cboStatus);
            
            
            dateChooser.setBounds(125, 74, 220, 40);
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

    public void loadDataComboBoxBorrow(List<BorrowModel> result) {
        cboBorrow.removeAllItems();
        if (result != null) {
            result.forEach(o -> {
                cboBorrow.addItem(o.getBorrowID());
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
            int borrowID = Integer.parseInt(cboBorrow.getSelectedItem().toString());
            
            Date dchr = dateChooser.getDate();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			String date = sdf.format(dchr);
			
            int status = 1;

            if (isValidate(borrowID, date, status)) {
                if (action.equals("insert")) {
//                    ReturnModel returnModel = new ReturnModel(borrowID, BorrowPanel.borrow.getBookName(), BorrowPanel.borrow.getReaderModel().getName(), date, status);
//                    ReturnService.getInstance().insert(returnModel);
                } else if (action.equals("update")) {
                    ReturnModel returnModel = new ReturnModel(returnID, borrowID, date, status);
                    ReturnService.getInstance().update(returnModel);
                }

                dispose();
                //returnPanel.reloadData();
            }
        } catch (NumberFormatException | HeadlessException e) {
            e.printStackTrace();
        }
    }

    private int parseTextField(JTextField textField) {
        String text = textField.getText().trim();
        return text.isEmpty() ? 0 : Integer.parseInt(text);
    }

    private boolean isValidate(int borrowID, String returnDate, int fine) {
        if (borrowID <= 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn mã mượn");
            return false;
        }
        if (returnDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập ngày trả");
            return false;
        }
        if (fine < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập tiền phạt hợp lệ");
            return false;
        }
        return true;
    }
}
