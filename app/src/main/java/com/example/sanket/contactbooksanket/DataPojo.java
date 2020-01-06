package com.example.sanket.contactbooksanket;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.UUID;

public class DataPojo implements Parcelable {

    private String firstname, secondname, phone;
    private String uuid;


    public DataPojo(String firstname, String secondname, String phone) {
        this.uuid = String.valueOf(UUID.randomUUID());
        this.firstname = firstname;
        this.secondname = secondname;
        this.phone = phone;
    }

    private DataPojo(Parcel in) {
        firstname = in.readString();
        secondname = in.readString();
        phone = in.readString();
        uuid = in.readString();
    }

    public static final Creator<DataPojo> CREATOR = new Creator<DataPojo>() {
        @Override
        public DataPojo createFromParcel(Parcel in) {
            return new DataPojo(in);
        }

        @Override
        public DataPojo[] newArray(int size) {
            return new DataPojo[size];
        }
    };

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(firstname);
        dest.writeString(secondname);
        dest.writeString(phone);
        dest.writeString(uuid);
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public void setSecondname(String secondname) {
        this.secondname = secondname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUuid() {
        return uuid;
    }
}
