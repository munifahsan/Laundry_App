package com.tiunida.laundry0.Xhistory;

import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.tiunida.laundry0.R;

import butterknife.BindView;

public class HistoryExpressFragment extends Fragment {

    @BindView(R.id.tabBiasa)
    TabItem mTabBiasa;
    @BindView(R.id.historyTablayout)
    TabLayout mHistoryTablayout;
    @BindView(R.id.tabExpress)
    TabItem mTabExpress;
    @BindView(R.id.tabKilat)
    TabItem mTabKilat;
    @BindView(R.id.historyToolbar)
    Toolbar mHistoryToolbar;
    @BindView(R.id.historyViewPager)
    ViewPager mHistoryViewPager;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myFragment = inflater.inflate(R.layout.fragment_history_express, container, false);

        PageAdapter pageAdapter = new PageAdapter(getChildFragmentManager());
        mHistoryViewPager.setAdapter(pageAdapter);
        mHistoryViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mHistoryTablayout));

        return myFragment;
    }


}
