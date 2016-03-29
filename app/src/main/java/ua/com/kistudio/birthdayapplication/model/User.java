package ua.com.kistudio.birthdayapplication.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Вiталя on 18.03.2016.
 */
public class User implements Parcelable {

    private int mId;
    private String mFirstName;
    private String mLastName;
    private Calendar mBirthday;
    private String mBirthdayString;
    private String mEmail;

    public User(int mId, String mFirstName, String mLastName, String mBirthdayString, String mEmail) {
        this.mId = mId;
        this.mFirstName = mFirstName;
        this.mLastName = mLastName;
        this.mBirthday = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            this.mBirthday.setTime(sdf.parse(mBirthdayString));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.mEmail = mEmail;
    }

    protected User(Parcel in) {
        mId = in.readInt();
        mFirstName = in.readString();
        mLastName = in.readString();
        mEmail = in.readString();
        mBirthdayString = in.readString();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        try {
            this.mBirthday.setTime(sdf.parse(mBirthdayString));
        } catch (ParseException e) {
            e.printStackTrace();
        }

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

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmFirstName() {
        return mFirstName;
    }

    public void setmFirstName(String mFirstName) {
        this.mFirstName = mFirstName;
    }

    public String getmLastName() {
        return mLastName;
    }

    public void setmLastName(String mLastName) {
        this.mLastName = mLastName;
    }

    public Calendar getmBirthday() {
        return mBirthday;
    }


    public String getStringmBirthday() {
        SimpleDateFormat sdfTemplate = new SimpleDateFormat("yyyy-mm-dd");
        return sdfTemplate.format(mBirthday);
    }

    public void setmBirthday(Calendar mBirthday) {
        this.mBirthday = mBirthday;
    }

    public String getmEmail() {
        return mEmail;
    }

    public void setmEmail(String mEmail) {
        this.mEmail = mEmail;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(mId);
        dest.writeString(mFirstName);
        dest.writeString(mLastName);
        dest.writeString(mEmail);
        dest.writeString(mBirthdayString);
    }
}