package ua.com.kistudio.birthdayapplication.notif;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

import ua.com.kistudio.birthdayapplication.model.MyIntentService;
import ua.com.kistudio.birthdayapplication.util.Prefs;

/**
 * Created by Вiталя on 24.03.2016.
 */
public class InstallBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if ((intent.getAction().equals(Intent.ACTION_INSTALL_PACKAGE))
                ||(intent.getAction().equals(Intent.ACTION_PACKAGE_ADDED))) {
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            Intent i = new Intent(MyIntentService.ACTION_UPDATE);
            PendingIntent pendingIntent = PendingIntent.getService(context, 1, i, PendingIntent.FLAG_NO_CREATE);
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,
                    Calendar.getInstance().getTimeInMillis(), 1000 * 60 * 60 * 24, pendingIntent);
        }
    }
}
