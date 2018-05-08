package com.movieku.app.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.movieku.app.Configuration.SharedPrefManager;
import com.movieku.app.Model.User;
import com.movieku.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfilFragment extends Fragment {

    View rootView;
    RelativeLayout logout;
    TextView Username, Email;

    public ProfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_profil, container, false);
        getActivity().setTitle("Profil");

        User user = SharedPrefManager.getInstance(getActivity()).getUser();

        String username = user.getUsername();
        String email = user.getEmail();

        Username = (TextView) rootView.findViewById(R.id.usernameprofil);
        Email = (TextView) rootView.findViewById(R.id.emailprofil);

        Username.setText(username);
        Email.setText(email);


        logout = (RelativeLayout) rootView.findViewById(R.id.logout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
                SharedPrefManager.getInstance(getActivity().getApplicationContext()).logout();
            }
        });

        return rootView;
    }

}
