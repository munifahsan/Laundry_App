package com.tiunida.laundry0.login.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tiunida.laundry0.forgetPass.ForgetPassActivity;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.login.LoginPresenter;
import com.tiunida.laundry0.login.LoginPresenterMvp;
import com.tiunida.laundry0.main.MainActivity;
import com.tiunida.laundry0.register.ui.RegisterActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginMvpView {


    @BindView(R.id.login_progress) ProgressBar mProgressBar;
    @BindView(R.id.emailEdtTxt) EditText mEdtTxtEmail;
    @BindView(R.id.passEdtTxt) EditText mEdtTxtPass;
    @BindView(R.id.loginBtn) Button mLoginButton;
    @BindView(R.id.login_register) Button mLoginRegButton;
    @BindView(R.id.forget_pass) Button mLoginForgetPassButton;

    private LoginPresenterMvp mLoginPresenterMvp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        mLoginPresenterMvp = new LoginPresenter(this);
        mLoginPresenterMvp.onCreate();

    }

    @Override
    protected void onDestroy() {
        mLoginPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.loginBtn)
    public void handleSignIn() {
        if (mLoginPresenterMvp.isValifForm(mEdtTxtEmail.getText().toString(), mEdtTxtPass.getText().toString()))
        mLoginPresenterMvp.validateLogin(mEdtTxtEmail.getText().toString(), mEdtTxtPass.getText().toString());
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void enableInputs(){
        setInputs(true);
    }

    @Override
    public void disableInputs(){
        setInputs(false);
    }

    private void setInputs(boolean enabeled){
        mEdtTxtEmail.setEnabled(enabeled);
        mEdtTxtPass.setEnabled(enabeled);
        mLoginButton.setEnabled(enabeled);
        mLoginForgetPassButton.setEnabled(enabeled);
        mLoginRegButton.setEnabled(enabeled);
    }

    public void navigateToMainScreen(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @OnClick(R.id.login_register)
    @Override
    public void navigateToRegisterScreen(){
        startActivity(new Intent(this,RegisterActivity.class));
        finish();
    }

    @OnClick(R.id.forget_pass)
    @Override
    public void navigateToForgetPassScreen(){
        startActivity(new Intent(this,ForgetPassActivity.class));
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
