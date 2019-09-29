package com.tiunida.laundry0.XsetupImage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import com.tiunida.laundry0.FragmentProfile.ui.ProfileFragment;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

public class SetupImageActivity extends AppCompatActivity {

    @BindView(R.id.setup_image_circle)
    CircleImageView setupImage;
    @BindView(R.id.setup_btn_bla)
    Button setupbtn;

    private Uri mainImageURI = null;

    private String user_id;

    private boolean isChanged = false;

    private StorageReference storageReference;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    private Bitmap compressedImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup_image);

        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();

        firebaseFirestore = FirebaseFirestore.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();

        firebaseFirestore.collection("Users").document(user_id).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                if(task.isSuccessful()){

                    if(task.getResult().exists()){

                        String image = task.getResult().getString("image");

                        mainImageURI = Uri.parse(image);

                        RequestOptions placeholderRequest = new RequestOptions();
                        placeholderRequest.placeholder(R.drawable.default_image);

                        Glide.with(SetupImageActivity.this).setDefaultRequestOptions(placeholderRequest).load(image).into(setupImage);

                    }

                } else {

                    String error = task.getException().getMessage();
                    Toast.makeText(SetupImageActivity.this, "(FIRESTORE Retrieve Error) : " + error, Toast.LENGTH_LONG).show();

                }

            }
        });

//        setupbtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                if (mainImageURI != null) {
//
//
//                    if (isChanged) {
//
//                        user_id = firebaseAuth.getCurrentUser().getUid();
//
//                        File newImageFile = new File(mainImageURI.getPath());
//                        try {
//
//                            compressedImageFile = new Compressor(SetupImageActivity.this)
//                                    .setMaxHeight(125)
//                                    .setMaxWidth(125)
//                                    .setQuality(50)
//                                    .compressToBitmap(newImageFile);
//
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//
//                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//                        compressedImageFile.compress(Bitmap.CompressFormat.JPEG, 100, baos);
//                        byte[] thumbData = baos.toByteArray();
//
//                        UploadTask image_path = storageReference.child("profile_images").child(user_id + ".jpg").putBytes(thumbData);
//
//                        image_path.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//                            @Override
//                            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                                if (taskSnapshot.getMetadata() != null){
//                                    Task<Uri> result = taskSnapshot.getStorage().getDownloadUrl();
//                                    result.addOnSuccessListener(new OnSuccessListener<Uri>() {
//                                        @Override
//                                        public void onSuccess(Uri uri) {
//                                            String imageUrl = uri.toString();
//                                            storeFirestore(imageUrl);
//                                        }
//                                    });
//                                }
//                            }
//                        });
//
//                    } else {
//
//                        storeFirestore(null);
//
//                    }
//
//                }
//
//            }
//
//        });

        setupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                if (isChanged) {

                    if (mainImageURI != null) {

                        user_id = firebaseAuth.getCurrentUser().getUid();

                        final StorageReference image_path = storageReference.child("profile_images").child(user_id + ".jpg");
                        image_path.putFile(mainImageURI).continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                            @Override
                            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                                if (!task.isSuccessful()) {

                                    throw task.getException();

                                }

                                // Continue with the task to get the download URL
                                return image_path.getDownloadUrl();
                            }
                        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                            @Override
                            public void onComplete(@NonNull Task<Uri> task) {
                                if (task.isSuccessful()) {
                                    storeFirestore(task);
                                } else {
                                    // Handle failures
                                    // ...
                                    String errorMessage = task.getException().getMessage();
                                    Toast.makeText(SetupImageActivity.this, "Error :" + errorMessage, Toast.LENGTH_LONG).show();


                                }
                            }
                        });
                    }
                } else {

                    storeFirestore(null);

                }

            }
        });
        setupImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){

                    if(ContextCompat.checkSelfPermission(SetupImageActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){

                        Toast.makeText(SetupImageActivity.this, "Permission Denied", Toast.LENGTH_LONG).show();
                        ActivityCompat.requestPermissions(SetupImageActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

                    } else {

                        BringImagePicker();

                    }

                } else {

                    BringImagePicker();

                }

            }

        });

    }

//    private void storeFirestore(String imageUrl) {
//
//        Map<String, String> userMap = new HashMap<>();
//
//        userMap.put("image", imageUrl);
//
//        firebaseFirestore.collection("Users").document(user_id).set(userMap).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//
//                if(task.isSuccessful()){
//
//                    Toast.makeText(SetupImageActivity.this, "The user Settings are updated.", Toast.LENGTH_LONG).show();
//                    Intent mainIntent = new Intent(SetupImageActivity.this, HistoryFragment.class);
//                    startActivity(mainIntent);
//                    finish();
//
//                } else {
//
//                    String error = task.getException().getMessage();
//                    Toast.makeText(SetupImageActivity.this, "(FIRESTORE Error) : " + error, Toast.LENGTH_LONG).show();
//
//                }
//
//
//            }
//        });
//
//
//    }

    private void storeFirestore(@NonNull Task<Uri> task) {

        Uri download_uri;

        if(task != null) {

            download_uri = task.getResult();

        } else {

            download_uri = mainImageURI;

        }

        Map<String, String> userMap2 = new HashMap<>();

        userMap2.put("image", download_uri.toString());

        firebaseFirestore.collection("Users").document(user_id).set(userMap2).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(SetupImageActivity.this,"the setting are update", Toast.LENGTH_LONG).show();
                    Intent mainIntent = new Intent(SetupImageActivity.this, ProfileFragment.class);
                    startActivity(mainIntent);

                }else {
                    String errorMessage = task.getException().getMessage();
                    Toast.makeText(SetupImageActivity.this,"FIRESTORE Error :" + errorMessage, Toast.LENGTH_LONG).show();
                }
                //setupProgress.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void BringImagePicker() {

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .setAspectRatio(1, 1)
                .start(SetupImageActivity.this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                mainImageURI = result.getUri();
                setupImage.setImageURI(mainImageURI);

                isChanged = true;

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {

                Exception error = result.getError();

            }
        }

    }
}
