package com.example.appinventory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateData extends Activity implements View.OnClickListener {

    private Button buttonSubmit;
    private EditText edNama;
    private EditText edMerk;
    private EditText edHarga;

    private DBDataSource dataSource;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);
        buttonSubmit = (Button) findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = (EditText) findViewById(R.id.nama_barang);
        edHarga = (EditText) findViewById(R.id.harga_barang);
        edMerk = (EditText) findViewById(R.id.merk_barang);

        dataSource = new DBDataSource(this);

        dataSource.open();
    }

    @Override
    public void onClick(View v) {

        String nama = null;
        String merk = null;
        String harga = null;
        @SuppressWarnings("unused")

                Barang barang = null;
        if(edNama.getText()!=null && edMerk.getText()!=null &&
                edHarga.getText()!=null)
        {
            nama = edNama.getText().toString();
            merk = edMerk.getText().toString();
            harga = edHarga.getText().toString();
        }
        switch(v.getId())
        {
            case R.id.button_submit:

                barang = dataSource.createBarang(nama, merk, harga);

                Toast.makeText(this, "masuk Barang\n" +
                                "nama" + barang.getNama_barang() +
                                "merk" + barang.getMerk_barang() +
                                "harga" + barang.getHarga_barang(),
                        Toast.LENGTH_LONG).show();
                break;
        }
    }
}
