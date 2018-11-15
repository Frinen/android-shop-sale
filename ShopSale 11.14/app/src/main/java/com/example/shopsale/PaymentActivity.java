package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    ArrayList<ListItem> selectedItems = new ArrayList();
    ListView paymentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        Bundle arguments = getIntent().getExtras();

        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");


        }
        catch (Exception ex)
        {

        }
        paymentList = (ListView) findViewById(R.id.paymentList);
        // создаем адаптер
        PaymentAdapter adapter = new PaymentAdapter(this, R.layout.payment_item, selectedItems);
        // устанавливаем адаптер
        paymentList.setAdapter(adapter);
        if(selectedItems.size()>0)
        {
            int sum=0;
            for (ListItem item :selectedItems)
            {
                sum += item.count*item.price;
            }
            TextView tv = (TextView) findViewById(R.id.allPrice);
            tv.setText(Integer.toString(sum));
        }
    }

    public void toMenue(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("List" , (Serializable)selectedItems);
        startActivity(intent);
    }

    public void ToCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra("List", (Serializable) selectedItems);
        startActivity(intent);
    }
}
