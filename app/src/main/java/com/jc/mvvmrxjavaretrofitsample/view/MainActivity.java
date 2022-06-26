package com.jc.mvvmrxjavaretrofitsample.view;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.jc.mvvmrxjavaretrofitsample.R;

import java.security.SecureRandom;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.cert.X509Certificate;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //获取碎片管理，并启动其事物，添加“movie_fragment"的实例到ID为movie_fragment上，其为帧布局，并对此进行提交(commit)
        getFragmentManager().beginTransaction().add(R.id.movie_fragment, MovieFragment.getInstance()).commit();
        //因为测试的虚拟机比较老，需要关闭掉证书验证。
        //理论上可以用ca-bundle.crt替换掉原本的证书，不过我目前没找到合适的解决方案
        //毕竟是学习MVVM的项目，先跑通吧，安全性放一放
        handleSSLHandshake();
    }



    /**

     * 忽略https的证书校验

     * 避免Glide加载https图片报错：

     * javax.net.ssl.SSLHandshakeException: java.security.cert.CertPathValidatorException: Trust anchor for certification path not found.

     */


    public static void handleSSLHandshake() {

        try {

            TrustManager[] trustAllCerts =new TrustManager[]{new X509TrustManager() {

                public X509Certificate[]getAcceptedIssuers() {

                    return new X509Certificate[0];

                }

                @Override

                public void checkClientTrusted(X509Certificate[] certs, String authType) {

                }

                @Override

                public void checkServerTrusted(X509Certificate[] certs, String authType) {

                }

            }};

            SSLContext sc = SSLContext.getInstance("TLS");

            // trustAllCerts信任所有的证书
            sc.init(null, trustAllCerts, new SecureRandom());

            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {

                @Override

                public boolean verify(String hostname, SSLSession session) {

                    return true;

                }

            });

        }catch (Exception ignored) {

        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
