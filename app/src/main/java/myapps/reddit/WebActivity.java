package myapps.reddit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
/*We may need to set the parameters like length and width of the information from the internet since some every website may use a different format.  */

    WebView mWebview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        mWebview = (WebView) findViewById(R.id.webview);

        String link = getIntent().getExtras().getString("URL");

        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setDisplayZoomControls(true);
        webSettings.setDisplayZoomControls(false);

        mWebview.setWebViewClient(new WebViewClient() { //when user wishes to click on a link within reddit, it will load into the application rather than the browser.
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) { //Loading progressed bar
                findViewById(R.id.webprogressbar).setVisibility(view.GONE);
                super.onPageFinished(view, url);
            }
        });

        mWebview.loadUrl("https://m.reddit.com" + link);  //Was missing http:// protocol when debugging.

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) { //Detect if use has pressed back, if there is a navigation to back page it will go to previous page.
        if(keyCode == KeyEvent.KEYCODE_BACK && mWebview.canGoBack()){
            mWebview.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
