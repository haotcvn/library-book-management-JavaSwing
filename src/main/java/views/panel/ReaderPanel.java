package views.panel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import models.ReaderModel;
import service.ReaderService;
import utilities.CustomButton;
import utilities.TableSorter;
import views.dialog.ReaderDialog;

public class ReaderPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tblModel;
	private JScrollPane scrollTable;
	private JPanel pnlLeft, pnlRight;
	private JButton btnAdd, btnEdit, btnDelete, btnDetail, btnRefresh;
	private JComboBox<String> cboSearch;
	private JTextField txtSearch;
	JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

	private List<ReaderModel> listReader = ReaderService.getInstance().selectAll();

	public ReaderPanel() {
		setupContent();
		loadDataTable(listReader);
	}

	public void setListReader(List<ReaderModel> listReader) {
		this.listReader = listReader;
	}

	private void setupContent() {
		setLayout(new BorderLayout(0, 0));
		JPanel pnlTop = new JPanel();
		pnlTop.setPreferredSize(new Dimension(10, 80));
		pnlTop.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlTop, BorderLayout.NORTH);
		pnlTop.setLayout(new BorderLayout(0, 0));

		pnlLeft = new JPanel();
		pnlLeft.setBorder(new EmptyBorder(10, 10, 10, 10));
		FlowLayout fl_pnlLeft = (FlowLayout) pnlLeft.getLayout();
		pnlTop.add(pnlLeft, BorderLayout.WEST);

		btnAdd = new CustomButton("Thêm", new FlatSVGIcon(ReaderPanel.class.getResource("/icons/add2.svg")),
				new CustomButton.ButtonClickListener() {
					@Override
					public void onButtonClicked() {
						new ReaderDialog(ReaderPanel.this, owner, "Thêm độc giả mới", true, "insert");
					}
				});
		pnlLeft.add(btnAdd);

		btnEdit = new CustomButton("Sửa", new FlatSVGIcon(ReaderPanel.class.getResource("/icons/edit.svg")),
				new CustomButton.ButtonClickListener() {
					@Override
					public void onButtonClicked() {
						int row = getRowSelected();
						if (row != -1) {
							int id = Integer.parseInt(table.getValueAt(row, 1).toString());
							ReaderModel readerModel = ReaderService.getInstance().selectById(id);
							new ReaderDialog(ReaderPanel.this, owner, "Chỉnh sửa thông tin độc giả", true, "update",
									readerModel);
						}
					}
				});
		pnlLeft.add(btnEdit);

		btnDelete = new CustomButton("Xóa", new FlatSVGIcon(ReaderPanel.class.getResource("/icons/delete.svg")),
				new CustomButton.ButtonClickListener() {
					@Override
					public void onButtonClicked() {
						int row = getRowSelected();
						if (row != -1) {
							int select = JOptionPane.showConfirmDialog(ReaderPanel.this,
									"Bạn có chắc chắn muốn xóa độc giả này?", "Thông báo", JOptionPane.OK_CANCEL_OPTION,
									JOptionPane.INFORMATION_MESSAGE);
							if (select == JOptionPane.OK_OPTION) {
								int id = Integer.parseInt(table.getValueAt(row, 1).toString());
								ReaderService.getInstance().delete(id);
								reloadData();
							}
						}
					}
				});
		pnlLeft.add(btnDelete);

		btnDetail = new CustomButton("Chi tiết", new FlatSVGIcon(ReaderPanel.class.getResource("/icons/detail.svg")),
				new CustomButton.ButtonClickListener() {
					@Override
					public void onButtonClicked() {
						int row = getRowSelected();
						if (row != -1) {
							int id = Integer.parseInt(table.getValueAt(row, 1).toString());
							ReaderModel readerModel = ReaderService.getInstance().selectById(id);
							new ReaderDialog(ReaderPanel.this, owner, "Chi tiết thông tin độc giả", true, "detail",
									readerModel);
						}
					}
				});
		pnlLeft.add(btnDetail);

		pnlRight = new JPanel();
		pnlRight.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlTop.add(pnlRight, BorderLayout.EAST);

		String[] item = { "Tất cả", "Mã độc giả", "Tên độc giả", "Địa chỉ", "Số điện thoại" };
		cboSearch = new JComboBox<String>(item);
		cboSearch.setFont(new Font("Tahoma", Font.BOLD, 13));
		cboSearch.setPreferredSize(new Dimension(120, 40));
		pnlRight.add(cboSearch);

		txtSearch = new JTextField();
		txtSearch.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtSearch.setPreferredSize(new Dimension(7, 40));
		txtSearch.setColumns(15);
		txtSearch.putClientProperty(FlatClientProperties.TEXT_FIELD_SHOW_CLEAR_BUTTON, true);
		txtSearch.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
			}

			@Override
			public void keyReleased(KeyEvent e) {
			}

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					// loadDataTable(searchList());
				}
			}
		});
		pnlRight.add(txtSearch);

		btnRefresh = new CustomButton("Tải lại", new FlatSVGIcon(ReaderPanel.class.getResource("/icons/refresh.svg")),
				new CustomButton.ButtonClickListener() {
					@Override
					public void onButtonClicked() {
						cboSearch.setSelectedIndex(0);
						txtSearch.setText("");
						reloadData();
					}
				});
		pnlRight.add(btnRefresh);

		JPanel pnlCenter = new JPanel();
		pnlCenter.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlCenter);
		pnlCenter.setLayout(new BorderLayout(0, 0));

		table = new JTable();
		table.setDefaultEditor(Object.class, null);
		scrollTable = new JScrollPane();
		tblModel = new DefaultTableModel();
		String[] header = new String[] { "STT", "Mã độc giả", "Tên độc giả", "Địa chỉ", "Số điện thoại", "Giới tính" };
		tblModel.setColumnIdentifiers(header);
		table.setModel(tblModel);
		table.setFocusable(false);
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 14));
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setRowHeight(20);
		scrollTable.setViewportView(table);
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		for (int i = 0; i < header.length; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		table.setAutoCreateRowSorter(true);
		TableSorter.configureTableColumnSorter(table, 0, TableSorter.INTEGER_COMPARATOR);
		pnlCenter.add(scrollTable);

	}

	public void loadDataTable(List<ReaderModel> result) {
		tblModel.setRowCount(0);
		if (result != null) {
			int i = 1;
			for (ReaderModel o : result) {
				Object[] rowData = { i++, o.getReaderID(), o.getName(), o.getAddress(), o.getPhoneNumber(),
						o.getGender() };

				tblModel.addRow(rowData);
			}
		}
	}

	public void reloadData() {
		List<ReaderModel> readerList = ReaderService.getInstance().selectAll();
		this.setListReader(readerList);
		this.loadDataTable(readerList);
	}

	private int getRowSelected() {
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn độc giả", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		return index;
	}
}
