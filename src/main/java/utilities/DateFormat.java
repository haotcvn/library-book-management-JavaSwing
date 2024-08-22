package utilities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 *
 * @author Trần Công Hào
 */
public class DateFormat {

	// Phương thức để lấy ngày hiện tại
	public static String getCurrentDate() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}

	public static String getCurrentDateToSQL() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date currentDate = new Date();
		return dateFormat.format(currentDate);
	}
	
	public static String getCurrentDateTimeToSQL() {
	    SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date currentDate = new Date();
	    return dateTimeFormat.format(currentDate);
	}

	// Phương thức để lấy giờ hiện tại
	public static String getCurrentTime() {
		SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm:ss a");
		Date currentTime = new Date();
		return timeFormat.format(currentTime);
	}
	
	
	public static Date DateStringToDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date newDate = null;
		try {
			newDate = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return newDate;
    }
	
	public static String MyDateToMySQL(String date) {
        // Tách ngày, tháng và năm từ chuỗi đầu vào
        String[] parts = date.split("/");
        String ngay = parts[0];
        String thang = parts[1];
        String nam = parts[2];

        // Sắp xếp lại thành chuỗi mới theo định dạng "yyyy/MM/dd"
        String dateNew = nam + "/" + thang + "/" + ngay;

        return dateNew;
    }
//	
//	public static void main(String[] args) {
//		String ngayThang = "05/05/2023";
//        String ngayMoi = MyDateSQL(ngayThang);
//        System.out.println("Ngày mới: " + ngayMoi);
//	}

}
