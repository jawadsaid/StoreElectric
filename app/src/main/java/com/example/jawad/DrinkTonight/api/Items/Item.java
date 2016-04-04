package com.example.jawad.DrinkTonight.api.Items;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Item {

@SerializedName("id")
@Expose
private Integer id;
@SerializedName("type")
@Expose
private String type;
@SerializedName("typeid")
@Expose
private Integer typeid;
@SerializedName("name")
@Expose
private String name;
@SerializedName("description")
@Expose
private String description;
@SerializedName("quantity")
@Expose
private Integer quantity;
@SerializedName("price")
@Expose
private Integer price;
@SerializedName("image")
@Expose
private String image;

/**
* 
* @return
* The id
*/
public Integer getId() {
return id;
}

/**
* 
* @param id
* The id
*/
public void setId(Integer id) {
this.id = id;
}

/**
* 
* @return
* The type
*/
public String getType() {
return type;
}

/**
* 
* @param type
* The type
*/
public void setType(String type) {
this.type = type;
}

/**
* 
* @return
* The typeid
*/
public Integer getTypeid() {
return typeid;
}

/**
* 
* @param typeid
* The typeid
*/
public void setTypeid(Integer typeid) {
this.typeid = typeid;
}

/**
* 
* @return
* The name
*/
public String getName() {
return name;
}

/**
* 
* @param name
* The name
*/
public void setName(String name) {
this.name = name;
}

/**
* 
* @return
* The description
*/
public String getDescription() {
return description;
}

/**
* 
* @param description
* The description
*/
public void setDescription(String description) {
this.description = description;
}

/**
* 
* @return
* The quantity
*/
public Integer getQuantity() {
return quantity;
}

/**
* 
* @param quantity
* The quantity
*/
public void setQuantity(Integer quantity) {
this.quantity = quantity;
}

/**
* 
* @return
* The price
*/
public Integer getPrice() {
return price;
}

/**
* 
* @param price
* The price
*/
public void setPrice(Integer price) {
this.price = price;
}

/**
* 
* @return
* The image
*/
public String getImage() {
return image;
}

    /**
*
* @param image
* The image
*/
public void setImage(String image) {
this.image = image;
}

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", typeid=" + typeid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                ", image='" + image + '\'' +
                '}';
    }

}