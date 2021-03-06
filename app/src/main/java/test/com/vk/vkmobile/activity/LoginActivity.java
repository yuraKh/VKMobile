package test.com.vk.vkmobile.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import test.com.vk.vkmobile.R;

/**
 * Created by yurak on 10.03.2017.
 */

public class LoginActivity extends AppCompatActivity{

    private static final String[] sMyScope = new String[]{
            VKScope.NOTIFY,
            VKScope.FRIENDS ,
            VKScope.PHOTOS ,
            VKScope.AUDIO,
            VKScope.VIDEO ,
            VKScope.DOCS ,
            VKScope.NOTES ,
            VKScope.PAGES ,
            VKScope.STATUS ,
            VKScope.WALL ,
            VKScope.GROUPS,
            VKScope.MESSAGES,
            VKScope.NOTIFICATIONS ,
            VKScope.STATS ,
            VKScope.ADS ,
            VKScope.OFFLINE,
            VKScope.EMAIL ,
            VKScope.DIRECT
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences prefs = getPreferences(MODE_PRIVATE);
        boolean restoredText = prefs.getBoolean("loginStatus", false);
        if (restoredText == true) {
            Toast.makeText(LoginActivity.this, R.string.logged_in, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        Button button = (Button) findViewById(R.id.uiBtnSignIn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                VKSdk.login(LoginActivity.this, sMyScope);

            }
        });

        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                switch (res) {
                    case LoggedOut:
                        Toast.makeText(LoginActivity.this, R.string.logged_out, Toast.LENGTH_SHORT).show();
                        break;
                    case LoggedIn:
                        Toast.makeText(LoginActivity.this, R.string.logged_in, Toast.LENGTH_SHORT).show();
                        break;
                    case Pending:
                        break;
                    case Unknown:
                        break;
                }
            }

            @Override
            public void onError(VKError error) {
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (!VKSdk.onActivityResult(requestCode, resultCode, data, new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                Toast.makeText(LoginActivity.this, R.string.logged_in, Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
                editor.putBoolean("loginStatus", true);
                editor.commit();
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(intent);
            }

            @Override
            public void onError(VKError error) {
            }
        })) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
