package cm.six;

import android.Manifest;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import c.six.util.SClickUtil;
import c.six.util.SLog;
import tsou.cn.lib_primissions.HxgPermissionFail;
import tsou.cn.lib_primissions.HxgPermissionHelper;
import tsou.cn.lib_primissions.HxgPermissionSuccess;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                HxgPermissionHelper.with(MainActivity.this)
                        .requestCode(100)
                        .requestPermission(
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                        .request();
            }
        }, 2000);

    }


    @HxgPermissionSuccess(requestCode = 100)
    private void success() {
        SLog.e("权限：  success");
        Toast.makeText(this, "申请权限成功", Toast.LENGTH_LONG).show();

    }


    @HxgPermissionFail(requestCode = 100)
    private void fail() {
        Log.e("权限： ", "fail");

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        HxgPermissionHelper.requestPermissionsResult(this, requestCode, permissions);
    }

    public void pdf(View view) {
        String pdf = "/storage/emulated/0/huangxiaoguo/10030-2018.pdf";
        Intent intent = new Intent(this, FileActivity.class);
        intent.putExtra("path", pdf);
        startActivity(intent);
    }

    public void doc(View view) {
        String txt = "/storage/emulated/0/huangxiaoguo/别墅.docx";
        Intent intent = new Intent(this, FileActivity.class);
        intent.putExtra("path", txt);
        startActivity(intent);
    }

    public void txt(View view) {
        String txt = "/storage/emulated/0/Log07-19-14-35.txt";
        Intent intent = new Intent(this, FileActivity.class);
        intent.putExtra("path", txt);
        startActivity(intent);
    }

    public void xlsx(View view) {
        String txt = "/storage/emulated/0/huangxiaoguo/欣隆府.xlsx";
        Intent intent = new Intent(this, FileActivity.class);
        intent.putExtra("path", txt);
        startActivity(intent);

    }

    public void fastclick(View view) {
        // 快速点击+有无网络
        if (SClickUtil.isClick(this, 3000)) {
            Toast.makeText(this, "快速点击__  时间间隔 " + SClickUtil.DIFF + "毫秒", Toast.LENGTH_SHORT).show();
        }
    }
}
