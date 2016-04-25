package com.zxy.urlrouter.test.product;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.zxy.urlrouter.Route;
import com.zxy.urlrouter.UrlRouter;
import com.zxy.urlrouter.test.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Route startedRoute = UrlRouter.getStartedRoute(this);
        Route currentRoute = UrlRouter.getCurrentRoute(this);
        if (startedRoute != null)
            Log.e("zxy", "Detail:startedRoute:" + startedRoute.toString());
        if (currentRoute != null)
            Log.e("zxy", "Detail:currentRoute:" + currentRoute.toString());
    }

    public void onClick(View view) {
        if (view.getId() == R.id.one) {
            UrlRouter.from(this).jumpToMain("activity://native/main");
        }
        if (view.getId() == R.id.two) {
            UrlRouter.from(this).jumpToMain("activity://native/hahaha");
        }
    }
}
