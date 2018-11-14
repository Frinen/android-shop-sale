package com.example.shopsale;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class PaymentAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater inflater;
    private int layout;
    private List<ListItem> items;

    Context _context;
    public PaymentAdapter(Context context, int resource, List<ListItem> states) {
        super(context, resource, states);
        this.items = states;
        this.layout = resource;

        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {




        View view=inflater.inflate(this.layout, parent, false);
        _context = inflater.getContext();



        TextView name = (TextView) view.findViewById(R.id.nameView);
         TextView price = (TextView) view.findViewById(R.id.priceView);

        final ListItem state = items.get(position);


        name.setText(state.name);
        price.setText(Integer.toString(state.count)+" x "+Integer.toString(state.price));

        return view;
    }
}

