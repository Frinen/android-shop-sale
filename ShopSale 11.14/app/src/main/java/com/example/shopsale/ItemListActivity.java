package com.example.shopsale;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ItemListActivity extends AppCompatActivity {


    ListView itemsListViev;
    CheckBox item1;
    CheckBox item2;
    CheckBox item3;
    ArrayList<ListItem> selectedItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);


        itemsListViev = (ListView) findViewById(R.id.itemList);
        ItemAdapter adapter = new ItemAdapter(this, R.layout.list_item, selectedItems);
        itemsListViev.setAdapter(adapter);

        item1 = (CheckBox) findViewById(R.id.checkBox1);
        item2 = (CheckBox) findViewById(R.id.checkBox2);
        item3 = (CheckBox) findViewById(R.id.checkBox3);

        Bundle arguments = getIntent().getExtras();
        try {
            selectedItems = (ArrayList<ListItem>) arguments.getSerializable("List");
            for(int i =0 ; i<selectedItems.size(); i++ )
                  {
                      if(selectedItems.get(i).name.equals("Acer Aspire 3 A315-53G"))
                      {
                          item1.setChecked(true);
                      }
                      if(selectedItems.get(i).name.equals("Samsung Galaxy A5 2017"))
                      {
                          item2.setChecked(true);
                      }
                      if(selectedItems.get(i).name.equals("Canon EOS 77D"))
                      {
                          item3.setChecked(true);
                      }
            }

        }
        catch (Exception ex)
        {

        }
    }



    public void Details3(View view)
    {
        Intent intent = new Intent(this,DetailAcivity.class);
        intent.putExtra("Picture", "3");
        intent.putExtra("Name", "Canon EOS 77D Body Black (1892C020AA)");
        intent.putExtra("Description", "Матриця CMOS 22.3 x 14.9 мм, 24.2 Мп / підтримка карт пам'яті SD / SDHC / SDXC / LCD-дисплей 3\" / Full HD-відео / Wi-Fi / живлення від літій-іонного акумулятора / 131 x 76.2 x 99.9 мм, 540 г (з об'єктивом)");
        intent.putExtra("OldPrice", "24 999 грн");
        intent.putExtra("NewPrice", "22 699 грн");
        startActivityForResult(intent, 1);
    }

    public void Details1(View view)
    {
        Intent intent = new Intent(this,DetailAcivity.class);
        intent.putExtra("Picture", "1");
        intent.putExtra("Name", "Acer Aspire 3 A315-53G (NX.H18EU.031) Obsidian Black");
        intent.putExtra("Description", "Екран 15.6\" (1920x1080) Full HD, матовий / Intel Core i5-7200U (2.5 - 3.1 ГГц) / RAM 8 ГБ / SSD 256 ГБ / nVidia GeForce MX130, 2 ГБ / без ОД / LAN / Wi-Fi / Bluetooth / веб-камера / Linux / 2.1 кг / чорний");
        intent.putExtra("OldPrice", "19 799 грн");
        intent.putExtra("NewPrice", "17 499 грн");
        startActivityForResult(intent, 1);
    }

    public void Detail2(View view)
    {
        Intent intent = new Intent(this,DetailAcivity.class);
        intent.putExtra("Picture", "2");
        intent.putExtra("Name", "Samsung Galaxy A5 2017 Duos SM-A520 Black");
        intent.putExtra("Description", "Екран (5.2\", Super AMOLED, 1920x1080)/ Samsung Exynos 7880 (1.9 ГГц)/ основна камера: 16 Мп, фронтальна камера: 16 Мп/ RAM 3 ГБ/ 32 ГБ вбудованої пам'яті + microSD/SDHC (до 256 ГБ)/ 3G/ LTE/ GPS/ ГЛОНАСС/ підтримка 2-х SIM-карток (Nano-SIM)/ Android 6.0 (Marshmallow)/ 3000 мА·год");
        intent.putExtra("OldPrice", "7 499 грн");
        intent.putExtra("NewPrice", "6 999 грн");
        startActivityForResult(intent, 1);
    }

    public void ToMenue(View view) {
        selectedItems = new ArrayList<>();
        if(item1.isChecked())
        {
            selectedItems.add(new ListItem("Acer Aspire 3 A315-53G", 1, 17499));
        }
        if(item2.isChecked())
        {
            selectedItems.add(new ListItem("Samsung Galaxy A5 2017", 1, 6999));
        }
        if(item3.isChecked())
        {
            selectedItems.add(new ListItem("Canon EOS 77D", 1, 6999));
        }
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("List" , (Serializable)selectedItems);
        startActivity(intent);
    }


    public void ToCart(View view) {
        Intent intent = new Intent(this, CartActivity.class);
        selectedItems = new ArrayList<>();
        if(item1.isChecked())
        {
            selectedItems.add(new ListItem("Acer Aspire 3 A315-53G", 1, 17499));
        }
        if(item2.isChecked())
        {
            selectedItems.add(new ListItem("Samsung Galaxy A5 2017", 1, 6999));
        }
        if(item3.isChecked())
        {
            selectedItems.add(new ListItem("Canon EOS 77D", 1, 6999));
        }
                //intent.removeExtra("List");
        intent.putExtra("List",selectedItems);
        startActivity(intent);
    }
}
