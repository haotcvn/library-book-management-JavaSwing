package views.panel;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import colors.CustomColor;
import models.ModelPieChart;
import models.StatisticalModel;
import service.StatisticalServic;
import views.component.PieChart;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.border.EmptyBorder;

public class StatisticalPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private PieChart pieChart;
	private JPanel pnlTitle, pnlContent;

	public StatisticalPanel() {
		setLayout(new BorderLayout(0, 0));


		
		StatisticalModel statisticalModel = StatisticalServic.getInstance().selectValue();
		pieChart = new PieChart();
		pieChart.setChartType(PieChart.PeiChartType.DONUT_CHART);
		pieChart.addData(new ModelPieChart("Tổng số sách",statisticalModel.getNumberOfBooks(), new Color(23, 126, 238)));
		pieChart.addData(new ModelPieChart("Sách đang mượn", statisticalModel.getNumberOfBorrowed(), new Color(221, 65, 65)));
		add(pieChart);
		

		pnlTitle = new JPanel();
		pnlTitle.setBorder(new EmptyBorder(0, 0, 25, 0));
		add(pnlTitle, BorderLayout.SOUTH);

		JLabel lblTitle = new JLabel("THỐNG KÊ SỐ LƯỢNG SÁCH");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 16));
		pnlTitle.add(lblTitle);

	}

}
