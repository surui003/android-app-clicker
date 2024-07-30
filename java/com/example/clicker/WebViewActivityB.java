package com.example.clicker;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.webkit.WebView;


public class WebViewActivityB extends AppCompatActivity {
    private WebView webView;  // for displaying web contents

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_b);

        webView = (WebView) findViewById(R.id.webView);
        //webView.getSettings().setJavaScriptEnabled(true);

        // 1. URL hardcoded
        //webView.loadUrl("https://www.google.com");

        // 2. Start your Tomcat (having the "hello" webapp).
        //    Use the following URL to trigger the HelloServlet with URL "sayhello" on emulator.
        webView.loadUrl("http://10.0.2.2:9999/clicker/select?choice=b");
        // 10.0.2.2 is the special IP for the host machine of the emulator.
        // You cannot use localhost, as it refers to the emulator.

        // 3. For actual phone, use ipconfig (Windows) or ifconfig (macOS) to check your IP address
        //webView.loadUrl("http://ip_addr:9999/hello/sayhello");
    }
}