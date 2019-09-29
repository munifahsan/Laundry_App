package com.tiunida.laundry0.ActivitySaran.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tiunida.laundry0.ActivitySaran.SaranPresenter;
import com.tiunida.laundry0.ActivitySaran.SaranPresenterMvp;
import com.tiunida.laundry0.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SaranActivity extends AppCompatActivity implements SaranViewMvp {
    private SaranPresenterMvp mSaranPresenterMvp;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    public String mUniqNumByTime;

    @BindView(R.id.saran_progress)
    ProgressBar mSaranProgress;
    @BindView(R.id.toolbarSaran)
    Toolbar mToolbarSaran;
    @BindView(R.id.saran_name)
    TextView mSaranName;
    @BindView(R.id.saran_text)
    TextView mSaranText;
    @BindView(R.id.saran_btn_kirim)
    Button mSaranBtnKirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saran);

        mSaranPresenterMvp = new SaranPresenter(this);
        mSaranPresenterMvp.onCreate();

        ButterKnife.bind(this);

        setSupportActionBar(mToolbarSaran);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("SARAN");
        mToolbarSaran.setNavigationIcon(R.drawable.ic_backspace_black);
        mToolbarSaran.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData();
    }

    @Override
    protected void onDestroy() {
        mSaranPresenterMvp.onDestroy();
        super.onDestroy();
    }

    public void getData() {
        mSaranPresenterMvp.getData();
    }

    public void setName(String name) {
        mSaranName.setText(name);
    }

    public void hideProgress() {
        mSaranProgress.setVisibility(View.GONE);
    }

    public void showProgress() {
        mSaranProgress.setVisibility(View.VISIBLE);
    }

    public void disableInputs() {
        setInputs(false);
    }

    public void enableinputs() {
        setInputs(true);
    }

    public void setInputs(Boolean enable) {
        mSaranText.setEnabled(enable);
    }

    public void setInputsEmpty() {
        mSaranText.setText("");
    }

    public void getUniqNumByTime() {
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MMddyyy");
        mUniqNumByTime = dateFormat.format(calendar.getTime());
    }

    @OnClick(R.id.saran_btn_kirim)
    public void onKirimBtnOnClick() {
        getUniqNumByTime();
        String saran = mSaranText.getText().toString();
        String name = mSaranName.getText().toString();
        String time = mUniqNumByTime;
        mSaranPresenterMvp.validateInputs(name, time, saran);
    }

    public void showMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();    }
}
