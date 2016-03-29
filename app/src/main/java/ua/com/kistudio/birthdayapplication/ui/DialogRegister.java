package ua.com.kistudio.birthdayapplication.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ua.com.kistudio.birthdayapplication.R;
import ua.com.kistudio.birthdayapplication.model.AppUser;
import ua.com.kistudio.birthdayapplication.model.MyIntentService;

/**
 * Created by Вiталя on 22.03.2016.
 */
public class DialogRegister extends DialogFragment implements View.OnClickListener {
    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.dialog_register,container,false);

        view.findViewById(R.id.btnSendRegister).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v) {
        String etNameValue =
                ((EditText) view.findViewById(R.id.etNameRegister)).getText().toString();
        String etPasswordValue =
                ((EditText) view.findViewById(R.id.etPasswordRegister)).getText().toString();
        String etEmailValue =
                ((EditText) view.findViewById(R.id.etEmailRegister)).getText().toString();
        String idAndroidDevice = Settings.Secure.getString(
                getActivity().getContentResolver(), Settings.Secure.ANDROID_ID);
        AppUser user = new AppUser(etNameValue,etPasswordValue,idAndroidDevice,etEmailValue);
        MyIntentService.startActionRegister(getActivity(),user);
        dismiss();
    }
}
