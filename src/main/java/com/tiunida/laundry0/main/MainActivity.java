package com.tiunida.laundry0.main;

import android.content.Intent;
import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.tiunida.laundry0.orderFrag.OrderFragment;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.historyFrag.HistoryFragment;
import com.tiunida.laundry0.login.ui.LoginActivity;
import com.tiunida.laundry0.profileFrag.ui.ProfileFragment;
import com.tiunida.laundry0.setup.ui.SetupActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getFragmentPage(new OrderFragment());

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bnb_tab);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        Intent mainIntent = getIntent();
        int tabToOpen = mainIntent.getIntExtra("tab",-1);
        if (tabToOpen != -1){
            getFragmentPage(new OrderFragment());
            mBottomNavigationView.getMenu().findItem(R.id.navigation_order).setChecked(true);
        }

        Intent mainIntent2 = getIntent();
        int tabToOpen2 = mainIntent2.getIntExtra("tab2",-2);
        if (tabToOpen2 != -2){
            getFragmentPage(new ProfileFragment());
            mBottomNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
        }


    }

    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;
        switch (menuItem.getItemId()){
            case R.id.navigation_order:
                fragment = new OrderFragment();
                break;
            case R.id.navigation_history:
                fragment = new HistoryFragment();
                break;
            case R.id.navigation_profile:
                fragment = new ProfileFragment();
                break;
        }
        return loadFragment(fragment);
    }


    private boolean loadFragment(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        //mPresenter.onMainStart();
        if (currentUser == null){
            sendToLogin();
        }
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void sendToSetup() {
        Intent loginIntent = new Intent(MainActivity.this, SetupActivity.class);
        startActivity(loginIntent);
    }
}
