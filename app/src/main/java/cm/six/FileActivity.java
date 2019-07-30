package cm.six;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import java.io.File;

import c.six.x5.FileView;

public class FileActivity extends AppCompatActivity {

    private String pdf;
    private FileView fileView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file);


        String path = getIntent().getStringExtra("path");
        if (path == null || TextUtils.isEmpty(path)) return;

        fileView = findViewById(R.id.superFileView);
        pdf = "/storage/emulated/0/huangxiaoguo/10030-2018.pdf";
        String t = "/storage/emulated/0/Log07-19-14-35.txt";
        fileView.displayFile(new File(path));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        fileView.onStopDisplay();
    }
}
