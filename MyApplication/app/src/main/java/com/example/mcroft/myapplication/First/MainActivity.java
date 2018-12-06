package com.example.mcroft.myapplication.First;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mcroft.myapplication.AddSecond.Add;
import com.example.mcroft.myapplication.AddSecond.CashActivity;
import com.example.mcroft.myapplication.AddSecond.DbLiteHalper;
import com.example.mcroft.myapplication.AddSecond.ExDialog;
import com.example.mcroft.myapplication.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements ExDialog.ExDialogListener {

    DbLiteHalper db;
    private List<Add> walletList;

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<Product> productList;

    private ProgressDialog progressDialog;
    private static String authorUrl = "http://altin.tlkur.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.stub_grid);
        listView.setOnItemClickListener(onItemClick);
        ImageView reset_img = (ImageView)findViewById(R.id.reset_img);
        TextView wallet_txt = (TextView)findViewById(R.id.hh);

        new ata().execute();

        Date now = new Date();
        DateFormat df = new SimpleDateFormat("E");
        Log.e("tarih",df.format(now));
        if (!df.format(now).equals("Sat")){
            if (!df.format(now).equals("Sun")){
            final Handler handler = new Handler();
            Timer timer;
            TimerTask timerTask = new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            new ata().execute();
                        }
                    });
                }
            };
            timer = new Timer();
            timer.schedule(timerTask,10000,30000);
        } }

        reset_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Her 10sn de bir yenileniyor", Toast.LENGTH_SHORT).show();
            }
        });
        ImageView img_wallet =(ImageView) findViewById(R.id.img_wallet) ;
        img_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CashActivity.class);
                startActivity(i);
            }
        });
        wallet_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,CashActivity.class);
                startActivity(i);
            }
        });
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            //Do any thing when user click to item
            Toast.makeText(getApplicationContext(), productList.get(position).getTitle() + " - " + productList.get(position).getDescription(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public void applyTexts(String money, String typr) {
    }

    private class ata extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setTitle("Borsa");
            progressDialog.setMessage("Paralar Yükleniyor...");
            progressDialog.setIndeterminate(false);
            progressDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {

            db = new DbLiteHalper(getApplicationContext());
            walletList = db.wallets();

            try{
                Document doc  = Jsoup.connect(authorUrl).get();
                Elements fiyat = doc.select("div[class=middle gray_neutral]");

                //Elements aa = fiyat.select("div[class=head] span[class=title]");
                Elements ss = fiyat.select("span[class=rate]");
                Elements ff = fiyat.select("span");

                String[] asd = ff.text().split(" ");
                //Log.e("",ff.text());

                int sayi = walletList.size();
                String typ;
                String bitcoin_mny = "0";
                String dolar_mny = "0";
                String sterlin_mny = "0";
                String euro_mny = "0";
                for (int i=0; i<sayi; i++){
                    typ = walletList.get(i).getTyp();
                    switch (typ) {
                        case "Dolar" :
                            dolar_mny = walletList.get(i).getMoney();
                            break;
                        case "Euro" :
                            euro_mny = walletList.get(i).getMoney();
                            break;
                        case "Sterlin" :
                            sterlin_mny = walletList.get(i).getMoney();
                            break;
                        case "Bitcoin" :
                            bitcoin_mny = walletList.get(i).getMoney();
                            break;
                    }
                }

                productList = new ArrayList<>();
                productList.add(new Product(R.drawable.bitcoins, "Bitcoin", ss.get(2).text(), asd[5], bitcoin_mny));
                productList.add(new Product(R.drawable.dolars, "Dolar", ss.get(0).text(), asd[1], dolar_mny));
                productList.add(new Product(R.drawable.euros, "Euro", ss.get(1).text(), asd[3], euro_mny));
                productList.add(new Product(R.drawable.sterlins, "Sterlin", ss.get(4).text(), asd[9], sterlin_mny));

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            listViewAdapter = new ListViewAdapter(MainActivity.this, R.layout.main_row, productList);
            listView.setAdapter(listViewAdapter);
            progressDialog.dismiss();
        }
    }

}
