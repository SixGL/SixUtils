package c.six.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;


public class SClickUtil {
    private static long lastClickTime = 0;
    public static long DIFF = 800;

    /**
     * 判断两次点击的间隔，如果小于diff，则认为是多次无效点击
     */
    private static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (timeD < DIFF) {
            Log.i("gs  ", "  短时间内按钮多次触发");
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 判断网络是否可用
     *
     * @param context
     * @return
     */
    private static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager mConnectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
            if (mNetworkInfo != null) {
                return mNetworkInfo.isAvailable();
            }
        }
        return false;
    }

    public static boolean isClick(Context context) {
        boolean networkConnected = isNetworkConnected(context);
        if (!networkConnected) {
            Toast.makeText(context, "请检查网络!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isFastDoubleClick() && networkConnected)
            return true;
        else
            return false;
    }

    public static boolean isClick(Context context, long time) {
        DIFF = time;
        boolean networkConnected = isNetworkConnected(context);
        if (!networkConnected) {
            Toast.makeText(context, "请检查网络!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (!isFastDoubleClick() && networkConnected)
            return true;
        else
            return false;
    }
}
