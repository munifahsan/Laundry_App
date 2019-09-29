package com.tiunida.laundry0.ActivityHubungi.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityHubungi.HubungiPresenter;
import com.tiunida.laundry0.ActivityHubungi.HubungiPresenterMvp;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class HubungiActivity extends AppCompatActivity implements HubungiViewMvp{

    private HubungiPresenterMvp mHubungiPresenterMvp;

    @BindView(R.id.hubungi_progress)
    ProgressBar mHubungiProgress;
    @BindView(R.id.toolbarHubungi)
    Toolbar mToolbarHubungi;
    @BindView(R.id.hubungiNoTxt)
    TextView mHubungiNoTxt;
    @BindView(R.id.hubungiNoBtn)
    CircleImageView mHubungiNoBtn;
    @BindView(R.id.hubungiEmail)
    TextView mHubungiEmail;
    @BindView(R.id.hubungiDesc)
    TextView mHubungiDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubungi);

        mHubungiPresenterMvp = new HubungiPresenter(this);
        mHubungiPresenterMvp.onCreate();

        ButterKnife.bind(this);

        setSupportActionBar(mToolbarHubungi);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getSupportActionBar().setTitle("HUBUNGI");
        mToolbarHubungi.setNavigationIcon(R.drawable.ic_backspace_black);
        mToolbarHubungi.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        getData();
    }

    public void getData(){
        mHubungiPresenterMvp.getData();
    }
    @Override
    protected void onDestroy() {
        mHubungiPresenterMvp.onDestroy();
        super.onDestroy();
    }

    @Override
    public void hideProgress(){
        mHubungiProgress.setVisibility(View.GONE);
    }
    @Override
    public void setNoTxt(String txt){
        mHubungiNoTxt.setText(txt);
    }

    @Override
    public void setEmailTxt(String txt){
        mHubungiEmail.setText(txt);
    }

    @Override
    public void setNoDescTxt(String txt){
        mHubungiDesc.setText(txt);
    }

    @OnClick(R.id.hubungiNoBtn)
    public void onNoBtnOnClick(){
        String phoneNumber = mHubungiNoTxt.getText().toString();
        Intent dialPhoneNumber = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+phoneNumber));
        startActivity(dialPhoneNumber);
    }

}
