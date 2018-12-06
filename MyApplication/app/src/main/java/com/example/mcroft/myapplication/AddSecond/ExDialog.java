package com.example.mcroft.myapplication.AddSecond;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.mcroft.myapplication.R;

public class ExDialog extends AppCompatDialogFragment {

    private EditText edt_money;
    private Button btn_add;
    private Spinner spinner;

    private ExDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder =new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.cash_dialog,null);
         builder.setView(view);
               /*.setNegativeButton(("cancel"), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });*/

        edt_money = view.findViewById(R.id.editText);
        btn_add = view.findViewById(R.id.button);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String mny = edt_money.getText().toString();
                String typ = spinner.getSelectedItem().toString();
                if (mny.isEmpty())
                {
                    //Toast.makeText(ExDialog.this, "para miktarı boş bırakılamaz !..." ,Toast.LENGTH_LONG).show();
                }else {

                    listener.applyTexts(mny,typ);
                    dismiss();
                }
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExDialogListener)context;
        }catch (ClassCastException e){
            throw  new ClassCastException(context.toString()+"must implement ExDialogListener");
        }
    }

    public interface ExDialogListener{
        void applyTexts(String money, String typr);
    }
}
