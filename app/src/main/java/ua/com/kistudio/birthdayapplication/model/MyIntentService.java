package ua.com.kistudio.birthdayapplication.model;

import android.app.IntentService;
import android.content.ContentValues;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.provider.Settings;
import android.util.Log;

import java.util.HashMap;
import java.util.concurrent.ExecutionException;

import ua.com.kistudio.birthdayapplication.util.Prefs;

public class MyIntentService extends IntentService {

    public static final String ACTION_GET_ALL = "ua.com.kistudio.birthdayapplication.model.action.GET_ALL";
    public static final String ACTION_REGISTER = "ua.com.kistudio.birthdayapplication.model.action.REGISTER";
    public static final String ACTION_UPDATE = "ua.com.kistudio.birthdayapplication.model.action.LOGIN";
    public static final String ACTION_LOGIN = "ua.com.kistudio.birthdayapplication.model.action.UPDATE";
    private static final String ACTION_GET_ONE_USER = "ua.com.kistudio.birthdayapplication.model.action.GET_ONE_USER";

    private static final String EXTRA_USER_ID = "ua.com.kistudio.birthdayapplication.model.extra.USER_ID";
    private static final String EXTRA_REG_USER = "ua.com.kistudio.birthdayapplication.model.extra.REG_USER";


    public MyIntentService() {
        super("MyIntentService");
    }

    /**
     * Starts this service to perform action Foo with the given parameters. If
     * the service is already performing a task this action will be queued.
     *
     * @see IntentService
     */

    public static void startActionGetAll(Context context){
        Intent intent = new Intent(context, MyIntentService.class).setAction(ACTION_GET_ALL);
        context.startService(intent);
    }

    public static void startActionGetOneUser(Context context, int id)
    {
        Intent intent = new Intent(context, MyIntentService.class).setAction(ACTION_GET_ONE_USER);
        intent.putExtra(EXTRA_USER_ID,id);
        context.startService(intent);
    }


    public static void startActionUpdate(Context context) {
        Intent intent = new Intent(context, MyIntentService.class).setAction(ACTION_UPDATE);
        context.startService(intent);
    }

    public static void startActionLogin(Context context, AppUser appUser){
        Intent intent = new Intent(context, MyIntentService.class)
                .setAction(ACTION_LOGIN);
        intent.putExtra(EXTRA_REG_USER,appUser);
        context.startService(intent);
    }

    public static void startActionRegister(Context context, AppUser appUser){
        Intent intent = new Intent(context, MyIntentService.class).setAction(ACTION_REGISTER);
        intent.putExtra(EXTRA_REG_USER,appUser);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_GET_ALL.equals(action)) {
                handleActionGetAll();
            } else if(ACTION_REGISTER.equals(action)){
                AppUser localUser = intent.getParcelableExtra(EXTRA_REG_USER);
                handleActionRegister(localUser);
            } else if(ACTION_LOGIN.equals(action)){
                AppUser localUser = intent.getParcelableExtra(EXTRA_REG_USER);
                handleActionLogin(localUser);
            } else if(ACTION_UPDATE.equals(action)){
                handleActionUpdate();
            } else if (ACTION_GET_ONE_USER.equals(action)){
                handleActionGetOneUser(intent.getIntExtra(EXTRA_USER_ID,-1));
            }
        }
    }

    private void handleActionGetOneUser(int id) {
        Cursor cursor = getContentResolver().query(Prefs.USERS_URI,null,"_id="+id,null,null);
        cursor.moveToFirst();

        User user = new User(
                cursor.getInt(cursor.getColumnIndex(Prefs.ID_FIELD_NAME)),
                cursor.getString(cursor.getColumnIndex(Prefs.FN_FIELD_NAME)),
                cursor.getString(cursor.getColumnIndex(Prefs.LN_FIELD_NAME)),
                cursor.getString(cursor.getColumnIndex(Prefs.BIRTHDAY_FIELD_NAME)),
                cursor.getString(cursor.getColumnIndex(Prefs.EMAIL_FIELD_NAME))
        );

        Intent intent = new Intent(Prefs.ACTION_USER_SEND);
        intent.putExtra(Prefs.EXTRA_USER, user);

        sendBroadcast(intent);
    }

    private void handleActionUpdate() {
        Cursor c = getContentResolver().query(Prefs.API_URI, null, null, null, null);
        c.moveToFirst();
        do{
            ContentValues contentValues = new ContentValues();
            contentValues.put(Prefs.FN_FIELD_NAME, c.getString(c.getColumnIndex(Prefs.FN_FIELD_NAME)));
            contentValues.put(Prefs.LN_FIELD_NAME, c.getString(c.getColumnIndex(Prefs.LN_FIELD_NAME)));
            contentValues.put(Prefs.BIRTHDAY_FIELD_NAME, c.getString(c.getColumnIndex(Prefs.BIRTHDAY_FIELD_NAME)));
            contentValues.put(Prefs.EMAIL_FIELD_NAME, c.getString(c.getColumnIndex(Prefs.EMAIL_FIELD_NAME)));
            getContentResolver().insert(Prefs.USERS_URI, contentValues);
        } while (c.moveToNext());
        Intent intent = new Intent(Prefs.ACTION_REQUEST);
        intent.putExtra(Prefs.EXTRA_REQUEST_CODE,7);
        sendBroadcast(intent);
    }

    private void handleActionLogin(AppUser user) {
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", user.getmName());
        data.put("password_user", user.getmPassword());
        data.put("phone_id",user.getmPhoneId());
        data.put("email", user.getmEmail());
        data.put("type", "1");

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
        String res= "";
        try {
            res = asyncHttpPost.execute(Prefs.API_SERVER_REG).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(Prefs.LOG_TAG, res);
        Intent intent = new Intent(Prefs.ACTION_REQUEST);

        if (res.equals("5")){
            intent.putExtra("name", user.getmName());
        }
        intent.putExtra(Prefs.EXTRA_REQUEST_CODE, Integer.valueOf(res));


        sendBroadcast(intent);
    }

    private void handleActionRegister(AppUser user) {

        HashMap<String, String> data = new HashMap<String, String>();
        data.put("name", user.getmName());
        data.put("password_user", user.getmPassword());
        data.put("phone_id",user.getmPhoneId());
        data.put("email",user.getmEmail());
        data.put("type", "0");

        AsyncHttpPost asyncHttpPost = new AsyncHttpPost(data);
        String res= "";
        try {
            res = asyncHttpPost.execute(Prefs.API_SERVER_REG).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(Prefs.LOG_TAG, res);

        Intent intent = new Intent(Prefs.ACTION_REQUEST);
        intent.putExtra(Prefs.EXTRA_REQUEST_CODE,Integer.valueOf(res));
        sendBroadcast(intent);
    }

    private void handleActionGetAll() {
        //TODO
    }

}
