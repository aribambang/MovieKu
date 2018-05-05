package com.movieku.app.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieku.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class BerandaFragment extends Fragment {
    View rootView;

    public BerandaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        getActivity().setTitle("Beranda");


        return rootView;
    }

}
