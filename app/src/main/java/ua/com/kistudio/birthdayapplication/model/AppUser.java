package ua.com.kistudio.birthdayapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Вiталя on 22.03.2016.
 */
public class AppUser implements Parcelable{
    private String mName;
    private String mPassword;
    private String mPhoneId;
    private String mEmail;

    public AppUser(String mName, String mPassword, String mPhoneId, String mEmail) {
        this.mName = mName;
        this.mPassword = mPassword;
        this.mPhoneId = mPhoneId;
        this.mEmail = mEmail;
    }


    protected AppUser(Parcel in) {
        mName = in.readString();
        mPassword = in.readString();
        mPhoneId = in.readString();
        mEmail = in.readString();
    }

    public static final Creator<AppUser> CREATOR = new Creator<AppUser>() {
        @Override
        public AppUser createFromParcel(Parcel in) {
            return new AppUser(in);
        }

        @Override
        public AppUser[] newArray(int size) {
            return new AppUser[size];
        }
    };

    public String getmName() {
        return mName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public String getmPhoneId() {
        return mPhoneId;
    }

    public String getmEmail() {
        return mEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeString(mPassword);
        dest.writeString(mPhoneId);
        dest.writeString(mEmail);
    }
}
