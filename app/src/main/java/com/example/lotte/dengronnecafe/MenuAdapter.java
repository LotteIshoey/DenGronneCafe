package com.example.lotte.dengronnecafe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends ArrayAdapter<Menulist> {
        public MenuAdapter(Context context, ArrayList<Menulist> menus){
            super(context, 0, menus);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            //Get the data item for this position
            Menulist menulist = getItem(position);
            //Check if an exixting view is being ruseed, otherwise inflate the view
            if (convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_menu, parent, false);
            }
            //Lookup view for data population
            TextView salatName = (TextView) convertView.findViewById(R.id.salatName);
            TextView salatPrice = (TextView) convertView.findViewById(R.id.salatPrice);
            TextView undername = (TextView) convertView.findViewById(R.id.undername);
            //Populate the data into the template view using the data object
            salatName.setText(menulist.name);
            salatPrice.setText(menulist.price);
            salatPrice.setText(menulist.price);
            undername.setText(menulist.undertitle);
            //Return the completed view render on screen
            return convertView;

        }

}
