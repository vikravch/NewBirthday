package ua.com.kistudio.birthdayapplication.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import ua.com.kistudio.birthdayapplication.R;
import ua.com.kistudio.birthdayapplication.model.MyIntentService;
import ua.com.kistudio.birthdayapplication.util.Prefs;

public class MainActivity extends AppCompatActivity {

    private SectionPagerAdapter mFragmentsAdapter;
    private SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.setTitleTextColor(Color.GRAY);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        mFragmentsAdapter = new SectionPagerAdapter(getSupportFragmentManager(),this);

        ViewPager viewPager = (ViewPager) findViewById(R.id.vpStart);
        viewPager.setAdapter(mFragmentsAdapter);
        tabLayout.setupWithViewPager(viewPager);

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Prefs.ACTION_REQUEST);
        registerReceiver(mBroadcastReceiver, intentFilter);
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mBroadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        boolean isLogin = sharedPreferences.getBoolean(Prefs.SHARED_PREFERENCE_LOGGED,false);
        if (!isLogin) {
            menu.add(getResources().getString(R.string.registration_menu_title));
            menu.add(getResources().getString(R.string.log_in));
            menu.add(getResources().getString(R.string.update_list));
        } else
        {
            menu.add(sharedPreferences.getString(Prefs.SHARED_PREFERENCE_NAME,"Unknown"));
            menu.add(getResources().getString(R.string.log_out));
        }


        return super.onCreateOptionsMenu(menu);
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getTitle().equals(getResources().getString(R.string.registration_menu_title)))
        {
            DialogRegister dialogRegister = new DialogRegister();
            dialogRegister.show(getFragmentManager(),null);
//            MyIntentService.startActionRegister(this,null);
        }
        else if(item.getTitle().equals(getResources().getString(R.string.log_in))){
            DialogLogIn dialogLogIn = new DialogLogIn();
            dialogLogIn.show(getFragmentManager(),"tag1");
        }
        else if (item.getTitle().equals(getResources().getString(R.string.log_out))){
            sharedPreferences.edit()
                    .putBoolean(Prefs.SHARED_PREFERENCE_LOGGED,false)
                    .putString(Prefs.SHARED_PREFERENCE_NAME,null)
                    .commit();
            invalidateOptionsMenu();
        }
        else if (item.getTitle().equals(getResources().getString(R.string.update_list))){
                MyIntentService.startActionUpdate(this);
        }

        return super.onOptionsItemSelected(item);
    }

    private BroadcastReceiver mBroadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            DialogAlert dialogAlert = new DialogAlert();

            int requestCode = intent.getIntExtra(Prefs.EXTRA_REQUEST_CODE,0);
            switch (requestCode){
                case 0:
                    dialogAlert.setText(context.getResources().getString(R.string.emty_base_request_text));
                    break;
                case 1:
                    dialogAlert.setText(context.getResources().getString(R.string.success_registry_request_text));
                    break;
                case 2:
                    dialogAlert.setText(context.getResources().getString(R.string.error_registration_request_text));
                    break;
                case 3:
                    dialogAlert.setText(context.getResources().getString(R.string.already_created_request_text));
                    break;
                case 4:
                    dialogAlert.setText(context.getResources().getString(R.string.emil_used_request_text));
                    break;
                case 5:

                    dialogAlert.setText(context.getResources().getString(R.string.login_success_request_text));

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(Prefs.SHARED_PREFERENCE_NAME, intent.getStringExtra("name"));
                    editor.putBoolean(Prefs.SHARED_PREFERENCE_LOGGED, true);
                    editor.commit();
                    invalidateOptionsMenu();

                    break;
                case 6:
                    dialogAlert.setText(context.getResources().getString(R.string.login_error_request_text));
                    break;
                case 7:
                    dialogAlert.setText(context.getResources().getString(R.string.base_update_request_text));
                    break;

            }

            dialogAlert.show(getFragmentManager(),null);

        }
    };
}
