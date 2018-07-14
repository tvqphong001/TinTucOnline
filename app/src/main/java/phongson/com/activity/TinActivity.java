package phongson.com.activity;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import phongson.com.R;
import phongson.com.model.LichSuDoc;

public class TinActivity extends AppCompatActivity {
    WebView webView;
    String ID_USER;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_Save:
                    if(AccessToken.getCurrentAccessToken()!=null)
                    {
                        final LichSuDoc lichSuDoc = new LichSuDoc(ID_USER,webView.getUrl().toString(),"",webView.getTitle().toString(),"");
                        //kiemtraTin(lichSuDoc);

                        // Luu tin
                        MainActivity.mDatabase.child("TinDaLuu").child(ID_USER).push().setValue(lichSuDoc);

//                        if (kiemtraTin(lichSuDoc))
//                        {
//                            Toast.makeText(TinActivity.this, "Tin Đã Lưu Từ Trước!!!", Toast.LENGTH_SHORT).show();
//                        }
//                        else {
//                            MainActivity.mDatabase.child("TinDaLuu").child(ID_USER).push().setValue(lichSuDoc);
//                            Toast.makeText(TinActivity.this, "Tin Đã Lưu!!!", Toast.LENGTH_SHORT).show();
//                        }

                    }
                    else {
                        Toast.makeText(TinActivity.this, "Bạn Chưa Đăng Nhập!!!", Toast.LENGTH_SHORT).show();
                    }
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

//        if (AccessToken.getCurrentAccessToken()!=null)
//        {
//            LichSuDoc lichSuDoc = new LichSuDoc(ID_USER,webView.getUrl().toString(),"",webView.getTitle().toString(),"");
//            MainActivity.mDatabase.child("TinDaXem").child(ID_USER).push().setValue(lichSuDoc);
//        }

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
