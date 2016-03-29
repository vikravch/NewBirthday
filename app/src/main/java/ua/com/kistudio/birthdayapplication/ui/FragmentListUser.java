package ua.com.kistudio.birthdayapplication.ui;

import android.app.LoaderManager;
import android.app.ProgressDialog;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import ua.com.kistudio.birthdayapplication.util.Prefs;
import ua.com.kistudio.birthdayapplication.R;

/**
 * Created by Вiталя on 18.03.2016.
 */
public class FragmentListUser extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    private ProgressDialog mProgressDialog;
    private CursorLoader mCursorLoader;
    ListView listView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_user,container,false);
        listView = (ListView) view.findViewById(R.id.lvUsers);
        getActivity().getLoaderManager().initLoader(1,null,this);
        return view;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        mProgressDialog = ProgressDialog.show(getActivity(),
                getActivity().getResources().getString(R.string.dialog_load_title),
                getActivity().getResources().getString(R.string.dialog_load_text));
        mCursorLoader = new CursorLoader(getActivity(), Prefs.API_URI,null,null,null,null);
        return mCursorLoader;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(getContext(),
                R.layout.fragment_user_item,data,
                new String[]{
                Prefs.FN_FIELD_NAME,Prefs.LN_FIELD_NAME,
                Prefs.BIRTHDAY_FIELD_NAME},
                new int[]{
                        R.id.tv_item_name,R.id.tv_item_surname,
                R.id.tv_item_dateBirthday},
                Adapter.NO_SELECTION);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,new String[]{
                getResources().getString(R.string.empty_user)
        });

        if (simpleCursorAdapter==null)
            listView.setAdapter(arrayAdapter);
        else
            listView.setAdapter(simpleCursorAdapter);
           // listView.setAdapter(arrayAdapter);

        final DialogUserEdit dialogUserEdit = new DialogUserEdit();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Bundle bundle = new Bundle();
                bundle.putInt("id",(int) id);
                dialogUserEdit.setArguments(bundle);

                dialogUserEdit.show(getActivity().getFragmentManager(),"dialog");

            }
        });

        mProgressDialog.dismiss();
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
