package com.example.jawad.storeelectric;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jawad.storeelectric.api.Items.Item;

import java.util.HashMap;
import java.util.List;

import it.sephiroth.android.library.picasso.Picasso;

/**
 * Created by jawad on 3/30/2016.
 */
public class AlcoholAdapter extends BaseExpandableListAdapter {
    private Context ctx;
    private HashMap<String, List<Item>> Alcohol_Category;
    List<String> Alcohol_List;
    public static String url = "https://collegeserver1.herokuapp.com/images/";
    public AlcoholAdapter(Context ctx, HashMap<String, List<Item>> alcohol_Category, List<String> alcohol_List) {
        this.ctx = ctx;
        Alcohol_Category = alcohol_Category;
        Alcohol_List = alcohol_List;
    }

    @Override
    public int getGroupCount() {
        return Alcohol_List.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return Alcohol_Category.get(Alcohol_List.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return Alcohol_List.get(groupPosition);
    }

    @Override
    public Object getChild(int parent, int child) {
        return Alcohol_Category.get(Alcohol_List.get(parent)).get(child);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int parent, int child) {
        return child;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int parent, boolean isExpanded, View convertView, ViewGroup parentView) {
        String groupTitle = (String) getGroup(parent);
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.parent_layout, parentView, false);
        }
        TextView parentText = (TextView) convertView.findViewById(R.id.parent_txt);
        parentText.setText(groupTitle);
        return convertView;
    }

    @Override
    public View getChildView(int parent, int child,boolean lastChild, View convertview, ViewGroup parentView) {
        Item item = (Item) getChild(parent, child);
        String child_title = item.getName();
        int id = item.getId();
        String description =item.getDescription();
        String price = item.getPrice().toString();
        if (convertview == null) {
            LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertview = inflater.inflate(R.layout.child_layout, parentView, false);
        }
        TextView child_txtView = (TextView) convertview.findViewById(R.id.child_txt);
        TextView descriptionTxtV= (TextView) convertview.findViewById(R.id.status);
        TextView priceTxtView= (TextView) convertview.findViewById(R.id.txtPrice);
        descriptionTxtV.setText(description.substring(0,20));
        priceTxtView.setText(price+"₪");
        ImageView imageView = (ImageView) convertview.findViewById(R.id.profile_pic);
        Picasso.with(ctx).load(url+item.getType()+"/"+item.getImage()).resize(100,100).placeholder(R.drawable.ic_not_found).into(imageView);
        child_txtView.setText(child_title);

        return convertview;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
