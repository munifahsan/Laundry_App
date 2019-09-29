package com.tiunida.laundry0.ActivityLogin;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivityLogin.events.LoginEvent;

public class LoginRepository implements LoginRepositoryMvp {
    private Context mContect;
    private FirebaseAuth mAuth;
    private String TAG = "LoginPresenter";
    private DatabaseReference mDatabase;
    private LoginPresenterMvp mLoginPresenterMvp;

    public LoginRepository() {
        mAuth = FirebaseAuth.getInstance();
        //mLoginPresenterMvp = new LoginPresenter();
    }

    @Override
    public void signIn(String email, String password){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            //dialog.dismiss();
                            //mLoginActivity.hideProgress();
                            //Intent intent = new Intent(mContect, MainActivity.class);
                            //mContect.startActivity(intent);
                            postEvent(LoginEvent.onSignInSuccess);
                            //mLoginPresenterMvp.onSignInSuccess();
                        } else {
                            //dialog.dismiss();
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            //Toast.makeText(mContect, "email atau password anda tidak sesuai",
                                    //Toast.LENGTH_SHORT).show();
                            //postEvent(LoginEvent.onSignInError);
                            postEvent(LoginEvent.onSignInError);
                        }

                        // ...
                    }
                });
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }
}
