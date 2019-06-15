package com.tiunida.laundry0.orderFrag;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.order.ui.OrderActivity;

public class OrderFragment extends Fragment {

    private Button btnOrder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragment = inflater.inflate(R.layout.fragment_order, container, false);

        btnOrder = (Button)myFragment.findViewById(R.id.button);

        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent((getActivity()), OrderActivity.class);
                startActivity(intent);
            }
        });
        return myFragment;

    }

}
