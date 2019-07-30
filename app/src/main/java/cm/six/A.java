package cm.six;

import android.app.Application;
import android.util.Log;

import com.tencent.smtt.sdk.QbSdk;

public class A extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        QbSdk.initX5Environment(getApplicationContext(), new QbSdk.PreInitCallback() {
            @Override
            public void onCoreInitFinished() {
                Log.e("初始化： ","onCoreInitFinished");
            }

            @Override
            public void onViewInitFinished(boolean b) {
                // true 初始化成功
                Log.e("初始化： ","onViewInitFinished___ "+b);
            }
        });
    }
}
