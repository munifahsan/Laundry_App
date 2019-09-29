package com.tiunida.laundry0.ActivityTentangAplikasi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityTentangAplikasi.TentangAppPresenter;
import com.tiunida.laundry0.ActivityTentangAplikasi.TentangAppPresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TentangAppActivity extends AppCompatActivity implements TentangAppViewMvp {
    private TentangAppPresenterMvp mTentangAppPresenterMvp;

    @BindView(R.id.toolbarTentang)
    Toolbar mToolbarTentang;

    @BindView(R.id.info)
    TextView mInfo;
    @BindView(R.id.tentang_progress)
    ProgressBar mTentangProgress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_app);

        mTentangAppPresenterMvp = new TentangAppPresenter(this);
        mTentangAppPresenterMvp.onCreate();

        ButterKnife.bind(this);

        setSupportActionBar(mToolbarTentang);

        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("TENTANG APLIKASI");
        mToolbarTentang.setNavigationIcon(R.drawable.ic_backspace_black);
        mToolbarTentang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData();
    }

    @Override
    protected void onDestroy() {
        mTentangAppPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    public void setInfoTxt(String info){
        mInfo.setText(info);
    }

    public void getData(){
        mTentangAppPresenterMvp.getData();
    }
    public void showProgress(){
        mTentangProgress.setVisibility(View.VISIBLE);
    }

    public void hideProgress(){
        mTentangProgress.setVisibility(View.GONE);
    }
}
