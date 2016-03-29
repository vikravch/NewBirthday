package ua.com.kistudio.birthdayapplication.ui;

import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

import ua.com.kistudio.birthdayapplication.R;
import ua.com.kistudio.birthdayapplication.model.MyIntentService;
import ua.com.kistudio.birthdayapplication.model.User;
import ua.com.kistudio.birthdayapplication.util.Prefs;

/**
 * Created by Вiталя on 24.03.2016.
 */
public class DialogUserEdit extends DialogFragment {

    View v;

    BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Prefs.ACTION_USER_SEND)){
                User user = intent.getParcelableExtra(Prefs.EXTRA_USER);
                ((EditText) v.findViewById(R.id.etFirstNameUserEdit)).setText(user.getmFirstName());
                ((EditText) v.findViewById(R.id.etLastNameUserEdit)).setText(user.getmLastName());
                ((EditText) v.findViewById(R.id.etEmailUserEdit)).setText(user.getmEmail());
                DatePicker datePicker = (DatePicker) v.findViewById(R.id.datePicker);


            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialog_user_edit,container,false);

        Bundle bundle = getArguments();
        int id = bundle.getInt("id");
        MyIntentService.startActionGetOneUser(getActivity(),id);

        return v;
    }


}
