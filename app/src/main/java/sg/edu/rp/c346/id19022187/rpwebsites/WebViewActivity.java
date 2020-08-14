package sg.edu.rp.c346.id19022187.rpwebsites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebViewActivity extends AppCompatActivity {

    WebView wvPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wvPage = findViewById(R.id.webViewPage);
        Intent intentReceive = getIntent();
        String url = intentReceive.getStringExtra("url");

        wvPage.setWebViewClient(new WebViewClient());

        WebSettings webSettings = wvPage.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);

        wvPage.loadUrl(url);
    }
}