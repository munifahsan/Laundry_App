package com.tiunida.laundry0.ActivityForgetPass.ui;

import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityLogin.ui.LoginActivity;

public class ForgetPassActivity extends AppCompatActivity {

    private Button mSendBtn;
    private EditText mForgetEmailEdt;

    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);

        mSendBtn = (Button) findViewById(R.id.send_email);
        mForgetEmailEdt = (EditText) findViewById(R.id.forget_email);

        firebaseAuth = FirebaseAuth.getInstance();

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.sendPasswordResetEmail(mForgetEmailEdt.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ForgetPassActivity.this, "Silahkan priksa E-mail anda", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(ForgetPassActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(ForgetPassActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }
}
