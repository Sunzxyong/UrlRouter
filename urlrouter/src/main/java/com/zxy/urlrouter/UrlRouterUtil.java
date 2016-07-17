package com.zxy.urlrouter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.text.TextUtils;

import java.util.List;

/**
 * Created by zhengxiaoyong on 16/4/21.
 */
public class UrlRouterUtil {

    public static void setupReferrer(Context context, Intent intent) {
        if (context != null && context instanceof Activity) {
            Route currentRoute = parseCurrentRoute(context);
            intent.putExtra(UrlRouter.URL_ROUTER_REFERRER, currentRoute);
        }
    }

    public static Route parseStartedRoute(Context context) {
        if (context != null && context instanceof Activity) {
            Intent startedIntent = ((Activity) context).getIntent();
            if (startedIntent.hasExtra(UrlRouter.URL_ROUTER_REFERRER)) {
                return startedIntent.getParcelableExtra(UrlRouter.URL_ROUTER_REFERRER);
            }
        }
        return null;
    }

    public static Route parseCurrentRoute(Context context) {
        if (context != null && context instanceof Activity) {
            Route route = Route.newInstance();
            Intent startedIntent = ((Activity) context).getIntent();
            Uri uri = startedIntent.getData();
            if (uri == null)
                return null;
            route.scheme = UrlRouterUtil.getScheme(uri);
            route.host = UrlRouterUtil.getHost(uri);
            route.path = UrlRouterUtil.getPath(uri);
            ResolveInfo resolveInfo = UrlRouterUtil.queryActivity(context, startedIntent);
            if (resolveInfo == null)
                return route;
            route.packageName = resolveInfo.activityInfo.packageName;
            route.activityName = resolveInfo.activityInfo.name;
            return route;
        }
        return null;
    }

    public static ResolveInfo queryActivity(Context context, Intent intent) {
        if (context == null || intent == null)
            return null;
        PackageManager packageManager = context.getApplicationContext().getPackageManager();
        List<ResolveInfo> resolveInfoList = packageManager.queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        if (resolveInfoList == null || resolveInfoList.size() == 0)
            return null;
        int size = resolveInfoList.size();
        if (size == 1)
            return resolveInfoList.get(0);
        String appPackageName = context.getApplicationContext().getPackageName();
        for (int i = 0; i < size; i++) {
            ResolveInfo resolveInfo = resolveInfoList.get(i);
            String activityName = resolveInfo.activityInfo.name;
            if (TextUtils.isEmpty(activityName))
                continue;
            if (activityName.startsWith(appPackageName)) {
                return resolveInfo;
            }
        }
        return resolveInfoList.get(0);
    }

    public static String getScheme(Uri uri) {
        if (uri == null)
            return null;
        return uri.getScheme();
    }

    public static String getHost(Uri uri) {
        if (uri == null)
            return null;
        return uri.getHost();
    }

    public static String getPath(Uri uri) {
        if (uri == null)
            return null;
        String path = uri.getPath();
        if (TextUtils.isEmpty(path))
            return null;
        path = path.replace("/", "");
        return path;
    }
}
