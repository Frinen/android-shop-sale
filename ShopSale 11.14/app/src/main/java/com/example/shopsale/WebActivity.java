package com.example.shopsale;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WebView browser = new WebView(this);
        setContentView(browser);
        browser.setWebViewClient(new WebViewSampleClient());

        Bundle arguments = getIntent().getExtras();
        String site = arguments.get("Site").toString();
        if(site.compareTo("1")==0) {
            browser.loadUrl("https://xtrade.ua/index.php?route=product/product&product_id=326184&search=Acer+aspire+3&sort=p.price&order=DESC&page=2");
        }
        if(site.compareTo("2")==0) {
            browser.loadUrl("https://xtrade.ua/index.php?route=product/product&product_id=301449&search=galaxy+A5&sort=p.price&order=DESC");
        }
        if(site.compareTo("3")==0) {
            browser.loadUrl("https://xtrade.ua/index.php?route=product/product&product_id=276958&search=Canon+EOS+77D");
        }
    }


}



 class WebViewSampleClient extends WebViewClient {
    @Override
    public boolean shouldOverrideUrlLoading(WebView view,
                                            String url) {
        view.loadUrl(url);
        return true;
    }
}
