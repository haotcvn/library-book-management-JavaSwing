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

import models.BookModel;
import models.CategoryModel;
import service.BookService;
import service.CategoryService;
import utilities.CustomButton;
import utilities.TableSorter;
import views.dialog.CategoryDialog;

public class CategoryPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private DefaultTableModel tblModel;
	private JScrollPane scrollTable;
	private JPanel pnlLeft, pnlRight;
	private JButton btnAdd, btnEdit, btnDelete, btnDetail, btnRefresh;
	private JComboBox<String> cboSearch;
	private JTextField txtSearch;
	JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

	private List<CategoryModel> listCategory = CategoryService.getInstance().selectAll();

	public CategoryPanel() {
		setupContent();
		loadDataTable(listCategory);
	}

	public void setListCategory(List<CategoryModel> listCategory) {
		this.listCategory = listCategory;
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

		btnAdd = new CustomButton("Thêm", new FlatSVGIcon(BookPanel.class.getResource("/icons/add2.svg")), new CustomButton.ButtonClickListener() {
			@Override
			public void onButtonClicked() {
				new CategoryDialog(CategoryPanel.this, owner, "Thêm thể loại mới", true, "insert");
			}
		});
		pnlLeft.add(btnAdd);

		btnEdit = new CustomButton("Sửa", new FlatSVGIcon(BookPanel.class.getResource("/icons/edit.svg")), new CustomButton.ButtonClickListener() {
			@Override
			public void onButtonClicked() {
				int row = getRowSelected();
				if(row != -1) {
					int id = Integer.parseInt(table.getValueAt(row, 1).toString());
					CategoryModel categoryModel = CategoryService.getInstance().selectById(id);
					new CategoryDialog(CategoryPanel.this, owner, "Chỉnh sửa thể loại", true, "update", categoryModel);
				}
			}
		});
		pnlLeft.add(btnEdit);

		btnDelete = new CustomButton("Xóa", new FlatSVGIcon(BookPanel.class.getResource("/icons/delete.svg")), new CustomButton.ButtonClickListener() {
			@Override
			public void onButtonClicked() {
				int row = getRowSelected();
				if(row != -1) {
					int select = JOptionPane.showConfirmDialog(CategoryPanel.this, "Bạn có chắc chắn muốn xóa thể loại này?", "Thông báo",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
					if (select == JOptionPane.OK_OPTION) {
						int id = Integer.parseInt(table.getValueAt(row, 1).toString());
						CategoryService.getInstance().delete(id);
						reloadData();
					}
				}
			}
		});
		pnlLeft.add(btnDelete);

		btnDetail = new CustomButton("Chi tiết", new FlatSVGIcon(BookPanel.class.getResource("/icons/detail.svg")), new CustomButton.ButtonClickListener() {
			@Override
			public void onButtonClicked() {
				int row = getRowSelected();
				if(row != -1) {
					int id = Integer.parseInt(table.getValueAt(row, 1).toString());
					CategoryModel categoryModel = CategoryService.getInstance().selectById(id);
					new CategoryDialog(CategoryPanel.this, owner, "Chi tiết thể loại", true, "detail", categoryModel);
				}
			}
		});
		pnlLeft.add(btnDetail);

		pnlRight = new JPanel();
		pnlRight.setBorder(new EmptyBorder(10, 10, 10, 10));
		pnlTop.add(pnlRight, BorderLayout.EAST);

		String[] item = { "Tất cả", "Mã thể loại", "Tên thể loại" };
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
			public void keyTyped(KeyEvent e) {}
			@Override
			public void keyReleased(KeyEvent e) {}
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loadDataTable(searchList());
				}
			}
		});
		pnlRight.add(txtSearch);

		btnRefresh = new CustomButton("Tải lại", new FlatSVGIcon(BookPanel.class.getResource("/icons/refresh.svg")), new CustomButton.ButtonClickListener() {
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
		String[] header = new String[] { "STT","Mã thể loại", "Tên thể loại"};
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

	public void loadDataTable(List<CategoryModel> result) {
		tblModel.setRowCount(0);
		if (result != null) {
			int i = 1;
			for (CategoryModel o : result) {
				Object[] rowData = {i++, o.getCategoryID(), o.getCategoryName() };

				tblModel.addRow(rowData);
			}
		}
	}
	
	public void reloadData() {
		List<CategoryModel> listCategory = CategoryService.getInstance().selectAll();
		this.setListCategory(listCategory);
		this.loadDataTable(listCategory);
	}
	
	private int getRowSelected() {
		int index = table.getSelectedRow();
		if (index == -1) {
			JOptionPane.showMessageDialog(this, "Vui lòng chọn thể loại", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
		return index;
	}
	
	private List<CategoryModel> searchList() {
		List<CategoryModel> categoryList = null;
		String key = cboSearch.getSelectedItem().toString();
		switch (key) {
		case "Tất cả" -> categoryList = CategoryService.getInstance().selectAll();
		case "Mã thể loại" -> categoryList = CategoryService.getInstance().selectByCondition("Mã thể loại", txtSearch.getText());
		case "Tên thể loại" -> categoryList = CategoryService.getInstance().selectByCondition("Tên thể loại", txtSearch.getText());

		default -> throw new IllegalArgumentException("Unexpected value: " + cboSearch.getSelectedItem().toString());
		}

		return categoryList;
	}
}
