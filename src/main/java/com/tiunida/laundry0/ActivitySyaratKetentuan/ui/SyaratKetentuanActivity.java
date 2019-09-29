package com.tiunida.laundry0.ActivitySyaratKetentuan.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivitySyaratKetentuan.SyaratKetentuanPersenter;
import com.tiunida.laundry0.ActivitySyaratKetentuan.SyaratKetentuanPresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SyaratKetentuanActivity extends AppCompatActivity implements SyaratKetentuanViewMvp {
    SyaratKetentuanPresenterMvp mSyaratKetentuanPresenterMvp;

    @BindView(R.id.toolbarSyaratKetentuan)
    Toolbar mToolbarSyaratKetentuan;

    @BindView(R.id.sayarat_ketentuan_progress)
    ProgressBar mSyaratKetentuanProgress;

    @BindView(R.id.rel1)
    RelativeLayout rel1;
    @BindView(R.id.rel2)
    RelativeLayout rel2;
    @BindView(R.id.rel3)
    RelativeLayout rel3;
    @BindView(R.id.rel4)
    RelativeLayout rel4;
    @BindView(R.id.rel5)
    RelativeLayout rel5;
    @BindView(R.id.rel6)
    RelativeLayout rel6;
    @BindView(R.id.rel7)
    RelativeLayout rel7;
    @BindView(R.id.rel8)
    RelativeLayout rel8;
    @BindView(R.id.rel9)
    RelativeLayout rel9;
    @BindView(R.id.rel11)
    RelativeLayout rel11;
    @BindView(R.id.rel12)
    RelativeLayout rel12;
    @BindView(R.id.rel13)
    RelativeLayout rel13;
    @BindView(R.id.rel14)
    RelativeLayout rel14;
    @BindView(R.id.rel15)
    RelativeLayout rel15;
    @BindView(R.id.rel10)
    RelativeLayout rel10;

    @BindView(R.id.allRel)
    RelativeLayout allRel;

    @BindView(R.id.txt1)
    TextView txt1;
    @BindView(R.id.txt2)
    TextView txt2;
    @BindView(R.id.txt3)
    TextView txt3;
    @BindView(R.id.txt4)
    TextView txt4;
    @BindView(R.id.txt5)
    TextView txt5;
    @BindView(R.id.txt6)
    TextView txt6;
    @BindView(R.id.txt7)
    TextView txt7;
    @BindView(R.id.txt8)
    TextView txt8;
    @BindView(R.id.txt9)
    TextView txt9;
    @BindView(R.id.txt10)
    TextView txt10;
    @BindView(R.id.txt11)
    TextView txt11;
    @BindView(R.id.txt12)
    TextView txt12;
    @BindView(R.id.txt13)
    TextView txt13;
    @BindView(R.id.txt14)
    TextView txt14;
    @BindView(R.id.txt15)
    TextView txt15;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_syarat_ketentuan);

        mSyaratKetentuanPresenterMvp = new SyaratKetentuanPersenter(this);
        mSyaratKetentuanPresenterMvp.onCreate();
        ButterKnife.bind(this);

        setSupportActionBar(mToolbarSyaratKetentuan);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("SYARAT DAN KETENTUAN");
        mToolbarSyaratKetentuan.setNavigationIcon(R.drawable.ic_backspace_black);
        mToolbarSyaratKetentuan.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        getData();
    }

    public void getData(){
        mSyaratKetentuanPresenterMvp.getData();
    }

    @Override
    protected void onDestroy() {
        mSyaratKetentuanPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showProgress(){
        mSyaratKetentuanProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress(){
        mSyaratKetentuanProgress.setVisibility(View.GONE);
    }

    @Override
    public void setTextAkad1(String text) {
        Log.d("akad1 activity",""+text);
        txt1.setText(text);
    }

    @Override
    public void setRelAllVisible(){
        allRel.setVisibility(View.VISIBLE);
    }

    @Override
    public void setTextAkad2(String text) {
        txt2.setText(text);
    }

    @Override
    public void setTextAkad3(String text) {
        txt3.setText(text);
    }

    @Override
    public void setTextAkad4(String text) {
        txt4.setText(text);
    }

    @Override
    public void setTextAkad5(String text) {
        txt5.setText(text);
    }

    @Override
    public void setTextAkad6(String text) {
        txt6.setText(text);
    }

    @Override
    public void setTextAkad7(String text) {
        txt7.setText(text);
    }

    @Override
    public void setTextAkad8(String text) {
        txt8.setText(text);
    }

    @Override
    public void setTextAkad9(String text) {
        txt9.setText(text);
    }

    @Override
    public void setTextAkad10(String text) {
        txt10.setText(text);
    }

    @Override
    public void setTextAkad11(String text) {
        txt11.setText(text);
    }

    @Override
    public void setTextAkad12(String text) {
        txt12.setText(text);
    }

    @Override
    public void setTextAkad13(String text) {
        txt13.setText(text);
    }

    @Override
    public void setTextAkad14(String text) {
        txt14.setText(text);
    }

    @Override
    public void setTextAkad15(String text) {
        txt15.setText(text);
    }

    @Override
    public void setAkad1Gone() {
        rel1.setVisibility(View.GONE);
    }

    @Override
    public void setAkad2Gone() {
        rel2.setVisibility(View.GONE);
    }

    @Override
    public void setAkad3Gone() {
        rel3.setVisibility(View.GONE);
    }

    @Override
    public void setAkad4Gone() {
        rel4.setVisibility(View.GONE);
    }

    @Override
    public void setAkad5Gone() {
        rel5.setVisibility(View.GONE);
    }

    @Override
    public void setAkad6Gone() {
        rel6.setVisibility(View.GONE);
    }

    @Override
    public void setAkad7Gone() {
        rel7.setVisibility(View.GONE);
    }

    @Override
    public void setAkad8Gone() {
        rel8.setVisibility(View.GONE);
    }

    @Override
    public void setAkad9Gone() {
        rel9.setVisibility(View.GONE);
    }

    @Override
    public void setAkad10Gone() {
        rel10.setVisibility(View.GONE);
    }

    @Override
    public void setAkad11Gone() {
        rel11.setVisibility(View.GONE);
    }

    @Override
    public void setAkad12Gone() {
        rel12.setVisibility(View.GONE);
    }

    @Override
    public void setAkad13Gone() {
        rel13.setVisibility(View.GONE);
    }

    @Override
    public void setAkad14Gone() {
        rel14.setVisibility(View.GONE);
    }

    @Override
    public void setAkad15Gone() {
        rel15.setVisibility(View.GONE);
    }

    @Override
    public void setAkad1Visible() {
        rel1.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad2Visible() {
        rel2.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad3Visible() {
        rel3.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad4Visible() {
        rel4.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad5Visible() {
        rel5.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad6Visible() {
        rel6.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad7Visible() {
        rel7.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad8Visible() {
        rel8.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad9Visible() {
        rel9.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad10Visible() {
        rel10.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad11Visible() {
        rel11.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad12Visible() {
        rel12.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad13Visible() {
        rel13.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad14Visible() {
        rel14.setVisibility(View.VISIBLE);
    }

    @Override
    public void setAkad15Visible() {
        rel15.setVisibility(View.VISIBLE);
    }

}
