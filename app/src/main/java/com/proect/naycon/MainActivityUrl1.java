package com.proect.naycon;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


public class MainActivityUrl1 extends AppCompatActivity {

    WebView webViewUrl1 ;
    Intent intent;
    WebSettings webSettings;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_url1);

          webViewUrl1 = findViewById(R.id.webviewurl1);

          intent = getIntent();

          String url_1 = intent.getStringExtra("URL");

          webViewUrl1.loadUrl(url_1);

          webSettings = webViewUrl1.getSettings();

          webSettings.setJavaScriptEnabled(true);


       webViewUrl1.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewUrl1.loadUrl(
                        "function ask(){var t=-1,e=document.getElementsByTagName(\"body\")[0];return e.hasAttribute(\"cstmAttr\")&&(t=e.getAttribute(\"cstmAttr\")),t}\"undefined\"==typeof jQuery?function(){var t=document.createElement(\"SCRIPT\");t.src=\"https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js\",t.type=\"text/javascript\",t.onload=function(){var t=window.jQuery;t(\"body\").attr(\"cstmAttr\",\"-1\"),setTimeout(function(){t(\"body\").attr(\"cstmAttr\",\"765\")},15e3)},document.getElementsByTagName(\"head\")[0].appendChild(t)}():($(\"body\").attr(\"cstmAttr\",\"-1\"),setTimeout(function(){$(\"body\").attr(\"cstmAttr\",\"765\")},15e3));");
            }
        });




    }
}
