package com.example.jawad.DrinkTonight.Drawer;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.jawad.DrinkTonight.R;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TabsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TabsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TabsFragment extends Fragment {


    @Bind(R.id.tabLayout)
    TabLayout tabLayout;
    @Bind(R.id.tabsViewPager)
    ViewPager tabsViewPager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tabs, container, false);
        ButterKnife.bind(this, view);

        setupTabs();


        return view;
    }

    private void setupTabs() {
        TabsPagerAdapter adapter = new TabsPagerAdapter
                (getChildFragmentManager());
        tabsViewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(tabsViewPager);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
