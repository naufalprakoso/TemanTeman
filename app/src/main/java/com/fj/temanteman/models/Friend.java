package com.fj.temanteman.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Friend implements Parcelable {

    private int id;
    private String name, major, initial, phone;

    public Friend(){

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.initial);
        dest.writeString(this.name);
        dest.writeString(this.major);
        dest.writeString(this.phone);
    }

    public Friend(Parcel in) {
        id = in.readInt();
        name = in.readString();
        major = in.readString();
        initial = in.readString();
        phone = in.readString();
    }

    public static final Creator<Friend> CREATOR = new Creator<Friend>() {
        @Override
        public Friend createFromParcel(Parcel in) {
            return new Friend(in);
        }

        @Override
        public Friend[] newArray(int size) {
            return new Friend[size];
        }
    };
}
