package phongson.com.activity;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

import phongson.com.R;
import phongson.com.adapter.VP_MainAdapter;
import phongson.com.model.ChuyenMuc;
import phongson.com.model.TheLoai;
import phongson.com.model.User;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    TabLayout tabLayout;
    ViewPager viewPager;
    LoginButton btnLoginButton;
    ImageView imvUser;
    TextView txtUser;
    public static String ID_USER="";
    CallbackManager callbackManager= CallbackManager.Factory.create();
    ArrayList<TheLoai>  listTheLoai;
    public static ArrayList<ChuyenMuc> listChuyenMuc;
    public static DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        try {
//            PackageInfo info = null;
//            try {
//                info = getPackageManager().getPackageInfo(
//                        "phongson.com.activity",
//                        PackageManager.GET_SIGNATURES);
//            } catch (PackageManager.NameNotFoundException e) {
//                e.printStackTrace();
//            }
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
//            }
//        }catch (NoSuchAlgorithmException e) {
//
//        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View hview = navigationView.getHeaderView(0);
        setDataUser(hview);

        Intent intent = getIntent();
        int Thaydoi = intent.getIntExtra("ThayDoi",-1);
        if (Thaydoi==1)
        {
            listChuyenMuc = ChonChuyenMucActivity.listChonChuyenMuc;
        }
        else {
            setTabChuyeMuc();
        }
        setViewPager();
       //setDataTheLoai();



    }

    private void setDataUser(View hview) {
        btnLoginButton = hview.findViewById(R.id.btnLoginFb);
        imvUser = hview.findViewById(R.id.imvUser);
        txtUser = hview.findViewById(R.id.txtUser);
        if (AccessToken.getCurrentAccessToken()!=null)
        {
            GraphRequest request = GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                @Override
                public void onCompleted(JSONObject object, GraphResponse response) {
                    Log.d("Json",response.getJSONObject().toString());
                    User user = null;
                    try {
                        String name = object.getString("name");
                        String id = object.getString("id");
                        String email = object.getString("email");
                        String profilePicUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
                        Picasso.get().load(profilePicUrl).into(imvUser);
                        txtUser.setText(name);
                        ID_USER = id;
                        //ID_USER = Integer.parseInt(id);
                        //user = new User(id,name,email);
                        //mDatabase.child("User").push().setValue(user);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            });
            Bundle parameters = new Bundle();
            parameters.putString("fields", "name,id,email");
            request.setParameters(parameters);
            request.executeAsync();
            btnLoginButton.setVisibility(View.INVISIBLE);
        }
        btnLoginButton.setReadPermissions(Arrays.asList("public_profile","email","user_birthday"));
        btnLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                GraphRequest request = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("Json",response.getJSONObject().toString());
                        //User user = null;
                        try {
                            String name = object.getString("name");
                            String id = object.getString("id");
                            //String email = object.getString("email");
                            String profilePicUrl = "https://graph.facebook.com/" + id + "/picture?type=large";
                            Picasso.get().load(profilePicUrl).into(imvUser);
                            txtUser.setText(name);
                            ID_USER = id;
                            //ID_USER = Integer.parseInt(id);
                            //user = new User(id,name,email);
                            //mDatabase.child("User").push().setValue(user);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "name,id,email");
                request.setParameters(parameters);
                request.executeAsync();
                btnLoginButton.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void setTabChuyeMuc() {
        ChuyenMuc chuyenMuc = new ChuyenMuc("1","Trang Chủ",1);
        ChuyenMuc chuyenMuc1 = new ChuyenMuc("2","Thời Sự",1);
        ChuyenMuc chuyenMuc2 = new ChuyenMuc("3","Giải Trí",1);
        ChuyenMuc chuyenMuc3 = new ChuyenMuc("4","Giáo Dục",1);
        ChuyenMuc chuyenMuc4 = new ChuyenMuc("5","Thể Thao",1);
        ChuyenMuc chuyenMuc5 = new ChuyenMuc("6","Sức Khỏe",1);
        ChuyenMuc chuyenMuc6 = new ChuyenMuc("7","Công Nghệ",1);
        ChuyenMuc chuyenMuc7 = new ChuyenMuc("8","Video",1);
        listChuyenMuc = new ArrayList<>();
        listChuyenMuc.addAll(Arrays.asList(chuyenMuc,chuyenMuc1,chuyenMuc2,chuyenMuc3,chuyenMuc4,chuyenMuc5,chuyenMuc6,chuyenMuc7));
    }

    private void setViewPager() {
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

//        viewPager.setAdapter(new VP_MainAdapter(getSupportFragmentManager(),getApplicationContext()) );

        viewPager.setAdapter(new VP_MainAdapter(getSupportFragmentManager(), getApplicationContext()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
        });
    }
    private void setDataTheLoai() {

        listTheLoai = new ArrayList<>();
        TheLoai theLoai = new TheLoai("001", "Trang Chủ", "https://vnexpress.net/rss/tin-moi-nhat.rss", "VnExpress");
        TheLoai theLoai1 = new TheLoai("002", "Trang Chủ", "https://thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai2 = new TheLoai("003", "Trang Chủ", "http://vietnamnet.vn/rss/home.rss", "VietNamNet");

        TheLoai theLoai3 = new TheLoai("004", "Thời Sự", "https://vnexpress.net/rss/thoi-su.rss", "VnExpress");
        TheLoai theLoai4 = new TheLoai("005", "Thời Sự", "https://thanhnien.vn/rss/viet-nam.rss", "Thanh Niên");
        TheLoai theLoai5 = new TheLoai("006", "Thời Sự", "http://vietnamnet.vn/rss/thoi-su.rss", "VietNamNet");

        TheLoai theLoai6 = new TheLoai("007", "Giải Trí", "https://vnexpress.net/rss/giai-tri.rss", "VnExpress");
        TheLoai theLoai7 = new TheLoai("008", "Giải Trí", "https://game.thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai8 = new TheLoai("009", "Giải Trí", "http://vietnamnet.vn/rss/giai-tri.rss", "VietNamNet");

        TheLoai theLoai9 = new TheLoai("010", "Giáo Dục", "https://vnexpress.net/rss/giao-duc.rss", "VnExpress");
        TheLoai theLoai10 = new TheLoai("011", "Giáo Dục", "https://thanhnien.vn/rss/giao-duc.rss", "Thanh Niên");
        TheLoai theLoai11 = new TheLoai("012", "Giáo Dục", "http://vietnamnet.vn/rss/giao-duc.rss", "VietNamNet");

        TheLoai theLoai12 = new TheLoai("012", "Thể Thao", "https://vnexpress.net/rss/the-thao.rss", "VnExpress");
        TheLoai theLoai13 = new TheLoai("013", "Thể Thao", "https://thethao.thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai14 = new TheLoai("014", "Thể Thao", "http://vietnamnet.vn/rss/the-thao.rss", "VietNamNet");

        TheLoai theLoai15 = new TheLoai("015", "Sức Khỏe", "https://vnexpress.net/rss/suc-khoe.rss", "VnExpress");
        TheLoai theLoai16 = new TheLoai("016", "Sức Khỏe", "https://thanhnien.vn/rss/doi-song/suc-khoe.rss", "Thanh Niên");
        TheLoai theLoai17 = new TheLoai("017", "Sức Khỏe", "http://vietnamnet.vn/rss/suc-khoe.rss", "VietNamNet");

        TheLoai theLoai18 = new TheLoai("018", "Công Nghệ", "https://vnexpress.net/rss/so-hoa.rss", "VnExpress");
        TheLoai theLoai19 = new TheLoai("019", "Công Nghệ", "https://thanhnien.vn/rss/cong-nghe-thong-tin.rss", "Thanh Niên");
        TheLoai theLoai20 = new TheLoai("020", "Công Nghệ", "http://vietnamnet.vn/rss/cong-nghe.rss", "VietNamNet");

        TheLoai theLoai21 = new TheLoai("021", "Video", "https://video.thanhnien.vn/rss/home.rss", "Thanh Niên");
        TheLoai theLoai22 = new TheLoai("022", "Video", "https://xe.thanhnien.vn/rss/clip.rss", "Thanh Niên");
        //TheLoai theLoai23 = new TheLoai("023","Video","http://vietnamnet.vn/rss/suc-khoe.rss","VietNamNet");
        listTheLoai.add(theLoai);
        listTheLoai.add(theLoai1);
        listTheLoai.add(theLoai2);
        listTheLoai.add(theLoai3);
        listTheLoai.add(theLoai4);
        listTheLoai.add(theLoai5);
        listTheLoai.add(theLoai6);
        listTheLoai.add(theLoai7);
        listTheLoai.add(theLoai8);
        listTheLoai.add(theLoai9);
        listTheLoai.add(theLoai10);
        listTheLoai.add(theLoai11);
        listTheLoai.add(theLoai12);
        listTheLoai.add(theLoai13);
        listTheLoai.add(theLoai14);
        listTheLoai.add(theLoai15);
        listTheLoai.add(theLoai16);
        listTheLoai.add(theLoai17);
        listTheLoai.add(theLoai18);
        listTheLoai.add(theLoai19);
        listTheLoai.add(theLoai20);
        listTheLoai.add(theLoai21);
        listTheLoai.add(theLoai22);

        for(int i=0;i<listTheLoai.size();i++)
        {
            mDatabase.child("TheLoai").push().setValue(listTheLoai.get(i));
        }

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();
        if (id == R.id.nav_Home) {
           setViewPager();
        }
        if (id == R.id.nav_thongtincanhan) {
//            fragmentManager.beginTransaction()
//                    .replace(R.id.content_frame
//                            , new fm_ChiTietThongTin())
//                    .commit();

            //startActivity(new Intent(MainActivity2.this, ThongTinCaNhanActivity.class));
        }
        if (id == R.id.nav_chonchuyenmuc) {
            startActivity(new Intent(MainActivity.this, ChonChuyenMucActivity.class));
        }
        if (id == R.id.nav_setting) {
           // startActivity(new Intent(MainActivity2.this, SettingsActivity2.class));
        }
        if (id == R.id.nav_history_new_save) {
            startActivity(new Intent(MainActivity.this, LichSuDocActivity.class));
        }
        if (id == R.id.nav_logout) {
            LoginManager.getInstance().logOut();
            btnLoginButton.setVisibility(View.VISIBLE);
            imvUser.setImageResource(R.drawable.guest);
            txtUser.setText("Khách");
            // đăng xuất fb
        }


            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
