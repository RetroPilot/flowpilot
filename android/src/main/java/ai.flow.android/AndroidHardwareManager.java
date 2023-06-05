package ai.flow.android;

import ai.flow.hardware.HardwareManager;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.text.format.Formatter;
import android.view.Window;
import android.view.WindowManager;

public class AndroidHardwareManager extends HardwareManager {
    public Window window;
    public Context context;
    public AndroidHardwareManager(Window window, Context context) {
        this.window = window;
        this.context = context;
    }

    public void enableScreenWakeLock(boolean enable){
        if (enable)
            window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        else
            window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    public void openWiFiSettings() {
        Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void openTetheringSettings() {
        Intent intent = new Intent();
        intent.setClassName("com.android.settings", "com.android.settings.TetherSettings");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public String getDeviceIpAddress() {
        // Get the WifiManager
        WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);

        // Get the WifiInfo
        WifiInfo wifiInfo = wifiManager.getConnectionInfo();

        // Get the IP address as an integer
        int ipAddress = wifiInfo.getIpAddress();

        // Convert the IP address to a human-readable format
        String ipAddressString = Formatter.formatIpAddress(ipAddress);

        return ipAddressString;
    }
}
