package com.example.lotte.dengronnecafe;

import java.util.ArrayList;


public class Menulist {

    public String name;
    public String price;
    public String undertitle;

    // Object that is a type of Menu
    public Menulist(String name, String price, String undertitle) {
        this.name = name;
        this.price = price;
        this.undertitle = undertitle;
    }


    public static ArrayList<Menulist> getMenus() {
        ArrayList<Menulist> menus = new ArrayList<Menulist>();
        menus.add(new Menulist("Salatbar", "49 kr", "2 hours of all you can eat"));
        menus.add(new Menulist("Sandwich", "55 kr", "Tuna, Italian, Chicken"));
        menus.add(new Menulist("Minipizza", "20 kr", " "));
        menus.add(new Menulist("Organic drink", "25 kr", "Apple, Orange, Elderflower"));
        menus.add(new Menulist("Smoothies", "29 kr", "Orange/mango, Raspberry/Backcurrant "));
        menus.add(new Menulist("Coffee/Tea", "20 kr", " "));
        menus.add(new Menulist("Special Coffee", "30 kr", "Cafe Latte, Latte Macchiato ect."));
        return menus;

    }

}

