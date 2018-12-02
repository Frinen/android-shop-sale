package com.example.shopsale;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class notification extends Activity {
    ArrayList<ListItem> selectedItems = new ArrayList();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        Bundle arguments = getIntent().getExtras();

        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");
        }
        catch (Exception ex)
        {

        }
        TextView allPrice = (TextView) findViewById(R.id.allPrice);
        allPrice.setText("Загальна ціна: "+arguments.get("AllPrice").toString());
        TextView delivery = (TextView) findViewById(R.id.deliveryPrice);
        delivery.setText("Ціна доставки: "+arguments.get("Shipping").toString());
        TextView ids = (TextView) findViewById(R.id.ids);
        ids.setText("Отрмувач: "+arguments.get("Name").toString());

        ListView notificationList = (ListView) findViewById(R.id.notiList);
        // создаем адаптер
        PaymentAdapter adapter = new PaymentAdapter(this, R.layout.payment_item, selectedItems);
        // устанавливаем адаптер
        notificationList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.item_mene, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(id){
            case R.id.action_mian:
            {
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("List",(Serializable) selectedItems);
                startActivity(intent);
                return true;
            }
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
}
