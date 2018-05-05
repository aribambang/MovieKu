package com.movieku.app.Activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.movieku.app.Fragment.FavoritFragment;
import com.movieku.app.Fragment.BerandaFragment;
import com.movieku.app.Fragment.ProfilFragment;
import com.movieku.app.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private BottomNavigationView bottomNavigationView;
    private final BerandaFragment berandaFragment = new BerandaFragment();
    private final FavoritFragment favoritFragment = new FavoritFragment();
    private final ProfilFragment profilFragment = new ProfilFragment();
    android.support.v4.app.Fragment  active = berandaFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_beranda:
                    if (active != berandaFragment){

                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.content, berandaFragment);
                        fragmentTransaction.commit();
                    }

                    else {
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.hide(active).show(berandaFragment).commit();
                    }
                    active = berandaFragment;
                    break;
                case R.id.navigation_favorit:
                    if (active != favoritFragment){
                        android.support.v4.app.FragmentTransaction fragmentFavoritTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentFavoritTransaction.replace(R.id.content, favoritFragment);
                        fragmentFavoritTransaction.commit();

                    }

                    else {
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.hide(active).show(favoritFragment).commit();
                    }

                    active = favoritFragment;
                    break;
                case R.id.navigation_profil:
                    if (active != profilFragment){
                        android.support.v4.app.FragmentTransaction fragmentProfilTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentProfilTransaction.replace(R.id.content, profilFragment);
                        fragmentProfilTransaction.commit();

                    }

                    else {
                        android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.hide(active).show(profilFragment).commit();
                    }

                    active = profilFragment;
                    break;
            }
            return true;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BerandaFragment berandaFragment = new BerandaFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.content, berandaFragment);
        fragmentTransaction.commit();

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
