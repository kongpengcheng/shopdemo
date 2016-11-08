package com.haier.shopdemo.adapter;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by centling on 2016/10/10.
 */
public class Girl implements Parcelable {

    public String name;
    public int age;

    public Girl() {

    }

    public Girl(String name, int age) {

    }

    public String getName() {

        return name;
    }

    public int getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(age);

    }

    public static final Creator<Girl> CREATOR = new Creator<Girl>() {
        @Override
        public Girl createFromParcel(Parcel source) {
            return new Girl(source);
        }

        //创建指定长度的原始对象数组
        @Override
        public Girl[] newArray(int size) {
            return new Girl[size];
        }
    };

    private Girl(Parcel in) {
        name = in.readString();
        age = in.readInt();
    }
}

