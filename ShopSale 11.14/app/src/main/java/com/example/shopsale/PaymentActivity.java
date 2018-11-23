package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class PaymentActivity extends AppCompatActivity {

    ArrayList<ListItem> selectedItems = new ArrayList();
    ListView paymentList;
    Button btn;

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
            tv.setText("Сума покупок: "+Integer.toString(sum));
        }
        btn = (Button) findViewById(R.id.button4);
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

    public void agree(View view) {
        if (((CheckBox) view).isChecked()) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }

    public void buy(View view) {
        Toast.makeText(this, "Замовлення оформлено!", Toast.LENGTH_SHORT).show();
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

}
