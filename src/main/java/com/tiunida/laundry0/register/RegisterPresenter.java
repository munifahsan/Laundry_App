package com.tiunida.laundry0.register;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.tiunida.laundry0.eventBus.EventBus;
import com.tiunida.laundry0.eventBus.GreenRobotEventBus;
import com.tiunida.laundry0.register.events.RegisterEvent;
import com.tiunida.laundry0.register.ui.RegisterViewMvp;

import org.greenrobot.eventbus.Subscribe;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterPresenter implements RegisterPresenterMvp {
    private Context mContect;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private String TAG = "LoginPresenter";

    private EventBus mEventBus;
    private RegisterViewMvp mRegisterViewMvp;
    private RegisterInteractorMvp mRegisterInteractorMvp;

    public RegisterPresenter(RegisterViewMvp registerViewMvp) {
//        this.mContect = mContect;
//        this.mAuth = mAuth;
//        this.mDatabase = mDatabase;
        mRegisterViewMvp = registerViewMvp;
        mRegisterInteractorMvp = new RegisterInteractor();
        mEventBus = GreenRobotEventBus.getInstance();
    }

    @Override
    public void onCreate() {
        mEventBus.register(this);
    }

    @Override
    public void onDestroy() {
        mRegisterViewMvp = null;
        mEventBus.unregister(this);
    }

//    @Override
//    public void registerUser(String email, String pass, String confirmPass) {
//
//        final ProgressDialog dialog = new ProgressDialog(mContect);
//        dialog.setMessage("loding");
//        dialog.setCancelable(false);
//        dialog.show();
//
//        if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass) && !TextUtils.isEmpty(confirmPass)){
//            if (pass.equals(confirmPass)){
//
//                mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//
//                        if (task.isSuccessful()){
//
//                            dialog.dismiss();
//                            Log.d(TAG, "signInWithEmail:success");
//
//                            Intent setupIntent = new Intent(mContect, SetupActivity.class);
//                            mContect.startActivity(setupIntent);
//
//                        }else {
//                            dialog.dismiss();
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//
//                            String errorMessage = task.getException().getMessage();
//                            Toast.makeText(mContect, "Error : " + errorMessage, Toast.LENGTH_LONG).show();
//
//                        }
//
//
//                    }
//                });
//
//            }else {
//                Toast.makeText(mContect, "confirm password and password confirm doesn't match", Toast.LENGTH_LONG).show();
//            }
//        }
//
//        }

    @Override
    @Subscribe
    public void onEventMainThread(RegisterEvent event){
        switch (event.getEventType()){
            case RegisterEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case RegisterEvent.onSignUpError:
                onSignUpError();
                break;

        }
    }

    public void onSignUpSuccess(){
        mRegisterViewMvp.showMessage("masuk on sign");
        if (mRegisterViewMvp != null){
            mRegisterViewMvp.navigateToSetupScreen();
        }
    }

    public void onSignUpError(){
        if (mRegisterViewMvp != null){
            mRegisterViewMvp.hideProgress();
            mRegisterViewMvp.enableInputs();
            mRegisterViewMvp.showMessage("Data yang anda masukan tidak falid");
        }
    }


    @Override
    public boolean isValifForm(String email, String password, String confirmPass) {
        boolean isValid = true;
        if (email.isEmpty()) {
            //String errorMessage = task.getException().getMessage();
            isValid = false;
            mRegisterViewMvp.showMessage("Email kosong");
        }
        if (!email.isEmpty() && !isEmailValid(email)) {
            isValid = false;
            mRegisterViewMvp.showMessage("Email tidak falid");
        }
        if (!email.isEmpty() && isEmailValid(email) && password.isEmpty()) {
            isValid = false;
            mRegisterViewMvp.showMessage("Password kosong");
        }
        if (!email.isEmpty() && !password.isEmpty() && confirmPass.isEmpty()) {
            isValid = false;
            mRegisterViewMvp.showMessage("Confirm Password kosong");
        }
        if (!email.isEmpty() && !password.isEmpty() && !confirmPass.isEmpty() && !password.equals(confirmPass)){
            isValid = false;
            mRegisterViewMvp.showMessage("confirm password dan password tidak sama");
        }
        return isValid;
    }

    @Override
    public void validateRegister(String email, String password){
        mRegisterViewMvp.showMessage("masuk on validate");
        if (mRegisterViewMvp != null){
            mRegisterViewMvp.disableInputs();
            mRegisterViewMvp.showProgress();
        }
        mRegisterInteractorMvp.doSignUp(email, password);
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
