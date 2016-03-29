package ua.com.kistudio.birthdayapplication.model;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import java.util.concurrent.ExecutionException;

import ua.com.kistudio.birthdayapplication.util.Prefs;

public class MyContentProvider extends ContentProvider {

    private SQLiteDatabase mDatabase;
    private DBHelper mDBHelper;

    static UriMatcher sMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        sMatcher.addURI(Prefs.AUTORITY,Prefs.PATH_USER,Prefs.CODE_USER);
        sMatcher.addURI(Prefs.AUTORITY,Prefs.PATH_API,Prefs.CODE_API);
    }

    public MyContentProvider() {
    }

    @Override
    public boolean onCreate() {
        mDBHelper = new DBHelper(getContext(), Prefs.DB_VERSION);
        return false;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        // Implement this to handle requests to delete one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public String getType(Uri uri) {
        // TODO: Implement this to handle requests for the MIME type of the data
        // at the given URI.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        mDatabase = mDBHelper.getWritableDatabase();

        Cursor c = mDatabase.query(Prefs.TABLE_NAME,null,"name="+values.getAsString(Prefs.FN_FIELD_NAME),null,null,null,null);
        long idInserted;
        if (c.getCount()==0) {
            idInserted = mDatabase.insert(Prefs.TABLE_NAME, null, values);
        }
        else
        {
            idInserted = mDatabase.update(Prefs.TABLE_NAME,values,"name="+values.getAsString(Prefs.FN_FIELD_NAME),null);
        }

        return ContentUris.withAppendedId(uri,idInserted);
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        mDatabase = mDBHelper.getWritableDatabase();
        Cursor cursor = null;
        switch (sMatcher.match(uri)){
            case Prefs.CODE_USER:
                cursor = mDatabase.query(Prefs.TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
                break;
            case Prefs.CODE_API:
                AsyncGetUsersJSON asyncGetUsersJSON = new AsyncGetUsersJSON();
                asyncGetUsersJSON.execute(Prefs.API_SERVER);
                try {
                    cursor = asyncGetUsersJSON.get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
