package ua.com.kistudio.birthdayapplication.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import ua.com.kistudio.birthdayapplication.util.Prefs;

/**
 * Created by Вiталя on 18.03.2016.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final String CREATE_TABLE =
            String.format("CREATE TABLE %s ( %s integer primary key autoincrement," +
            " %s text, %s text, %s date, %s text); ",Prefs.TABLE_NAME,
            Prefs.ID_FIELD_NAME,Prefs.FN_FIELD_NAME,
            Prefs.LN_FIELD_NAME,Prefs.BIRTHDAY_FIELD_NAME,Prefs.EMAIL_FIELD_NAME);

    public DBHelper(Context context, int version) {
        super(context, Prefs.DB_NAME, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
