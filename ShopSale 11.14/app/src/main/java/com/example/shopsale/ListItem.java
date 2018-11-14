package com.example.shopsale;

import java.io.Serializable;

public class ListItem implements Serializable {
    public String name;
    public int count;
    public int price;
    public ListItem(String _name, int _count, int _price)
    {
        name = _name;
        count = _count;
        price = _price;
    }
}
