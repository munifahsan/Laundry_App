package com.tiunida.laundry0.ActivityLogin;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.tiunida.laundry0.EventBus.EventBus;
import com.tiunida.laundry0.EventBus.GreenRobotEventBus;
import com.tiunida.laundry0.ActivityLogin.events.LoginEvent;
import com.tiunida.laundry0.ActivityLogin.ui.LoginMvpView;

import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginPresenter implements LoginPresenterMvp {

    private Context mContect;
    private FirebaseAuth mAuth;
    private String TAG = "LoginPresenter";;
    private DatabaseReference mDatabase;

    private EventBus mEventBus;
    private LoginMvpView mLoginMvpView;
    private LoginInteractorMvp mLoginInteractorMvp;



    public LoginPresenter(LoginMvpView loginMvpView) {
        mLoginMvpView = loginMvpView;
        mLoginInteractorMvp = new LoginInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
//        this.mContect = mContect;
//        this.mAuth = mAuth;
//        this.mDatabase = mDatabase;
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mLoginMvpView = null;
        mEventBus.unregister(this);
    }
//    @Override
//    public boolean isValidForm(String email, String password){
//        boolean isValid = true;
//        if (email.isEmpty()) {
//            //String errorMessage = task.getException().getMessage();
//            isValid = false;
//            Toast.makeText(mContect,"email kosong" , Toast.LENGTH_LONG).show();
////            return;
//        }
//        if (!isEmailValid(email)) {
//            isValid = false;
//            Toast.makeText(mContect,"email ga valid" , Toast.LENGTH_LONG).show();
////            return;
//        }
//        if (password.isEmpty()) {
//            isValid = false;
//            Toast.makeText(mContect,"pass kosong" , Toast.LENGTH_LONG).show();
////            return;
//        }
//        return isValid;

//        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){
//            final ProgressDialog dialog = new ProgressDialog(mContect);
//            dialog.setMessage("loding");
//            dialog.setCancelable(false);
//            dialog.show();
//
//            mAuth.signInWithEmailAndPassword(email, password)
//                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                        @Override
//                        public void onComplete(@NonNull Task<AuthResult> task) {
//                            if (task.isSuccessful()) {
//                                // Sign in success, update UI with the signed-in user's information
//                                Log.d(TAG, "signInWithEmail:success");
//                                dialog.dismiss();
//                                //mLoginActivity.hideProgress();
//                                Intent intent = new Intent(mContect, MainActivity.class);
//                                mContect.startActivity(intent);
//                            } else {
//                                dialog.dismiss();
//                                // If sign in fails, display a message to the user.
//                                Log.w(TAG, "signInWithEmail:failure", task.getException());
//                                Toast.makeText(mContect, "email atau password anda tidak sesuai",
//                                        Toast.LENGTH_SHORT).show();
//                            }
//
//                            // ...
//                        }
//                    });
//        }

//    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event){
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError();
                break;
        }
    }

    public void onSignInSuccess(){
        if (mLoginMvpView != null){
            mLoginMvpView.navigateToMainScreen();
        }
    }

    public void onSignInError(){
        if (mLoginMvpView != null){
            mLoginMvpView.hideProgress();
            mLoginMvpView.enableInputs();
            mLoginMvpView.showMessage("Email dan password tidak sesuai");
        }
    }

    @Override
    public boolean isValifForm(String email, String password) {
        boolean isValid = true;
        if (email.isEmpty()) {
            //String errorMessage = task.getException().getMessage();
            isValid = false;
            mLoginMvpView.showMessage("Email kosong");
        }
        if (!isEmailValid(email)) {
            isValid = false;
            mLoginMvpView.showMessage("Email tidak falid");
        }
        if (password.isEmpty()) {
            isValid = false;
            mLoginMvpView.showMessage("Password kosong");
        }
        return isValid;
    }

    @Override
    public void validateLogin(String email, String password){
        if (mLoginMvpView != null){
            mLoginMvpView.disableInputs();
            mLoginMvpView.showProgress();
        }
        mLoginInteractorMvp.doSignIn(email,password);
    }

    public static boolean isEmailValid(String email) {
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
