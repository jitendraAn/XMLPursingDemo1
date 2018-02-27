package com.example.user.xmlpursingdemo1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DescriptionActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
        tv=(TextView)findViewById(R.id.des);

        Bundle bd=getIntent().getExtras();
        if(bd!=null)
        {
            String des=bd.getString("Description");
            tv.setText(des);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
