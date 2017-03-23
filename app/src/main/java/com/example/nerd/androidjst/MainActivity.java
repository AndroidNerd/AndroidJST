package com.example.nerd.androidjst;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button btn_1, btn_2;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        web = (WebView) findViewById(R.id.web);
        btn_1 = (Button) findViewById(R.id.btn_1);
        btn_2 = (Button) findViewById(R.id.btn_2);

        web.getSettings().setJavaScriptEnabled(true);
        web.loadUrl("file:///android_asset/web.html");
        web.addJavascriptInterface(MainActivity.this, "android");

        //Js无法根据参数个数来区分相同方法名的方法
        btn_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl("javascript:nativecalljs()");
            }
        });
        btn_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                web.loadUrl("javascript:nativecalljs2('有参方法运行了')");
            }
        });
    }

    //这个方法是给html页面里面调用的方法
    //由于安全原因 targetSdkVersion>=17需要加 @JavascriptInterface
    @JavascriptInterface
    public void jscallnative() {
        Toast.makeText(this, "无参方法", Toast.LENGTH_SHORT).show();
    }

    @JavascriptInterface
    public void jscallnative(String arg) {
        Toast.makeText(this, arg, Toast.LENGTH_SHORT).show();
    }
}
