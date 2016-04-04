package com.example.jawad.DrinkTonight;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jawad.DrinkTonight.api.Utils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends AppCompatActivity {

    @Bind(R.id.input_name)
    EditText inputName;
    @Bind(R.id.input_email)
    EditText inputEmail;
    @Bind(R.id.input_password)
    EditText inputPassword;
    @Bind(R.id.btn_signup)
    AppCompatButton btnSignup;
    @Bind(R.id.link_login)
    TextView linkLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @OnClick({R.id.btn_signup, R.id.link_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_signup:
                String name = inputName.getText().toString();
                String email = inputEmail.getText().toString();
                String password = inputPassword.getText().toString();
                if (checkEmail(name, email, password))
                    Utils.signUp(this, name, password, email);
                else
                    Toast.makeText(RegisterActivity.this, "Check Your Fields", Toast.LENGTH_SHORT).show();
                break;
            case R.id.link_login:
               // Intent intent = new Intent(this,LoginActivity.class);
               // startActivity(intent);
                finish();
                break;
        }
    }

    private boolean checkEmail(String name, String email, String password) {
        return name.length() == 0 || password.length() == 0 ? false :
                !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();
//        Pattern pattern;
//        Matcher matcher;
//
//        final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        pattern = Pattern.compile(EMAIL_PATTERN);
//        matcher = pattern.matcher(inputEmail.getText().toString());
//        return matcher.matches();
    }

    @OnClick(R.id.link_login)
    public void onClick() {
    }
}
