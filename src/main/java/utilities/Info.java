package utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Info {

	public static String getName() throws UnknownHostException {
		InetAddress ipAddress = InetAddress.getLocalHost();
        String hostname = ipAddress.getHostName();
		return hostname;
	}
	
	public static String getIP() throws UnknownHostException {
		InetAddress ipAddress = InetAddress.getLocalHost();
        String ip = ipAddress.getHostAddress() + " " + Info.getWF();
        return ip;
	}
	
	public static String getWF() {
		String wifiName = "";
		try {
            Process process = Runtime.getRuntime().exec("netsh wlan show interfaces");

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("SSID")) {
                    String[] parts = line.split(":");
                    wifiName = parts[1].trim();
                    break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
		return wifiName;
	}
}
