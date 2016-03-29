package ua.com.kistudio.birthdayapplication.notif;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.GregorianCalendar;

import ua.com.kistudio.birthdayapplication.model.MyIntentService;
import ua.com.kistudio.birthdayapplication.util.Prefs;

/**
 * Created by Вiталя on 24.03.2016.
 */
public class UpdateBroadcastReceiver extends BroadcastReceiver{

    SharedPreferences sharedPreferences;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.net.conn.CONNECTIVITY_CHANGE")){
            if (isOnline(context)){
                sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
                String time = sharedPreferences.getString(Prefs.SHARED_PREFERENCE_TIME_UPDATE, "0000-00-00 00:00");
                SimpleDateFormat sdf = new SimpleDateFormat(Prefs.DATA_FORMAT);
                Calendar calendar = new GregorianCalendar();
                try {
                    calendar.setTime(sdf.parse(time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                Calendar calendarCurrent = new GregorianCalendar();
                calendarCurrent = Calendar.getInstance();

                if (calendarCurrent.getTimeInMillis() - calendar.getTimeInMillis()>1000*60*60*24){
                    MyIntentService.startActionUpdate(context);
                    sharedPreferences
                            .edit()
                            .putString(Prefs.SHARED_PREFERENCE_TIME_UPDATE,sdf.format(calendarCurrent))
                            .commit();
                }
            }
        }
    }
    boolean isOnline(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =connectivityManager.getActiveNetworkInfo();
        return networkInfo.isConnected();
    }

}
