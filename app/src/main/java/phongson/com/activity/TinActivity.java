package phongson.com.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.share.Sharer;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import phongson.com.R;
import phongson.com.model.BinhLuan;
import phongson.com.model.LichSuDoc;

public class TinActivity extends AppCompatActivity {
    WebView webView;
    public static String ID_USER;
    CallbackManager callbackManager;
    ShareDialog shareDialog;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Save:

                    LichSuDoc lichSuDoc = new LichSuDoc(ID_USER,webView.getUrl().toString(),"",webView.getTitle().toString(),"");
                    MainActivity.mDatabase.child("TinDaLuu").push().setValue(lichSuDoc);
                    return true;
                case R.id.navigation_Comments:
                    Intent intent = new Intent(TinActivity.this, BinhLuanActivity.class);
                    intent.putExtra("linkTinTuc",webView.getUrl());
                    startActivity(intent);
                    return true;
                case R.id.navigation_Share:
                    shareFaceBook();
                    return true;
            }
            return false;
        }
    };

    private boolean kiemtraTin(final LichSuDoc lichSuDoc) {
        final Boolean[] tinbitrung = {false};
        MainActivity.mDatabase.child("TinDaLuu").child(ID_USER).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                LichSuDoc lichSuDoc1 = dataSnapshot.getValue(LichSuDoc.class);
                if (lichSuDoc.getLinkTinTuc().equals(lichSuDoc1.getLinkTinTuc()))
                {
                   tinbitrung[0] =true;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return tinbitrung[0];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tin);
        Intent intent1 = getIntent();
        ID_USER=intent1.getStringExtra("ID_USER");

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

    private void shareFaceBook() {
        ShareLinkContent content = new ShareLinkContent.Builder()
                .setContentUrl(Uri.parse(webView.getUrl()))
                .build();

        callbackManager = CallbackManager.Factory.create();
        shareDialog = new ShareDialog(this);
        ShareDialog.show(this, content);

        if (ShareDialog.canShow(ShareLinkContent.class)) {
            ShareLinkContent linkContent = new ShareLinkContent.Builder()
                    .setContentUrl(Uri.parse(webView.getUrl()))
                    .build();
            shareDialog.show(linkContent);
        }
        shareDialog.registerCallback(callbackManager, new FacebookCallback<Sharer.Result>() {
            @Override
            public void onSuccess(Sharer.Result result) {
                Toast.makeText(TinActivity.this, "Chia Sẽ Thành Công", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancel() {
                Toast.makeText(TinActivity.this, "Chia Sẽ Đã Thoát", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(TinActivity.this, "Lỗi Chia Sẽ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
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
