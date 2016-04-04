package com.example.jawad.DrinkTonight.api;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.example.jawad.DrinkTonight.AlcoholAdapter;
import com.example.jawad.DrinkTonight.Drawer.DrawerActivity;
import com.example.jawad.DrinkTonight.R;
import com.example.jawad.DrinkTonight.api.Items.Item;
import com.example.jawad.DrinkTonight.api.Items.ItemDialog;
import com.example.jawad.DrinkTonight.api.Items.ListOfItems;
import com.example.jawad.DrinkTonight.api.Login.UserExistJson;
import com.example.jawad.DrinkTonight.myCart;
import com.google.gson.Gson;

import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jawad on 3/16/2016.
 */
public class Utils {
    //Login
    public static String connection(final Context context, final String userName,String password){

        new AsyncTask<String, Void, UserExistJson>() {

            @Override
            protected UserExistJson doInBackground(String... params) {
                URL url = null;
                try {
                    url = new URL("https://collegeserver1.herokuapp.com/api/existuserpost");
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    //outPut Stream
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                   //OutputStream out =

                    String urlParams=String.format("username=%s&password=%s",params[0],params[1]);
                    DataOutputStream out = new DataOutputStream( con.getOutputStream());
                    out.writeBytes(urlParams);
                    out.flush();
                    out.close();
                    InputStream in = con.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(in);
                    Gson gson = new Gson();
                  //  UserResponse userResponse = gson.fromJson(streamReader, UserResponse.class);

                    UserExistJson existJson = gson.fromJson(streamReader,UserExistJson.class);
                return existJson;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(UserExistJson s) {
                if(s.getSuccess()){
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    // SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", userName);

                    editor.commit();
                    Intent intent = new Intent(context, DrawerActivity.class);
                    context.startActivity(intent);

                }else{
                    Toast.makeText(context, "Error , try again", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute(userName,password);



        return null;
    }
    //Sign Up
    public static String signUp(final Context context, final String userName,String password, final String email){
        new AsyncTask<String, Void, UserExistJson>() {

            @Override
            protected UserExistJson doInBackground(String... params) {
                URL url = null;
                try {
                    url = new URL("https://collegeserver1.herokuapp.com/api/insertuser");
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    //outPut Stream
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    //OutputStream out =

                    String urlParams=String.format("username=%s&password=%s&mail=%s",params[0],params[1],params[2]);
                    DataOutputStream out = new DataOutputStream( con.getOutputStream());
                    out.writeBytes(urlParams);
                    out.flush();
                    out.close();
                    InputStream in = con.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(in);
                    Gson gson = new Gson();
                    //  UserResponse userResponse = gson.fromJson(streamReader, UserResponse.class);

                    UserExistJson existJson = gson.fromJson(streamReader,UserExistJson.class);
                    return existJson;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(UserExistJson s) {
                if(s.getSuccess()){
                    SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
                    // SharedPreferences prefs = getSharedPreferences("login", MODE_PRIVATE);
                    SharedPreferences.Editor editor = prefs.edit();
                    editor.putString("username", email);
                    editor.putString("name",userName);

                    editor.commit();
                    Intent intent = new Intent(context, DrawerActivity.class);
                    context.startActivity(intent);

                }else{
                    Toast.makeText(context, "Error , try again", Toast.LENGTH_SHORT).show();
                }

            }
        }.execute(userName,password,email);



        return null;
    }

    public static void getItems(final Context context, final ExpandableListView expList, final android.support.v4.app.FragmentManager fragmentManager) {
        new AsyncTask<Void, Void, HashMap<String, List<Item>>>() {
            @Override
            protected HashMap<String, List<Item>> doInBackground(Void... params) {
                URL url = null;
                try {
                    url = new URL("https://collegeserver1.herokuapp.com/db/items");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();

                    InputStream in = con.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(in);
                    Gson gson = new Gson();
                    ListOfItems items = gson.fromJson(streamReader, ListOfItems.class);
                    con.disconnect();
                    in.close();
                    streamReader.close();
                    HashMap<String, List<Item>> AlcoholCategory = new HashMap<String, List<Item>>();
                    ArrayList<Item> allItems = (ArrayList) items.getItems();
                    List<Item> Beer = new ArrayList<>();
                    List<Item> Whisky = new ArrayList<>();
                    List<Item> Wine = new ArrayList<>();
                    List<Item> Vodka = new ArrayList<>();
                    List<Item> Other = new ArrayList<>();
                    for (Item tmp : allItems) {
                        if (tmp.getType().equalsIgnoreCase("Beer")) {
                            Beer.add(tmp);
                        } else if (tmp.getType().equalsIgnoreCase("Whisky")) {
                            Whisky.add(tmp);
                        } else if (tmp.getType().equalsIgnoreCase("Wine")) {
                            Wine.add(tmp);
                        } else if (tmp.getType().equalsIgnoreCase("Vodka")) {
                            Vodka.add(tmp);
                        } else
                            Other.add(tmp);
                    }
                    AlcoholCategory.put("1. Beer", Beer);
                    AlcoholCategory.put("4. Whisky", Whisky);
                    AlcoholCategory.put("2. Wine", Wine);
                    AlcoholCategory.put("3. Vodka", Vodka);
                    AlcoholCategory.put("5. Other", Other);
                    return AlcoholCategory;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }
            @Override
            protected void onPostExecute(final HashMap<String, List<Item>> AlcoholCategory) {
                if (AlcoholCategory == null)
                    return;
                final ArrayList<String> Alcohol_list = new ArrayList<>(AlcoholCategory.keySet());
                Collections.sort(Alcohol_list);
                AlcoholAdapter.Visible=false;
                AlcoholAdapter adapter = new AlcoholAdapter(context, AlcoholCategory, Alcohol_list);
                expList.setAdapter(adapter);



                expList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                        ItemDialog dialog = new ItemDialog();
                        // Item item = AlcoholCategory.get(groupPosition).get(childPosition);
                        Item item = AlcoholCategory.get(Alcohol_list.get(groupPosition)).get(childPosition);

                        String name = item.getName();
                        String description = item.getDescription();
                        String image = item.getImage();
                        String type = item.getType();
                        int price = item.getPrice();
                        int parentID = item.getTypeid();
                        int childID = item.getId();
                        Bundle bundle = new Bundle();
                        bundle.putString("name", name);
                        bundle.putString("type", type);
                        bundle.putString("desc", description);
                        bundle.putString("image", image);
                        bundle.putInt("price", price);
                        bundle.putInt("child", childID);
                        bundle.putInt("parent", parentID);
                        dialog.setArguments(bundle);
                        dialog.show(fragmentManager, "Test");
                        return true;
                    }
                });

            }
        }.execute();
    }


    public static void addToCart(final Context context, final int child, final int finalprice, final int quantity){
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                URL url = null;
                try {
                    url = new URL("https://collegeserver1.herokuapp.com/insertIntoCart");
                    HttpURLConnection con= (HttpURLConnection) url.openConnection();
                    //outPut Stream
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    //OutputStream out =
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String username = preferences.getString("username", null);
                    String urlParams=String.format("username=%s&child=%d&price=%d&quantity=%d",username,child,finalprice,quantity);
                    DataOutputStream out = new DataOutputStream( con.getOutputStream());
                    out.writeBytes(urlParams);
                    out.flush();
                    out.close();

                    InputStream in = con.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(in);
                    int data= streamReader.read();

                    while(data != -1){
                        char theChar = (char) data;
                        data = streamReader.read();
                    }
                    streamReader.close();

                   // Gson gson = new Gson();
                    //  UserResponse userResponse = gson.fromJson(streamReader, UserResponse.class);

                   // UserExistJson existJson = gson.fromJson(streamReader,UserExistJson.class);
                    //return existJson;

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                System.out.println("Done :)");
            }
        }.execute();
    }

    public static void deleteItemFromCart(final int id, final FragmentManager fragmentManager) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
               URL url;
                try {
                    url = new URL("https://collegeserver1.herokuapp.com/removeItemCart");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    //outPut Stream
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    DataOutputStream out = new DataOutputStream( con.getOutputStream());
                    String urlParams=String.format("id=%d",id);
                    out.writeBytes(urlParams);
                    out.flush();
                    out.close();

                    InputStream in = con.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(in);
                    int data= streamReader.read();
                    while(data != -1){
                        char theChar = (char) data;
                        data = streamReader.read();
                    }
                    streamReader.close();


                }
                catch (Exception e){
                    e.printStackTrace();
                }

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                fragmentManager.beginTransaction().
                        replace(R.id.drawerLayout, new myCart()).
                        commit();
            }
        }.execute();
    }


    public static void getMyCart(final Context context,final ExpandableListView expandableListView,final FragmentManager manager, final Boolean checkOut) {
        new AsyncTask<Void, Void, HashMap<String, List<Item>>>() {
            @Override
            protected HashMap<String, List<Item>> doInBackground(Void... params) {
                URL url = null;
                try {
                    url = new URL("https://collegeserver1.herokuapp.com/getItemCart");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setDoOutput(true);
                    con.setRequestMethod("POST");
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
                    String username = preferences.getString("username", null);
                    String urlParams = String.format("username=%s", username);
                    DataOutputStream out = new DataOutputStream(con.getOutputStream());
                    out.writeBytes(urlParams);
                    out.flush();
                    out.close();
                    InputStream in = con.getInputStream();
                    InputStreamReader streamReader = new InputStreamReader(in);
                    Gson gson = new Gson();
                    ListOfItems items = gson.fromJson(streamReader, ListOfItems.class);
                    con.disconnect();
                    in.close();
                    streamReader.close();
                    ArrayList<Item> allItems = (ArrayList) items.getItems();
                    HashMap<String, List<Item>> AlcoholCategory = new HashMap<String, List<Item>>();
                    List<Item> Beer = new ArrayList<>();
                    List<Item> Whisky = new ArrayList<>();
                    List<Item> Wine = new ArrayList<>();
                    List<Item> Vodka = new ArrayList<>();
                    List<Item> Other = new ArrayList<>();
                    for (Item tmp : allItems) {
                        if (tmp.getType().equalsIgnoreCase("Beer")) {
                            Beer.add(tmp);
                        } else if (tmp.getType().equalsIgnoreCase("Whisky")) {
                            Whisky.add(tmp);
                        } else if (tmp.getType().equalsIgnoreCase("Wine")) {
                            Wine.add(tmp);
                        } else if (tmp.getType().equalsIgnoreCase("Vodka")) {
                            Vodka.add(tmp);
                        } else
                            Other.add(tmp);
                    }
                    if (Beer.size() > 0)
                        AlcoholCategory.put("Beer", Beer);
                    if (Whisky.size() > 0)
                        AlcoholCategory.put("Whisky", Whisky);
                    if (Wine.size() > 0)
                        AlcoholCategory.put("Wine", Wine);
                    if (Vodka.size() > 0)
                        AlcoholCategory.put("Vodka", Vodka);
                    if (Other.size() > 0)
                        AlcoholCategory.put("Other", Other);
                    if(checkOut){
                        {
                            StringBuilder message=new StringBuilder("Hi Jawad , I need this drinks : ");
                            int totalPrice=0;
                            final ArrayList<String> Alcohol_list = new ArrayList<>(AlcoholCategory.keySet());
                            for (String parent : Alcohol_list) {
                                ArrayList<Item> itemArr = (ArrayList<Item>) AlcoholCategory.get(parent);
                                for (Item item : itemArr) {
                                    message.append("Bottle : " +item.getName() + " ");
                                    message.append("Quantity : " +item.getQuantity() + " ");
                                    message.append("And , " + " ");
                                    totalPrice+=item.getPrice();
                                }
                            }
                            if(totalPrice==0){
                                Toast.makeText(context, "Your cart is empty", Toast.LENGTH_SHORT).show();
                            }else {
                                String phoneNo = "+972548082189";
                                Intent sendIntent = new Intent(Intent.ACTION_SENDTO);
                                String msg = message.substring(0, message.length() - 7).toString() + " Total : " + totalPrice + "₪";
                                sendIntent.setData(Uri.parse("smsto:" + Uri.encode("0548082189")));
                                sendIntent.putExtra("sms_body", msg);
                                url = new URL("https://collegeserver1.herokuapp.com/checkout");
                                con = (HttpURLConnection) url.openConnection();
                                //outPut Stream
                                con.setDoOutput(true);
                                con.setRequestMethod("POST");
                                out = new DataOutputStream( con.getOutputStream());

                                urlParams=String.format("username=%s",username);
                                out.writeBytes(urlParams);
                                out.flush();
                                out.close();

                                in = con.getInputStream();
                                streamReader = new InputStreamReader(in);
                                int data= streamReader.read();
                                while(data != -1){
                                    char theChar = (char) data;
                                    data = streamReader.read();
                                }
                                streamReader.close();
                                con.disconnect();
                                in.close();
                                streamReader.close();
                                context.startActivity(Intent.createChooser(sendIntent, "Send a Msg"));


                            }
                        }
                    }
                    return AlcoholCategory;
                } catch (Exception e) {
                    e.printStackTrace();
                }


                return null;
            }



            @Override
            protected void onPostExecute(final HashMap<String, List<Item>> AlcoholCategory) {
                if (AlcoholCategory == null)
                    return;
                final ArrayList<String> Alcohol_list = new ArrayList<>(AlcoholCategory.keySet());
                Collections.sort(Alcohol_list);
                AlcoholAdapter.Visible = true;
                AlcoholAdapter adapter = new AlcoholAdapter(context, AlcoholCategory, Alcohol_list,manager);
                expandableListView.setAdapter(adapter);


            }
        }.execute();
    }
}
