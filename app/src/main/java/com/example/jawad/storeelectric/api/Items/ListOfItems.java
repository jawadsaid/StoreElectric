package com.example.jawad.storeelectric.api.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ListOfItems {

@SerializedName("items")
@Expose
private List<Item> items = new ArrayList<Item>();

/**
* 
* @return
* The items
*/
public List<Item> getItems() {
return items;
}

/**
* 
* @param items
* The items
*/
public void setItems(List<Item> items) {
this.items = items;
}

}
