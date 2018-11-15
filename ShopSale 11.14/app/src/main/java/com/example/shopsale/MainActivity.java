package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<ListItem> selectedItems = new ArrayList();
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle arguments = getIntent().getExtras();
        tv = (TextView) findViewById(R.id.arrayView);
        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");
            tv.setText(selectedItems.toString());
        }
        catch (Exception ex)
        {

        }

    }

    public void onItemsList(View view) {
        Bundle arguments = getIntent().getExtras();
        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");

        }
        catch (Exception ex)
        {

        }
        Intent intent = new Intent(this, ItemListActivity.class);
        intent.putExtra("List",(Serializable) selectedItems);
        startActivity(intent);
    }


    public void ToCart(View view) {
        Bundle arguments = getIntent().getExtras();
        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");

        }
        catch (Exception ex) {

        }
            Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("List",(Serializable) selectedItems);
        startActivity(intent);
    }

    public void toPayment(View view) {
        Intent intent = new Intent(this, PaymentActivity.class);
        intent.putExtra("List",(Serializable) selectedItems);
        startActivity(intent);
    }
}
