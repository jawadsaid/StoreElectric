package com.example.jawad.storeelectric.api.Items;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jawad.storeelectric.AlcoholAdapter;
import com.example.jawad.storeelectric.R;
import com.example.jawad.storeelectric.api.Utils;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by jawad on 3/31/2016.
 */
public class ItemDialog extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        //return super.onCreateDialog(savedInstanceState);
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getActivity(), AlertDialog.THEME_HOLO_LIGHT);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.item_layout, null);

        TextView child_txtView = (TextView) view.findViewById(R.id.txtNameBottle);
        TextView descriptionTxtV= (TextView) view.findViewById(R.id.editText);
        TextView priceTxtView= (TextView) view.findViewById(R.id.txtPriceItem);
        final EditText quantity = (EditText) view.findViewById(R.id.edQuantityItem);
        ImageView imageView =(ImageView) view.findViewById(R.id.itemImageView);
        final String name = getArguments().getString("name");
       // String name = savedInstanceState.getString("name");
        final String desc = getArguments().getString("desc");
        final String image = getArguments().getString("image");
        final String type= getArguments().getString("type");
        final int price = getArguments().getInt("price");
        final int child = getArguments().getInt("child");
        final int parent= getArguments().getInt("parent");
        quantity.setText("1");
        child_txtView.setText(name);
        descriptionTxtV.setText(desc);
        priceTxtView.setText(price + getString(R.string.newShekel));
        Picasso.with(getContext()).load(AlcoholAdapter.url+type+"/"+image).resize(100,100).placeholder(R.drawable.ic_not_found).into(imageView);
        builder.setView(view);
        builder.setNegativeButton(R.string.Cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "Thea", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setPositiveButton(R.string.Add, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                EditText editText = (EditText) view.findViewById(R.id.edQuantityItem);
            //    Toast.makeText(getActivity(), "Quantity: " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                int quantityItem=Integer.parseInt(editText.getText().toString());
                if(quantityItem>0) {
                    int finalprice = price * quantityItem;
                    Utils.addToCart(child, finalprice, quantityItem);
                    Toast.makeText(getActivity(), "Your drink has been added successfully to your cart", Toast.LENGTH_SHORT).show();
                }else
                    Toast.makeText(getActivity(), "Error , Check Quantity", Toast.LENGTH_SHORT).show();
            }
        });
       // Dialog dialog = builder.create();
        return builder.create();
    }
}
