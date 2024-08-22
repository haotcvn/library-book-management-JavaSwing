package utilities;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.*;
import java.awt.*;

public class CustomCellRenderer extends DefaultTableCellRenderer {
    public CustomCellRenderer() {
        setHorizontalAlignment(JLabel.CENTER); // Căn giữa chữ
        setFont(new Font("Tahoma", Font.BOLD, 13)); // Đặt font in đậm
        setOpaque(true);
        setBackground(Color.LIGHT_GRAY); // Bạn có thể tùy chỉnh màu nền của tiêu đề ở đây
        setForeground(Color.BLACK); // Bạn cũng có thể tùy chỉnh màu chữ
    }
}

