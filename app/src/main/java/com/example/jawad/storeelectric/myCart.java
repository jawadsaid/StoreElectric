package com.example.jawad.storeelectric;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.jawad.storeelectric.api.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class myCart extends Fragment {

    @Bind(R.id.expListCart)
    ExpandableListView expListCart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycart, container, false);
        view.setBackgroundResource(R.mipmap.wallpaper);
        ButterKnife.bind(this, view);
        Utils.getMyCart(getContext(), expListCart, getFragmentManager());
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
