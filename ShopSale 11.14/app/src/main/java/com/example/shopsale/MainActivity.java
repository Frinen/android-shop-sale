package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.view.Menu;
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
        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");
            tv.setText(selectedItems.toString());
        }
        catch (Exception ex)
        {

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menue, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_list :
            {
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
                return true;
            }
            case R.id.action_cart:
            {
                Intent intent = new Intent(this, CartActivity.class);
                intent.putExtra("List",(Serializable) selectedItems);
                startActivity(intent);
                return true;
            }
            case R.id.action_payment:
            {
                Intent intent = new Intent(this, PaymentActivity.class);
                intent.putExtra("List",(Serializable) selectedItems);
                startActivity(intent);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
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
