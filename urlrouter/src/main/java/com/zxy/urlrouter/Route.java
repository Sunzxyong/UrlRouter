package com.zxy.urlrouter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by zhengxiaoyong on 16/4/22.
 */
public final class Route implements Parcelable {
    public String scheme;
    public String host;
    public String path;
    public String packageName;
    public String activityName;

    private Route() {
    }

    public static Route newInstance() {
        return new Route();
    }

    @Override
    public String toString() {
        return "Route{" +
                "activityName='" + activityName + '\'' +
                ", scheme='" + scheme + '\'' +
                ", host='" + host + '\'' +
                ", path='" + path + '\'' +
                ", packageName='" + packageName + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.scheme);
        dest.writeString(this.host);
        dest.writeString(this.path);
        dest.writeString(this.packageName);
        dest.writeString(this.activityName);
    }

    protected Route(Parcel in) {
        this.scheme = in.readString();
        this.host = in.readString();
        this.path = in.readString();
        this.packageName = in.readString();
        this.activityName = in.readString();
    }

    public static final Parcelable.Creator<Route> CREATOR = new Parcelable.Creator<Route>() {
        @Override
        public Route createFromParcel(Parcel source) {
            return new Route(source);
        }

        @Override
        public Route[] newArray(int size) {
            return new Route[size];
        }
    };
}
