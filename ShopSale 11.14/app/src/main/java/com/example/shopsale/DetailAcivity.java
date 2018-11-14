package com.example.shopsale;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailAcivity extends AppCompatActivity {

    TextView nameView;
    TextView descriptionView;
    TextView oldPriceView;
    TextView newPriceView;
    ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_acivity);
        nameView = (TextView) findViewById(R.id.nameView);
        descriptionView = (TextView) findViewById(R.id.descriptionView);
        oldPriceView = (TextView) findViewById(R.id.oldPriceView);
        newPriceView = (TextView) findViewById(R.id.newPriceView);
        img = (ImageView) findViewById(R.id.imageView3);

        Bundle arguments = getIntent().getExtras();
        nameView.setText(arguments.get("Name").toString());
        descriptionView.setText(arguments.get("Description").toString());
        oldPriceView.setText(arguments.get("OldPrice").toString());
        newPriceView.setText(arguments.get("NewPrice").toString());

        String picName = arguments.get("Picture").toString();
        if(picName.equals("1"))
        {
            img.setImageResource( R.drawable.notebook);
        }
        if(picName.equals("2"))
        {
            img.setImageResource( R.drawable.smartphone);
        }
        if(picName.equals("3"))
        {
            img.setImageResource( R.drawable.camera);
        }
    }

    }

