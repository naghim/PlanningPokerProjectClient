package com.example.planningpokerprojectclient.Model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.firebase.database.PropertyName;

public class User implements Parcelable {

    @PropertyName("name")
    String name;

    @PropertyName("value")
    int value;

    public User(){
    }

    public User(String name, int value){
        this.name = name;
        this.value = value;
    }

    public void setName(String name) {

        this.name = name;
    }

    public void setValue(int value) {

        this.value = value;
    }

    protected User(Parcel in) {
        name = in.readString();
        value = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };


    public String getName()
    {
        return this.name;
    }

    public int getValue()
    {
        return this.value;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeInt(value);
    }
}
