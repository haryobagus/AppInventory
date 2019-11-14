package com.example.appinventory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditData extends Activity implements View.OnClickListener {

    private DBDataSource dataSource;
    private long id;
    private String harga;
    private String merk;
    private String nama;
    private EditText edNama;
    private EditText edHarga;
    private EditText edMerk;
    private TextView txId;
    private Button btnSave;
    private Button btnCancel;
    private Barang barang;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_data);

        edNama = (EditText) findViewById(R.id.editText_nama);
        edHarga = (EditText) findViewById(R.id.editText_harga);
        edMerk = (EditText) findViewById(R.id.editText_merk);
        txId = (TextView) findViewById(R.id.text_id_barang);

        dataSource = new DBDataSource(this);
        dataSource.open();

        Bundle bun = this.getIntent().getExtras();
        id = bun.getLong("id");
        harga = bun.getString("harga");
        merk = bun.getString("merk");
        nama = bun.getString("nama");

        txId.append(String.valueOf(id));
        edNama.setText(nama);
        edHarga.setText(harga);
        edMerk.setText(merk);

        btnSave = (Button) findViewById(R.id.button_save_update);
        btnSave.setOnClickListener(this);
        btnCancel = (Button) findViewById(R.id.button_cancel_update);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch(v.getId())
        {
            case R.id.button_save_update :
                barang = new Barang();
                barang.setHarga_barang(edHarga.getText().toString());
                barang.setNama_barang(edNama.getText().toString());
                barang.setMerk_barang(edMerk.getText().toString());
                barang.setId(id);
                dataSource.updateBarang(barang);
                Intent i = new Intent(this, ViewData.class);
                startActivity(i);
                EditData.this.finish();
                dataSource.close();
                break;

            case R.id.button_cancel_update :
                finish();
                dataSource.close();
                break;
        }
    }
}
