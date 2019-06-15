package com.tiunida.laundry0.profileFrag;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.tiunida.laundry0.R;
import com.tiunida.laundry0.login.ui.LoginActivity;

public class ProfileFragment extends Fragment {

    private Button mLogOut;
    private FirebaseAuth mAuth;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragment = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();

        mLogOut = (Button)myFragment.findViewById(R.id.log_out);
        mLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                sendToLogin();
            }
        });
        return myFragment;
    }

    private void sendToLogin() {

        Intent loginIntent = new Intent(getView().getContext(), LoginActivity.class);
        startActivity(loginIntent);
        getActivity().finish();

    }

}
