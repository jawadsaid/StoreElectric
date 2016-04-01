package com.example.jawad.storeelectric;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ContactUsActivity extends Fragment {

    @Bind(R.id.subject)
    EditText subject;
    @Bind(R.id.emailBody)
    EditText emailBody;
    @Bind(R.id.linearLayout1)
    LinearLayout linearLayout1;
    @Bind(R.id.fab)
    FloatingActionButton fab;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // return super.onCreateView(inflater, container, savedInstanceState);
        setHasOptionsMenu(true);
        View view = inflater.inflate(R.layout.activity_main, container, false);
        ButterKnife.bind(this, view);
        return view;

    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
    @OnClick(R.id.fab)
    public void onClick() {
        String message = emailBody.getText().toString();
        Intent email = new Intent(Intent.ACTION_SEND);
        email.putExtra(Intent.EXTRA_EMAIL, new String[]{"saidj@post.bgu.ac.il"});
        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, message);

        // need this to prompts email client only
        email.setType("message/rfc822");

        startActivity(Intent.createChooser(email, "Choose an Email client"));

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
     //   super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.contact_us, menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.send_email:
                onClick();
                clear();
                return true;
            case R.id.clear_msg:
                clear();
                return true;
            default:
                return false;
        }
    }




    private void clear() {
        subject.setText("");
        subject.setHint("Enter Subject");
        emailBody.setText("");
        emailBody.setHint("Enter mail body");
    }
}
