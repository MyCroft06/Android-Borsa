package com.example.mcroft.myapplication.AddSecond;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mcroft.myapplication.First.MainActivity;
import com.example.mcroft.myapplication.R;

import java.util.List;

public class CashActivity extends AppCompatActivity implements ExDialog.ExDialogListener {

    DbLiteHalper db;

    private ViewStub stubGrid;
    private GridView gridView;
    private GridViewAdapter gridViewAdapter;
    private List<Add> walletList;

    private int ws;
    private String mny[];
    private String typ[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash);

        db = new DbLiteHalper(getApplicationContext());

        stubGrid = (ViewStub) findViewById(R.id.stub_grid);
        stubGrid.inflate();
        gridView = (GridView) findViewById(R.id.mygridview);
        ImageView isi = (ImageView)findViewById(R.id.add_img);
        ImageView geri_img = (ImageView)findViewById(R.id.geri_img);

        walletList = db.wallets();
        ws = walletList.size();
        if(ws == 0){
            Toast.makeText(getApplicationContext(), "Henüz Cüzdan Eklenmemiş.\nYukarıdaki + Butonundan Ekleyiniz", Toast.LENGTH_LONG).show();
        }

        setAdapters();
        gridView.setOnItemClickListener(onItemClick);

        geri_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CashActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
        isi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               openDialog();
            }
        });
    }

    public void openDialog() {
        ExDialog exDialog = new ExDialog();
        exDialog.show(getSupportFragmentManager(), "example dialog");
    }

    private void setAdapters() {
        gridViewAdapter = new GridViewAdapter(this, R.layout.cash_row, walletList);
        gridView.setAdapter(gridViewAdapter);
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Toast.makeText(getApplicationContext(), productList.get(position).getTitle() + " - " + productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void applyTexts(String money, String typ) {

        db.walletEkle(money,typ);

        walletList = db.wallets();
        ws = walletList.size();
        setAdapters();
    }

}
