package phongson.com.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import phongson.com.R;

public class TinActivity extends AppCompatActivity {
    WebView webView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Save:
                    Toast.makeText(TinActivity.this, "Saved", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_Comments:
                    Toast.makeText(TinActivity.this, "Comments", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_Share:
                    Toast.makeText(TinActivity.this, "Shared", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin);
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.setTitle(getResources().getString(R.string.app_name));
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView = findViewById(R.id.webview_tintuc);

        // open javaScript to watch video
       settingWebView(true);

        webView.loadUrl(link);
    }

    private void settingWebView(boolean trangthai) {
        webView.getSettings().setJavaScriptEnabled(trangthai);
        // webView.getSettings().setPluginState(WebSettings.PluginState.ON);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(trangthai);
        webView.getSettings().setSupportMultipleWindows(trangthai);
        webView.getSettings().setSupportZoom(trangthai);
        webView.getSettings().setBuiltInZoomControls(trangthai);
        webView.getSettings().setAllowFileAccess(trangthai);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                settingWebView(false);
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
