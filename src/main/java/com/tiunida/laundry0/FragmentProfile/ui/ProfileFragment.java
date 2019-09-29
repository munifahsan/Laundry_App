package com.tiunida.laundry0.FragmentProfile.ui;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityEditProfile.View.EditProfileActivity;
import com.tiunida.laundry0.ActivityHubungi.ui.HubungiActivity;
import com.tiunida.laundry0.ActivityLogin.ui.LoginActivity;
import com.tiunida.laundry0.FragmentProfile.ProfileFragPresenter;
import com.tiunida.laundry0.FragmentProfile.ProfileFragPresenterMvp;
import com.tiunida.laundry0.XsetupImage.SetupImageActivity;
import com.tiunida.laundry0.ActivitySaran.ui.SaranActivity;
import com.tiunida.laundry0.ActivitySyaratKetentuan.ui.SyaratKetentuanActivity;
import com.tiunida.laundry0.ActivityTentangAplikasi.ui.TentangAppActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements ProfileFragmentViewMvp{

    //private Button mLogOut;
    private FirebaseAuth mAuth;

    @BindView(R.id.profile_progress)
    ProgressBar mProfileProgres;
    @BindView(R.id.tentangAppBtn)
    Button mTentangAppBtn;
    @BindView(R.id.hubungiBtn)
    Button mHubungiBtn;
    @BindView(R.id.log_out)
    Button mLogOut;
    @BindView(R.id.edit_profile)
    Button mEditProfile;
    @BindView(R.id.profile_profile_name)
    TextView mProfileName;
    @BindView(R.id.profile_profile_nim)
    TextView mProfileNim;
    @BindView(R.id.profile_profile_dormitory)
    TextView mProfileDormitory;
    @BindView(R.id.profile_profile_room)
    TextView mProfileRoom;
    @BindView(R.id.profile_profile_phone)
    TextView mProfilePhone;
    @BindView(R.id.profile_profile_status)
    TextView mProfileStatus;
    @BindView(R.id.profile_profile_gender)
    TextView mProfileGender;
    @BindView(R.id.imageProfile)
    CircleImageView mProfileImage;
    private ProfileFragPresenterMvp mProfileFragPresenterMvp;
    private Unbinder unbinder;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragment = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        mProfileFragPresenterMvp = new ProfileFragPresenter(this);
        mProfileFragPresenterMvp.onCreate();

        unbinder = ButterKnife.bind(this,myFragment);

        getDataProfile();

        return myFragment;
    }

    public void hideProgress(){
        mProfileProgres.setVisibility(View.GONE);
    }

    public void showProgress(){
        mProfileProgres.setVisibility(View.VISIBLE);
    }

    public void getDataProfile(){
        mProfileFragPresenterMvp.getProfileData();
    }

    @OnClick(R.id.imageProfile)
    public void onImageClick(){
        sendToSetupImage();
        showMessage("ke ganti image halaman");
    }

    @OnClick(R.id.log_out)
    public void onLogOut(){
        mAuth.signOut();
        sendToLogin();
    }

    @OnClick(R.id.edit_profile)
    public void onEditProfile(){
        sendToEditPofile();
    }

    @OnClick(R.id.tentangAppBtn)
    public void onTentangBtnOnClick(){
        sendToTentang();
    }

    @OnClick(R.id.syaratDanKetentuanBtn)
    public void onSayaratKetentuanBtnOnClick(){
        sendToSyaratKetentuan();
    }

    @OnClick(R.id.saranBtn)
    public void onSaranBtnOnClick(){
        sendToSaran();
    }

    @OnClick(R.id.hubungiBtn)
    public void onHubungiOnClick(){
        sendToHubungi();
    }

    public void setData(String dataName, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender){
        mProfileName.setText(dataName);
        //mProfileNim.setText(dataNim);
        mProfileDormitory.setText(dataDormitory);
        mProfileRoom.setText(dataRoom);
        mProfilePhone.setText(dataPhone);
        mProfileStatus.setText(dataStatus);
        mProfileGender.setText(dataGender);
    }

    public void sendToEditPofile(){
        Intent editIntent = new Intent(getView().getContext(), EditProfileActivity.class);
        startActivity(editIntent);
    }

    private void sendToLogin() {
        Intent intent = new Intent(getView().getContext(), LoginActivity.class);
        startActivity(intent);
        getActivity().finish();
    }

    private void sendToSyaratKetentuan() {
        Intent intent = new Intent(getView().getContext(), SyaratKetentuanActivity.class);
        startActivity(intent);
    }

    private void sendToSaran() {
        Intent intent = new Intent(getView().getContext(), SaranActivity.class);
        startActivity(intent);
    }

    private void sendToTentang() {
        Intent intent = new Intent(getView().getContext(), TentangAppActivity.class);
        startActivity(intent);
    }

    private void sendToHubungi() {
        Intent intent = new Intent(getView().getContext(), HubungiActivity.class);
        startActivity(intent);
    }

    private void sendToSetupImage() {
        Intent intent = new Intent(getView().getContext(), SetupImageActivity.class);
        startActivity(intent);
    }

    public void showMessage(String message){
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();
    }

    public void setButtons(boolean enabeled){
        mEditProfile.setEnabled(enabeled);
        mLogOut.setEnabled(enabeled);
    }

}
