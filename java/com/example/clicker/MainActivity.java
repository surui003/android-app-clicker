package com.example.clicker;

import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.webkit.WebView;
import android.content.Intent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        webView = (WebView) findViewById(R.id.webView);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void btnGoHandlerA(View view) {
        // Create an Intent to start the second activity called "WebViewActivity"
        Intent intent = new Intent(this, WebViewActivityA.class);
        // Start the intended activity
        startActivity(intent);
    }

    public void btnGoHandlerB(View view) {
        // Create an Intent to start the second activity called "WebViewActivity"
        Intent intent = new Intent(this, WebViewActivityB.class);
        // Start the intended activity
        startActivity(intent);
    }

    public void btnGoHandlerC(View view) {
        // Create an Intent to start the second activity called "WebViewActivity"
        Intent intent = new Intent(this, WebViewActivityC.class);
        // Start the intended activity
        startActivity(intent);
    }

    public void btnGoHandlerD(View view) {
        // Create an Intent to start the second activity called "WebViewActivity"
        Intent intent = new Intent(this, WebViewActivityD.class);
        // Start the intended activity
        startActivity(intent);
    }
}