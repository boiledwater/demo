package com.demo.bridge;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

import com.demo.R;

import java.io.InputStream;

public class BridgeActivity extends Activity {
    private WebView webView;
    private WebViewJavascriptBridge bridge;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bridge);
        webView = (WebView) this.findViewById(R.id.webView);
        bridge =
                new WebViewJavascriptBridge(this, webView, new UserServerHandler());
        loadUserClient();
        registerHandle();
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test", "button1");
                bridge.callHandler("showAlert", "handler1 argument");
            }
        });
    }

    private void loadUserClient() {
        InputStream is = getResources().openRawResource(R.raw.user_client);
        String user_client_html = WebViewJavascriptBridge.convertStreamToString(is);
        Log.d("test", user_client_html);
        webView.loadData(user_client_html, "text/html", "UTF-8");
//        webView.loadUrl("file:///android_asset/map/map.html");
    }

    class UserServerHandler implements WebViewJavascriptBridge.WVJBHandler {
        @Override
        public void handle(String parameter, WebViewJavascriptBridge.WVJBResponseCallback jsCallback) {
            Log.d("test", "Received message from javascript: " + parameter);
            if (null != jsCallback) {
                jsCallback.callback("Java said:Right back atcha");
            }
//            bridge.send("I expect a response!", new WebViewJavascriptBridge.WVJBResponseCallback() {
//                @Override
//                public void callback(String responseData) {
//                    Log.d("test", "Got response! " + responseData);
//                }
//            });
//            bridge.send("Hi");
        }
    }

    public void p(String name) {
        Log.d("test", "println: " + name);
    }

    private void registerHandle() {
        bridge.registerHandler("handler1", new WebViewJavascriptBridge.WVJBHandler() {
            @Override
            public void handle(String parameter, WebViewJavascriptBridge.WVJBResponseCallback jsCallback) {
                Log.d("test", "handler1 got:" + parameter);
                if (null != jsCallback) {
                    jsCallback.callback("handler1 answer");
                }
                bridge.callHandler("showAlert", "42");
            }
        });
    }


}