package ua.com.kistudio.birthdayapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import ua.com.kistudio.birthdayapplication.util.Prefs;
import ua.com.kistudio.birthdayapplication.R;

/**
 * Created by Вiталя on 18.03.2016.
 */
public class FragmentFeedback extends Fragment{

    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_feedback,container,false);

        mView.findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnButtonSend();
            }
        });

        return mView;
    }

    private void clickOnButtonSend() {
        String name = ((EditText) mView.findViewById(R.id.etNameFeedback)).getText().toString();
        String email = ((EditText) mView.findViewById(R.id.etEmailFeedback)).getText().toString();
        String massage = ((EditText) mView.findViewById(R.id.etMessageFeedback)).getText().toString();
        Log.d(Prefs.LOG_TAG,
                String.format("%s - name, %s - email, %s - massage",name,email,massage));
    }
}
