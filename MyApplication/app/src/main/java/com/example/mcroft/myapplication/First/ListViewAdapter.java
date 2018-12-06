package com.example.mcroft.myapplication.First;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mcroft.myapplication.R;

import java.util.List;

public class ListViewAdapter extends ArrayAdapter<Product> {

    public ListViewAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
    }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;

        if(null == v) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.main_row, null);
        }
        Product product = getItem(position);
        ImageView img = (ImageView) v.findViewById(R.id.imageView);
        TextView txtTitle = (TextView) v.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) v.findViewById(R.id.txtDescription);
        ImageView img_ok = (ImageView) v.findViewById(R.id.ok);
        TextView txt_acceleration = (TextView) v.findViewById(R.id.acceleration);
        TextView wallet = (TextView) v.findViewById(R.id.wallet_txt);
        TextView sonuc = (TextView) v.findViewById(R.id.wallet_txt2);

        img.setImageResource(product.getImageId());
        txtTitle.setText(product.getTitle());
        txtDescription.setText(product.getDescription());

        String bir = product.getDescription();

        if (product.getMny().equals("0")) {
            //wallet.setText("-");
            wallet.setVisibility(View.INVISIBLE);
            sonuc.setVisibility(View.INVISIBLE);
            //sonuc.setText("-");
        }
        else {
            wallet.setText(product.getMny());
            String iki = wallet.getText().toString();
            double a = Double.valueOf(bir) * Double.valueOf(iki);
            sonuc.setText(String.valueOf("(" + a + " TL)"));
        }

        String acceleration = product.getRange();
        if (acceleration.contains("+")) {
            img_ok.setImageResource(R.drawable.arrow_up);
            txt_acceleration.setText(product.getRange());
            txt_acceleration.setTextColor(Color.GREEN);
        }else {
            img_ok.setImageResource(R.drawable.arrow_down);
            txt_acceleration.setText(product.getRange());
            txt_acceleration.setTextColor(Color.RED);
        }

        return v;
    }
}
