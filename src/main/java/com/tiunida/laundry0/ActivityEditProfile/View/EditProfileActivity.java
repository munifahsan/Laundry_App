package com.tiunida.laundry0.ActivityEditProfile.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityEditProfile.Presenter.EditProfilePresenter;
import com.tiunida.laundry0.ActivityEditProfile.Presenter.EditProfilePresenterMvp;
import com.tiunida.laundry0.ActivityMain.MainActivity;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity implements EditProfileViewMvp {

    @BindView(R.id.edit_profile_progress)
    ProgressBar mEditProfileProgres;
    @BindView(R.id.edit_profile_name)
    EditText mEditProfileName;
    @BindView(R.id.edit_profile_nim)
    EditText mEditProfileNim;
    @BindView(R.id.edit_profile_dormitory)
    EditText mEditProfileDormitory;
    @BindView(R.id.edit_profile_room_number)
    EditText mEditProfileRoom;
    @BindView(R.id.edit_profile_phone_number)
    EditText mEditProfilePhone;
    @BindView(R.id.edit_profile_status)
    MaterialBetterSpinner mEditProfileStatus;
    @BindView(R.id.edit_profile_gender)
    MaterialBetterSpinner mEditProfileGender;
    @BindView(R.id.edit_profile_btn_save)
    Button mEditProfileBtnSave;

    private boolean isChanged = false;
    String status = "";
    String gender = "";
    String [] SPINNERLIST_STATUS={"Mahasiswa","Staff","Dosen","Tamu"};
    String [] SPINNERLIST_GENDER={"MALE","FEMALE"};

    private EditProfilePresenterMvp mEditProfilePresenterMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        ButterKnife.bind(this);

        mEditProfilePresenterMvp = new EditProfilePresenter(this);
        mEditProfilePresenterMvp.onCreate();

        getDataProfile();

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,SPINNERLIST_STATUS);

        mEditProfileStatus.setAdapter(arrayAdapterStatus);

        mEditProfileStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                status = mEditProfileStatus.getText().toString();
            }
        });

        ArrayAdapter<String> arrayAdapterGender = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,SPINNERLIST_GENDER);

        mEditProfileGender.setAdapter(arrayAdapterGender);

        mEditProfileGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                gender = mEditProfileGender.getText().toString();
            }
        });
    }

    @Override
    protected void onDestroy() {
        mEditProfilePresenterMvp.onDestroy();
        super.onDestroy();
    }

    public void getDataProfile(){
        mEditProfilePresenterMvp.getProfileData();
    }

    public void hideProgress(){
        mEditProfileProgres.setVisibility(View.INVISIBLE);
    }

    public void showProgress(){
        mEditProfileProgres.setVisibility(View.VISIBLE);
    }

    public void navigateToMainScreen(){
        Intent mainIntent = new Intent(EditProfileActivity.this, MainActivity.class);
        mainIntent.putExtra("tab2",2);
        startActivity(mainIntent);
        finish();
    }

    public void showMessage(String message){
        Toast.makeText(EditProfileActivity.this, message, Toast.LENGTH_LONG).show();
    }

    public void setInputs(boolean enabeled){
        mEditProfileBtnSave.setEnabled(enabeled);
        mEditProfileGender.setEnabled(enabeled);
        mEditProfileStatus.setEnabled(enabeled);
        mEditProfileName.setEnabled(enabeled);
        mEditProfileNim.setEnabled(enabeled);
        mEditProfileDormitory.setEnabled(enabeled);
        mEditProfileRoom.setEnabled(enabeled);
        mEditProfilePhone.setEnabled(enabeled);
    }

    public void enableInputs(boolean enabeled){
        setInputs(true);
    }

    public void disableInputs(boolean enabeled){
        setInputs(false);
    }

    public void nameFieldError(String errorMsg){
        mEditProfileName.setError(errorMsg);
    }

    public void nimFieldError(String errorMsg){
        mEditProfileNim.setError(errorMsg);
    }

    public void dormitoryFieldError(String errorMsg){
        mEditProfileDormitory.setError(errorMsg);
    }

    public void roomFieldError(String errorMsg){
        mEditProfileRoom.setError(errorMsg);
    }

    public void phoneFieldError(String errorMsg){
        mEditProfilePhone.setError(errorMsg);
    }

    @OnClick(R.id.edit_profile_btn_save)
    public void saveInput(){
        final String user_name = mEditProfileName.getText().toString();
        final String user_nim = mEditProfileNim.getText().toString();
        final String user_dormitory = mEditProfileDormitory.getText().toString();
        final String user_room = mEditProfileRoom.getText().toString();
        final String user_phone = mEditProfilePhone.getText().toString();
        final String user_status = status;
        final String user_gender = gender;

        mEditProfilePresenterMvp.validateInput(user_name,user_dormitory,user_room,user_phone,user_gender,user_status);
    }

    public void setData(String dataName, String dataDormitory, String dataRoom, String dataPhone, String dataStatus, String dataGender){
        mEditProfileName.setText(dataName);
       // mEditProfileNim.setText(dataNim);
        mEditProfileDormitory.setText(dataDormitory);
        mEditProfileRoom.setText(dataRoom);
        mEditProfilePhone.setText(dataPhone);
        mEditProfileStatus.setText(dataStatus);
        mEditProfileGender.setText(dataGender);
    }

}
