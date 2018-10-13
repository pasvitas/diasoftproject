package ru.pasvitas.diasoftproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import java.net.URL;

import ru.pasvitas.diasoftproject.Utils.IFinisherListener;
import ru.pasvitas.diasoftproject.Utils.UserInfo;
import ru.pasvitas.diasoftproject.Utils.UtilWebClient;

public class AuthActivity extends AppCompatActivity {

    WebView wvAuth;
    UtilWebClient utilWebClient = new UtilWebClient();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        wvAuth = findViewById(R.id.wvAuth);

        wvAuth.setWebViewClient(utilWebClient);

        wvAuth.loadUrl("https://oauth.vk.com/authorize?client_id=6193788&display=page&redirect_uri=https://oauth.vk.com/blank.html&scope=friends&response_type=token&v=5.8");

        IFinisherListener finisherListener = new IFinisherListener() {
            @Override
            public void getLink(URL url) {
                tryAuth(url);
            }
        };

        utilWebClient.addListener(finisherListener);

    }

    public void tryAuth(URL url)
    {
        String host = url.getHost();
        String path = url.getPath();
        if (url.getHost().equals("oauth.vk.com"))
        {
            if (url.getPath().equals("/blank.html"))
            {
                String info = url.getRef();
                UserInfo.getInfos(info);
                Intent intent = new Intent(this, FriendsActivity.class);
                startActivity(intent);
            }
        }
    }

}
