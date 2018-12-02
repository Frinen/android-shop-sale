package com.example.shopsale;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationManagerCompat;
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
    TextView tv;
    TextView shippingView;
    int sum;
    int shipping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        Bundle arguments = getIntent().getExtras();
        shipping = 30;
        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");
            shipping = (int) arguments.get("Shipping");

        }
        catch (Exception ex)
        {

        }
        paymentList = (ListView) findViewById(R.id.paymentList);
        // создаем адаптер
        PaymentAdapter adapter = new PaymentAdapter(this, R.layout.payment_item, selectedItems);
        // устанавливаем адаптер
        paymentList.setAdapter(adapter);
        tv = (TextView) findViewById(R.id.allPrice);
        shippingView = (TextView) findViewById(R.id.shippingView);
        btn = (Button) findViewById(R.id.button4);
        if(selectedItems.size()>0)
        {
            sum=0;
            for (ListItem item :selectedItems)
            {
                sum += item.count*item.price;
            }
            sum+=shipping;

            tv.setText("Сума покупок: "+Integer.toString(sum));
            shippingView.setText("Доставка: "+Integer.toString(shipping));
        }
        else
        {
            tv.setText("");
            shippingView.setText("Товар не вибрано");
            btn.setVisibility(View.INVISIBLE);
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

    public void agree(View view) {
        if (((CheckBox) view).isChecked()) {
            btn.setEnabled(true);
        } else {
            btn.setEnabled(false);
        }
    }

    public void buy(View view) {
        CheckBox call = (CheckBox) findViewById(R.id.callCheck);
        String fullName="";
        if(call.isChecked())
        {
            Toast.makeText(this, "Замовлення оформлено! Ми вам передзвонимо.", Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this, "Замовлення оформлено!", Toast.LENGTH_SHORT).show();
        }
        try
        {
            TextView surname = (TextView) findViewById(R.id.surname);
            TextView name = (TextView) findViewById(R.id.name);
            TextView secondname = (TextView) findViewById(R.id.secondName);
            fullName = surname.getText()+" "+name.getText()+" "+secondname.getText();
        }
        catch (Exception ex){}
        Context context = getApplicationContext();

        Intent notificationIntent = new Intent(context,
                notification.class);

        notificationIntent.putExtra("List" , (Serializable)selectedItems);
        notificationIntent.putExtra("AllPrice", sum);
        notificationIntent.putExtra("Shipping", shipping);
        notificationIntent.putExtra("Name", fullName);
        PendingIntent contentIntent =
                PendingIntent.getActivity(context,0, notificationIntent,
                        PendingIntent.FLAG_CANCEL_CURRENT);
        Resources res = context.getResources();

        Notification.Builder builder = new Notification.Builder(context);

        builder.setContentIntent(contentIntent)
                .setSmallIcon(R.drawable.xtrade_logo_mini)
                .setContentTitle("Замовлення оформлено")
                .setTicker("Замовлення оформлено")
                .setContentText("Загальна сумма: "+Integer.toString(sum)+"грн");

        Notification notification = builder.getNotification();

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(this);

        notificationManager.notify(101, notification);




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
