package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

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
        startActivity(intent);

    }
}
