package com.tiunida.laundry0.setup.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import androidx.annotation.NonNull;
import com.google.android.material.textfield.TextInputLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.main.MainActivity;
import com.tiunida.laundry0.setup.SetupPresenter;
import com.tiunida.laundry0.setup.SetupPresenterMvp;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.HashMap;
import java.util.Map;

import androidx.fragment.app.Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class SetupActivity extends AppCompatActivity implements SetupViewMvp{

    @BindView(R.id.setup_btn)
    Button mSetupBtn;
    @BindView(R.id.gender)
    MaterialBetterSpinner mGender;
    @BindView(R.id.status)
    MaterialBetterSpinner mStatus;
    @BindView(R.id.profile_name)
    EditText mProfileName;
    @BindView(R.id.profile_nim)
    EditText mProfileNim;
    @BindView(R.id.profile_dormitory)
    EditText mProfileDormitory;
    @BindView(R.id.profile_room_number)
    EditText mProfileRoom;
    @BindView(R.id.profile_phone_number)
    EditText mProfilePhone;
    @BindView(R.id.text_input_name)
    TextInputLayout mTextInputName;
    @BindView(R.id.text_input_nim)
    TextInputLayout mTextInputNim;
    @BindView(R.id.text_input_dormitory)
    TextInputLayout mTextInputDormitory;
    @BindView(R.id.text_input_room)
    TextInputLayout mTextInputRoom;
    @BindView(R.id.text_input_phone_number)
    TextInputLayout mTextInputPhoneNumber;
    @BindView(R.id.setup_progress)
    ProgressBar mSetupProgres;

    private boolean isChanged = false;
    String status = "";
    String gender = "";
    String [] SPINNERLIST_STATUS={"Mahasiswa","Staff","Dosen","Tamu"};
    String [] SPINNERLIST_GENDER={"MALE","FEMALE"};
    private String user_id;

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;
    private SetupPresenterMvp mSetupPresenterMvp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);

        mSetupPresenterMvp = new SetupPresenter(this);
        mSetupPresenterMvp.onCreate();

        storageReference = FirebaseStorage.getInstance().getReference();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        firebaseFirestore = FirebaseFirestore.getInstance();

        ArrayAdapter<String> arrayAdapterStatus = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,SPINNERLIST_STATUS);

        mStatus.setAdapter(arrayAdapterStatus);

        mStatus.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                status = mStatus.getText().toString();
                Log.d("status_spinner: ", status);
            }
        });

        ArrayAdapter<String> arrayAdapterGender = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,SPINNERLIST_GENDER);

        mGender.setAdapter(arrayAdapterGender);

        mGender.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                gender = mGender.getText().toString();
                Log.d("status_spinner: ", status);
            }
        });

        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()){

                    if (task.getResult().exists()){
//                        Toast.makeText(SetupActivity.this,"datanya ada " , Toast.LENGTH_LONG).show();
                        String name = task.getResult().getString("name");
                        String image = task.getResult().getString("image");
                        String nim = task.getResult().getString("nim");
                        String room = task.getResult().getString("room");
                        String dormitory = task.getResult().getString("dormitory");
                        String phone = task.getResult().getString("phone");
                        String gender = task.getResult().getString("gender");
                        String status = task.getResult().getString("status");

                        mProfileName.setText(name);
                        mProfileRoom.setText(room);
                        mProfileNim.setText(nim);
                        mProfileDormitory.setText(dormitory);
                        mProfilePhone.setText(phone);
                        mGender.setText(gender);
                        mStatus.setText(status);
                        //RequestOptions placeHolderRequest = new RequestOptions();
                        //placeHolderRequest.placeholder(R.drawable.default_image);

                        //Glide.with(SetupActivity.this).setDefaultRequestOptions(placeHolderRequest).load(image).into(mSetupImage);
                    }
                } else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(SetupActivity.this,"FIRESTORE retrivew Error :" + errorMessage, Toast.LENGTH_LONG).show();
                }
                mSetupBtn.setEnabled(true);
            }
        });
    }

    @Override
    protected void onDestroy() {
        mSetupPresenterMvp.onDestroy();
        super.onDestroy();
    }

//    @OnClick(R.id.profile_image)
//    @Override
//    public void setupImage(){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
//
//            if (ContextCompat.checkSelfPermission(SetupActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
//
//                Toast.makeText(SetupActivity.this, "Permision Denied", Toast.LENGTH_LONG).show();
//                ActivityCompat.requestPermissions(SetupActivity.this, new String []{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
//
//            }else{
//
//                BringImagePicker();
//
//            }
//        }else {
//
//            BringImagePicker();
//
//        }
//    }

    public void nameFieldError(String errorMsg){
        mTextInputName.setError(errorMsg);
    }

    public void nimFieldError(String errorMsg){
        mTextInputNim.setError(errorMsg);
    }

    public void dormitoryFieldError(String errorMsg){
        mTextInputDormitory.setError(errorMsg);
    }

    public void roomFieldError(String errorMsg){
        mTextInputRoom.setError(errorMsg);
    }

    public void phoneFieldError(String errorMsg){
        mTextInputPhoneNumber.setError(errorMsg);
    }

    @Override
    public void BringImagePicker() {
        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .start(SetupActivity.this);
    }

    public void showMessage(String message){
        Toast.makeText(SetupActivity.this, message, Toast.LENGTH_LONG).show();
    }

    @OnClick(R.id.setup_btn)
    @Override
    public void confirmInput(){
        final String user_name = mProfileName.getText().toString();
        final String user_nim = mProfileNim.getText().toString();
        final String user_dormitory = mProfileDormitory.getText().toString();
        final String user_room = mProfileRoom.getText().toString();
        final String user_phone = mProfilePhone.getText().toString();
        final String user_status = status;
        final String user_gender = gender;

        //showMessage(mainImageURI.toString());
        mSetupPresenterMvp.validateInput(user_name,user_nim,user_dormitory,user_room,user_phone,user_gender,user_status);
    }

    private boolean getFragmentPage(Fragment fragment){
        if (fragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fl_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    public void setInputs(boolean enabeled){
        mSetupBtn.setEnabled(enabeled);
        mGender.setEnabled(enabeled);
        mStatus.setEnabled(enabeled);
        mProfileName.setEnabled(enabeled);
        mProfileNim.setEnabled(enabeled);
        mProfileDormitory.setEnabled(enabeled);
        mProfileRoom.setEnabled(enabeled);
        mProfilePhone.setEnabled(enabeled);
       }

    public void enableInputs(boolean enabeled){
        setInputs(true);
    }

    public void disableInputs(boolean enabeled){
        setInputs(false);
    }

    public void hideProgress() {
        mSetupProgres.setVisibility(View.INVISIBLE);
    }

    public void showProgress() {
        mSetupProgres.setVisibility(View.VISIBLE);
    }

    @Override
    public void navigateToMainScreen(){
        startActivity(new Intent(this,MainActivity.class));
    }

//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
//            CropImage.ActivityResult result = CropImage.getActivityResult(data);
//            if (resultCode == RESULT_OK) {
//
//                mainImageURI = result.getUri();
//                mSetupImage.setImageURI(mainImageURI);
//
//                isChanged = true;
//
//            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
//
//                Exception error = result.getError();
//
//            }
//        }
//
//    }
}
