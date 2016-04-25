package com.zxy.urlrouter.test.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zxy.urlrouter.Route;
import com.zxy.urlrouter.UrlRouter;
import com.zxy.urlrouter.test.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
            Route startedRoute = UrlRouter.getStartedRoute(this);
            Route currentRoute = UrlRouter.getCurrentRoute(this);
            if (startedRoute != null)
                Log.e("zxy", "Login:startedRoute:" + startedRoute.toString());
            if (currentRoute != null)
                Log.e("zxy", "Login:currentRoute:" + currentRoute.toString());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.one) {
            setResult(RESULT_OK);
            finish();
        }
        if (view.getId() == R.id.two) {
            UrlRouter.from(this).jump("activity://product/detail");
        }
    }
}
