package com.example.shopsale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

public class ItemAdapter extends ArrayAdapter<ListItem> {
    private LayoutInflater inflater;
    private int layout;
    private List<ListItem> items;

    Context _context;
    public ItemAdapter(Context context, int resource, List<ListItem> states) {
        super(context, resource, states);
        this.items = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(final int position, View convertView, ViewGroup parent) {




        View view=inflater.inflate(this.layout, parent, false);
        _context = inflater.getContext();


        TextView price = (TextView) view.findViewById(R.id.priceView);
        TextView name = (TextView) view.findViewById(R.id.nameView);
        ImageView pic = (ImageView) view.findViewById(R.id.listPic);
        final TextView count = (TextView) view.findViewById(R.id.countView);

        final ListItem state = items.get(position);

        pic.setImageResource(state.pic);
        Button deleteButton = (Button) view.findViewById(R.id.delete);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                items.remove(position);
                notifyDataSetChanged();
            }
        });

        Button lessButton = (Button) view.findViewById(R.id.lessButton);
        lessButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(state.count>1) {
                    state.count--;
                    count.setText(Integer.toString(state.count));
                }
            }
        });

        Button moreButton = (Button) view.findViewById(R.id.moreButton);
        moreButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                state.count++;
                count.setText(Integer.toString(state.count));
            }
        });
        name.setText(state.name);
        count.setText(Integer.toString(state.count));
        price.setText(Integer.toString(state.price));
        return view;
    }
}
