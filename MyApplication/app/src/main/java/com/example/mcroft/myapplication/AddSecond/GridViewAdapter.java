package com.example.mcroft.myapplication.AddSecond;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mcroft.myapplication.First.Product;
import com.example.mcroft.myapplication.R;

import java.util.List;

public class GridViewAdapter extends ArrayAdapter<Add> {

    public GridViewAdapter(Context context, int resource, List<Add> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.cash_row, null);
        }
        Add add = getItem(position);
        ImageView img_cizgi = (ImageView) v.findViewById(R.id.cizgi);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);

        String[] cizgi = {"cizgi_k", "cizgi_ma", "cizgi_mo", "cizgi_s", "cizgi_y"};
        int ss = position % 5;
        switch (ss) {
            case 0:    img_cizgi.setImageResource(R.drawable.cizgi_k);
                break;
            case 1:    img_cizgi.setImageResource(R.drawable.cizgi_ma);
                break;
            case 2:    img_cizgi.setImageResource(R.drawable.cizgi_mo);
                break;
            case 3:    img_cizgi.setImageResource(R.drawable.cizgi_s);
                break;
            case 4:    img_cizgi.setImageResource(R.drawable.cizgi_y);
                break;
        }

        String im = add.getTyp();

        switch (im) {
            case "Dolar" :
                img.setImageResource(R.drawable.dolars);
                break;
            case "Euro" :
                img.setImageResource(R.drawable.euros);
                break;
            case "Sterlin" :
                img.setImageResource(R.drawable.sterlins);
                break;
            case "Bitcoin" :
                img.setImageResource(R.drawable.bitcoins);
                break;
        }

        txtTitle.setText(add.getMoney());
        txtDescription.setText(add.getTyp());

        return v;
    }
}
