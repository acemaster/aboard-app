package com.vivekapp.android.aboard;
import com.actionbarsherlock.app.SherlockFragment;
import com.actionbarsherlock.internal.widget.IcsToast;
import com.vivekapp.android.aboard.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

    public class Fragment2 extends SherlockFragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            View rootView = inflater.inflate(R.layout.fragment2layout, container, false);
            
            return rootView;
        }
        @Override
        public void onActivityCreated (Bundle savedInstanceState)
        {
        super.onActivityCreated(savedInstanceState);
            super.onCreate(savedInstanceState);
            IcsToast.makeText(getActivity().getBaseContext(),"Working on it",Toast.LENGTH_SHORT).show();
    }
    }