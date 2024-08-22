package views.component;

import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import colors.CustomColor;

import java.awt.BorderLayout;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;

public class ItemTaskbar extends JPanel implements MouseListener {

	private static final long serialVersionUID = 1L;
	
    Color FontColor = new Color(96, 125, 139);
    Color ColorBlack = new Color(26, 26, 26);
    Color DefaultColor = new Color(255, 255, 255);
    JLabel lblIcon, lblContent, pnlSoLuong, pnlContent1;
    JPanel right;
    JLabel img;
    public boolean isSelected;

    public ItemTaskbar() {
    	this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(225, 45));
        this.setBackground(DefaultColor);
        this.putClientProperty( FlatClientProperties.STYLE, "arc: 15" );
        this.addMouseListener(this);
	}
    
    public ItemTaskbar(String linkIcon, String content) {
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(225, 45));
        this.setBackground(DefaultColor);
        this.putClientProperty( FlatClientProperties.STYLE, "arc: 15" );
        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setBorder(new EmptyBorder(0, 10, 0, 0));
        lblIcon.setPreferredSize(new Dimension(45, 30));
        lblIcon.setIcon(new FlatSVGIcon("./icons/" + linkIcon));
        this.add(lblIcon);

        lblContent = new JLabel(content);
        lblContent.setPreferredSize(new Dimension(155, 30));
        lblContent.putClientProperty("FlatLaf.style", "font: 145% $medium.font");
        lblContent.setFont(new Font("Tahoma", Font.BOLD, 14));
        this.add(lblContent);
    }

    public ItemTaskbar(String linkIcon, String content1, String content2) {
        this.setLayout(new FlowLayout(0, 20, 50));
        //this.setPreferredSize(new Dimension(250, 45));
        this.setBackground(DefaultColor);
        this.putClientProperty( FlatClientProperties.STYLE, "arc: 15" );
        this.addMouseListener(this);

        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(110, 110));
        lblIcon.setIcon(new FlatSVGIcon("./icons/" + linkIcon));

        this.add(lblIcon);

        lblContent = new JLabel(content1);
        lblContent.setPreferredSize(new Dimension(170, 30));
        lblContent.putClientProperty("FlatLaf.style", "font: 200% $medium.font");
        lblContent.setForeground(FontColor);
        this.add(lblContent);

//        box[i].setBorder(new EmptyBorder(20, 20, 20, 20));
    }

    public ItemTaskbar(String linkImg, String tenSP, int soLuong) {

        this.setLayout(new BorderLayout(0, 0));
        this.setPreferredSize(new Dimension(380, 60));
        this.setBackground(Color.white);

        img = new JLabel("");
        img.setIcon(InputImage.resizeImage(new ImageIcon("./src/img_product/" + linkImg), 36));
        this.add(img, BorderLayout.WEST);

        right = new JPanel();
        right.setLayout(new FlowLayout(0, 0, 0));
        right.setBorder(new EmptyBorder(10, 10, 0, 0));
        right.setOpaque(false);
        this.add(right, BorderLayout.CENTER);

        lblContent = new JLabel(tenSP);
        lblContent.putClientProperty("FlatLaf.style", "font: 120% $semibold.font");
        lblContent.setForeground(Color.black);
        right.add(lblContent);

        pnlSoLuong = new JLabel("Số lượng: " + soLuong);
        pnlSoLuong.setPreferredSize(new Dimension(350, 20));
        pnlSoLuong.putClientProperty("FlatLaf.style", "font: 100% $medium.font");
        pnlSoLuong.setForeground(Color.gray);
        right.add(pnlSoLuong);

    }

    public ItemTaskbar(String linkIcon, String content, String content2, int n) {
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(100, 100));
        lblIcon.setBorder(new EmptyBorder(0, 20, 0, 0));

        lblIcon.setIcon(new FlatSVGIcon("./icon/" + linkIcon));
        this.add(lblIcon, BorderLayout.WEST);

        JPanel center = new JPanel();
        center.setLayout(new FlowLayout(0, 10, 0));
        center.setBorder(new EmptyBorder(20, 0, 0, 0));
        center.setOpaque(false);
        this.add(center);

        lblContent = new JLabel(content);
        lblContent.setPreferredSize(new Dimension(250, 30));
        lblContent.putClientProperty("FlatLaf.style", "font: 300% $semibold.font");
        lblContent.setForeground(FontColor);
        center.add(lblContent);

        pnlContent1 = new JLabel(content2);
        pnlContent1.setPreferredSize(new Dimension(250, 30));
        pnlContent1.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent1.setForeground(FontColor);
        center.add(pnlContent1);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected) {
            setBackground(CustomColor.PRIMARY);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            lblContent.setForeground(CustomColor.WHITE);
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(255, 255, 255));
            lblContent.setForeground(CustomColor.BLACK);
        }
    }
}
