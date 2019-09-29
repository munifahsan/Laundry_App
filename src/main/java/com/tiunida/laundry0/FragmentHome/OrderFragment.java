package com.tiunida.laundry0.FragmentHome;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tiunida.laundry0.R;
import com.tiunida.laundry0.ActivityOrder.biasa.ui.BiasaActivity;
import com.tiunida.laundry0.ActivityOrder.express.ui.ExpressActivity;
import com.tiunida.laundry0.ActivityOrder.kilat.ui.KilatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderFragment extends Fragment {

    private Button btnOrder;

    @BindView(R.id.order_biasa_btn)
    CardView mOrderBiasaBtn;
    @BindView(R.id.order_express_btn)
    CardView mOrderExpressBtn;
    @BindView(R.id.order_kilat_btn)
    CardView mOrderKilatBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View myFragment = inflater.inflate(R.layout.fragment_order, container, false);

        ButterKnife.bind(this,myFragment);

        return myFragment;
    }

    @OnClick(R.id.order_biasa_btn)
    public void orderBiasaOnClick(){
        Intent intent = new Intent((getActivity()), BiasaActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.order_express_btn)
    public void orderExpressOnClick(){
        Intent intent = new Intent((getActivity()), ExpressActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.order_kilat_btn)
    public void orderKilatOnClick(){
        Intent intent = new Intent((getActivity()), KilatActivity.class);
        startActivity(intent);
    }

}
