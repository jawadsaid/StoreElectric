package com.example.jawad.DrinkTonight;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.jawad.DrinkTonight.api.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;


public class myCart extends Fragment {

    @Bind(R.id.expListCart)
    ExpandableListView expListCart;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mycart, container, false);
        //view.setBackgroundResource(R.mipmap.wallpaper);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        Utils.getMyCart(getContext(), expListCart, getFragmentManager(),false);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //   super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.checkout, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.checkout:
                onClick();
                break;
        }
        return true;
    }

    private void onClick() {
        Utils.getMyCart(getContext(), expListCart, getFragmentManager(),true);

    }
}
