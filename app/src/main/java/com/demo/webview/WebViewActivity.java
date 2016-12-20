package com.demo.webview;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.demo.R;

import java.util.ArrayList;
import java.util.List;

public class WebViewActivity extends Activity {
    private WebView mWebView;
    private List<String> list;
    private int mkeyCode;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        mWebView = (WebView) findViewById(R.id.webView);
        initData();

        WebSettings webSettings = mWebView.getSettings();

        // 是否允许在webview中执行javascript
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.addJavascriptInterface(this, "javatojs");
        //加载网页

        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("file:///android_asset/execute.html");
            }
        });

        mWebView.addJavascriptInterface(new JsObject(), "jsInterface");

        mWebView.addJavascriptInterface(new JsObject(), "injectedObject");
        mWebView.loadData("", "text/html", null);
        mWebView.loadUrl("javascript:alert(injectedObject.toString())");
    }

    class JsObject {
        @JavascriptInterface
        public String toString() {
            return "injectedObject";
        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        mkeyCode = keyCode;
        Log.i("test", "keyCode=" + keyCode);
        mWebView.loadUrl("javascript: OnKeyUp()");
        return super.onKeyUp(keyCode, event);
    }

    public int getKeyCode() {
        return mkeyCode;
    }

    void initData() {
        list = new ArrayList<String>();
        for (int i = 0; i < 5; i++) {
            list.add("我是List中的第" + (i + 1) + "行");
        }
    }

    /**
     * 该方法将在js脚本中，通过window.javatojs.....()进行调用
     *
     * @return
     */
    @JavascriptInterface
    public Object getObject(int index) {
        Log.i("test", "getObject");
        return list.get(index);
    }

    @JavascriptInterface
    public int getSize() {
        Log.i("test", "getSize");
        return list.size();
    }

    @JavascriptInterface
    public void Callfunction() {
        Log.i("test", "Callfunction");
        mWebView.post(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("javascript: GetList()");
            }
        });
    }

    @JavascriptInterface
    public void printStr(String str) {
        Log.i("test", "GetList:" + str);
    }
}