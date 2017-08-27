package com.hani.contactsmap;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;


public class SinglePersonActivity extends Activity {

    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_person);

        Intent in = getIntent();

        String name = in.getStringExtra(TAG_NAME);
        String email = in.getStringExtra(TAG_EMAIL);


        TextView lblName = (TextView) findViewById(R.id.name_label);
        TextView lblEmail = (TextView) findViewById(R.id.email_label);


        lblName.setText(name);
        lblEmail.setText(email);
    }
}

