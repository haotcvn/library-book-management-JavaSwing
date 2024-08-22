package utilities;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Trần Công Hào
 */
public class Formater {

    public static String FormatVND(double number) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(number) + "đ";
    }
    
    public static String FormatTime(Timestamp date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return formatDate.format(date);
    }
}
