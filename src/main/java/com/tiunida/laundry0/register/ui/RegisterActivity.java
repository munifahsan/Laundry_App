package com.tiunida.laundry0.register.ui;

import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.tiunida.laundry0.main.MainActivity;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.login.ui.LoginActivity;
import com.tiunida.laundry0.register.RegisterPresenterMvp;
import com.tiunida.laundry0.register.RegisterPresenter;
import com.tiunida.laundry0.setup.ui.SetupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity implements RegisterViewMvp{

    @BindView(R.id.pass_reg_edt) EditText mPassEdt;
    @BindView(R.id.pass_cofrm_edt) EditText mConfirmPassEdt;
    @BindView(R.id.email_reg_edt) EditText mEmailRegEdt;
    @BindView(R.id.reg_btn) Button mRegBtn;
    @BindView(R.id.register_login) Button mRegLoginBtn;
    @BindView(R.id.register_progress) ProgressBar mProgressBar;

    //private Button mRegBtn, mRegLoginBtn;
    //private EditText mPassEdt, mConfirmPassEdt, mEmailRegEdt;

    private RegisterPresenterMvp mRegisterPresenterMvp;

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        mRegisterPresenterMvp = new RegisterPresenter(this);
        mRegisterPresenterMvp.onCreate();

        ButterKnife.bind(this);

//        mPassEdt = (EditText)findViewById(R.id.pass_reg_edt);
//        mConfirmPassEdt = (EditText)findViewById(R.id.pass_cofrm_edt);
//        mEmailRegEdt = (EditText)findViewById(R.id.email_reg_edt);
//
//        mRegBtn = (Button)findViewById(R.id.reg_btn);
//        mRegLoginBtn = (Button)findViewById(R.id.register_login);
//        mRegLoginBtn.setOnClickListener(this);
//        mRegBtn.setOnClickListener(this);

    }

//    @Override
//    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.reg_btn:
//                String email = mEmailRegEdt.getText().toString().trim();
//                String pass = mPassEdt.getText().toString().trim();
//                String confirmPass = mConfirmPassEdt.getText().toString().trim();
//
//                mRegisterMvpPresenter.registerUser(email, pass, confirmPass);
//
//                break;
//            case R.id.register_login:
//                Intent regLogin = new Intent(RegisterActivity.this, LoginActivity.class);
//                startActivity(regLogin);
//                finish();
//                break;
//        }
//    }


    @Override
    protected void onDestroy() {
        mRegisterPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @OnClick(R.id.reg_btn)
    @Override
    public void handleSignUp(){
        if (mRegisterPresenterMvp.isValifForm(mEmailRegEdt.getText().toString(),mPassEdt.getText().toString(),mConfirmPassEdt.getText().toString()))
        mRegisterPresenterMvp.validateRegister(mEmailRegEdt.getText().toString(),mPassEdt.getText().toString());

    }

    @OnClick(R.id.register_login)
    @Override
    public void navigateToLoginScreen(){
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null){

            navigateToMainScreen();
        }
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void enableInputs(){
        setInputs(true);
    }

    @Override
    public void disableInputs(){
        setInputs(false);
    }

    private void setInputs(Boolean enabeled){
        mEmailRegEdt.setEnabled(enabeled);
        mPassEdt.setEnabled(enabeled);
        mConfirmPassEdt.setEnabled(enabeled);
        mRegBtn.setEnabled(enabeled);
        mRegLoginBtn.setEnabled(enabeled);
    }

    private void navigateToMainScreen() {
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }

    @Override
    public void navigateToSetupScreen(){
        //showMessage("masuk cuuuy");
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(this, SetupActivity.class));
            finish();
        }
    }

    @Override
    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}
