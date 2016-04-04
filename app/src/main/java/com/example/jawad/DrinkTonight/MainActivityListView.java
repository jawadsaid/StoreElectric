package com.example.jawad.DrinkTonight;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.jawad.DrinkTonight.api.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivityListView extends android.support.v4.app.Fragment {
    @Bind(R.id.exp_list)
    ExpandableListView expList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_main_activity_list_view, container, false);
        ButterKnife.bind(this, view);
        Utils.getItems(getContext(), expList, getFragmentManager());
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
