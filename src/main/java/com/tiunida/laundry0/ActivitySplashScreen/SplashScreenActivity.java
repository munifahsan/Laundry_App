package com.tiunida.laundry0.ActivitySplashScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tiunida.laundry0.ActivityLogin.ui.LoginActivity;
import com.tiunida.laundry0.ActivityMain.MainActivity;
import com.tiunida.laundry0.ActivitySetup.ui.SetupActivity;
import com.tiunida.laundry0.R;

public class SplashScreenActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseFirestore firebaseFirestore;

    private String current_user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);

//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                finish();
//            }
//        }, 1000L); //3000 L = 3 detik
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();

        if (currentUser == null) {
            sendToLogin();
        } else {

            current_user_id = mAuth.getCurrentUser().getUid();

            firebaseFirestore.collection("Users").document(current_user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {

                        if (!task.getResult().exists()) {

                            sendToSetup();

                        }

                        if (task.getResult().exists()) {

                            sendToMain();

                        }

                    } else {

                        String errorMessage = task.getException().getMessage();
                        showMessage(errorMessage);
                    }

                }
            });

        }
    }

    private void sendToLogin() {
        Intent loginIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
        startActivity(loginIntent);
        finish();
    }

    private void sendToSetup() {
        Intent setupIntent = new Intent(SplashScreenActivity.this, SetupActivity.class);
        startActivity(setupIntent);
        finish();
    }

    private void sendToMain() {
        Intent setupIntent = new Intent(SplashScreenActivity.this, MainActivity.class);
        startActivity(setupIntent);
        finish();
    }

    public void showMessage(String message){
        Toast.makeText(SplashScreenActivity.this, "Error : " + message, Toast.LENGTH_LONG).show();

    }
}
