package ua.com.kistudio.birthdayapplication.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ua.com.kistudio.birthdayapplication.R;

/**
 * Created by Вiталя on 18.03.2016.
 */
public class FragmentEmpty extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_empty,container,false);
    }
}
