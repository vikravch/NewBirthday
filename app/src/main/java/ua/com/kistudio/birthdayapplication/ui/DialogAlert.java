package ua.com.kistudio.birthdayapplication.ui;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Вiталя on 23.03.2016.
 */
public class DialogAlert extends DialogFragment {
    private String mText="default";

    public DialogAlert(){super();}

    public void setText(String text){
        mText = text;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(android.R.layout.simple_list_item_1,container,false);
        TextView textView = (TextView) view.findViewById(android.R.id.text1);
        textView.setText(mText);
        return view;
    }
}
