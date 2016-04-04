package com.example.jawad.DrinkTonight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        checkLogin();
    }

    private void checkLogin() {
        //SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String username = preferences.getString("username", null);
        if(username==null){
            Intent intent = new Intent(this,LoginActivity.class);
            startActivity(intent);
        }
        else {
        /*    if(preferences.getString("name",null) ==null)
                Toast.makeText(HomeActivity.this, "Hello " +username, Toast.LENGTH_SHORT).show();
            else
            Toast.makeText(this, "Hello " + preferences.getString("name", "username"), Toast.LENGTH_SHORT).show();
        */}
    }

}
