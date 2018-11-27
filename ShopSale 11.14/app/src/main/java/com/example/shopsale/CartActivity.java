package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {
    ArrayList<ListItem> selectedItems = new ArrayList();
    ListView itemsList;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Bundle arguments = getIntent().getExtras();

        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");
          //  tv.setText(selectedItems.toString());
            // items.add(new ListItem("test",1));


        }
        catch (Exception ex)
        {

        }
        if(selectedItems!=null) {
            itemsList = (ListView) findViewById(R.id.itemList);
            // создаем адаптер
            ItemAdapter adapter = new ItemAdapter(this, R.layout.list_item, selectedItems);
            // устанавливаем адаптер
            itemsList.setAdapter(adapter);
        }
    }

    public void ToItemList(View view) {
        Intent intent = new Intent(this, ItemListActivity.class);
        intent.putExtra("List",selectedItems);
        startActivity(intent);
    }

    public void toPayment(View view) {
        Intent intent = new Intent(this,  PaymentActivity.class);
        intent.putExtra("List",selectedItems);

        int shipping = 0;
        RadioButton ukr = (RadioButton)  findViewById(R.id.UkrRadio);
        if(ukr.isChecked())
        {
            shipping = 30;
        }
        RadioButton nova = (RadioButton)  findViewById(R.id.NewRadio);
        if(nova.isChecked())
        {
            shipping = 60;
        }
        intent.putExtra("Shipping", shipping);

        startActivity(intent);

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
