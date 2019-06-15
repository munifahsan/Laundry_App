package com.tiunida.laundry0.register;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.register.events.RegisterEvent;

public class RegisterRepository implements RegisterRepositoryMvp {

    private FirebaseAuth mAuth;
    private String TAG = "LoginPresenter";

    public RegisterRepository(){
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){

//                    dialog.dismiss();
                    Log.d(TAG, "signInWithEmail:success");
//
//                    Intent setupIntent = new Intent(mContect, SetupActivity.class);
//                    mContect.startActivity(setupIntent);
                    postEvent(RegisterEvent.onSignUpSuccess);

                }else {
//                    dialog.dismiss();
//                    Log.w(TAG, "signInWithEmail:failure", task.getException());
//
//                    String errorMessage = task.getException().getMessage();
//                    Toast.makeText(mContect, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
                    postEvent(RegisterEvent.onSignUpError);
                }


            }
        });
    }

    private void postEvent(int type, String errorMessage) {
        RegisterEvent loginEvent = new RegisterEvent();
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
