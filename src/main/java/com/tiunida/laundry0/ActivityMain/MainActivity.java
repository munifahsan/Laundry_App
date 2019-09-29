package com.tiunida.laundry0.ActivityMain;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tiunida.laundry0.FragmentHome.OrderFragment;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.FragmentHistory.HistoryFragment;
import com.tiunida.laundry0.ActivityLogin.ui.LoginActivity;
import com.tiunida.laundry0.FragmentProfile.ui.ProfileFragment;
import com.tiunida.laundry0.ActivitySetup.ui.SetupActivity;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    Fragment fragment;

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    private String current_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        getFragmentPage(new OrderFragment());

        BottomNavigationView mBottomNavigationView = findViewById(R.id.bnb_tab);
        mBottomNavigationView.setOnNavigationItemSelectedListener(this);

        Intent mainIntent = getIntent();
        int tabToOpen = mainIntent.getIntExtra("tab", -1);
        if (tabToOpen != -1) {
            getFragmentPage(new OrderFragment());
            mBottomNavigationView.getMenu().findItem(R.id.navigation_order).setChecked(true);
            finish();
        }

        Intent mainIntent2 = getIntent();
        int tabToOpen2 = mainIntent2.getIntExtra("tab2", -2);
        if (tabToOpen2 != -2) {
            getFragmentPage(new ProfileFragment());
            mBottomNavigationView.getMenu().findItem(R.id.navigation_profile).setChecked(true);
        }


    }

    private boolean getFragmentPage(Fragment fragment) {
        if (fragment != null) {
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
        switch (menuItem.getItemId()) {
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

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
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

//        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
//
//        if (currentUser == null) {
//            sendToLogin();
//        } else {
//
//            current_user_id = mAuth.getCurrentUser().getUid();
//
//            firebaseFirestore.collection("Users").document(current_user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
//                @Override
//                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
//
//                    if (task.isSuccessful()) {
//
//                        if (!task.getResult().exists()) {
//
//                            sendToSetup();
//
//                        }
//
//                    } else {
//
//                        String errorMessage = task.getException().getMessage();
//                        showMessage(errorMessage);
//                    }
//
//                }
//            });
//
//        }
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void sendToSetup() {
        Intent setupIntent = new Intent(MainActivity.this, SetupActivity.class);
        startActivity(setupIntent);
        finish();
    }

    public void showMessage(String message){
        Toast.makeText(MainActivity.this, "Error : " + message, Toast.LENGTH_LONG).show();

    }
}
