package com.example.appinventory;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appinventory.models.Barang;

public class CreateData extends Activity implements View.OnClickListener {

    // inisialisasi elemen-elemen pada layout
    private Button buttonSubmit;
    private EditText edNama;
    private EditText edMerk;
    private EditText edHarga;

    // inisialisasi kontroller/Data Source
    private DBDataSource dataSource;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_data);

        buttonSubmit = findViewById(R.id.button_submit);
        buttonSubmit.setOnClickListener(this);
        edNama = findViewById(R.id.nama_barang);
        edHarga = findViewById(R.id.harga_barang);
        edMerk = findViewById(R.id.merk_barang);

        // instanstiasi kelas DBDataSource
        dataSource = new DBDataSource(this);

        // membuat sambungan baru ke database
        dataSource.open();
    }

    @Override
    public void onClick(View v) {
        // Inisialisasi data barang
        String nama = null;
        String merk = null;
        String harga = null;
        @SuppressWarnings("unused")

        // inisialisasi barang baru (masih kosong)
                Barang barang = null;
        if (edNama.getText() != null && edMerk.getText() != null && edHarga.getText() != null) {
            // Jika field nama, merk, dan harga tidak kosong maka masukkan ke dalam data barang
            nama = edNama.getText().toString();
            merk = edMerk.getText().toString();
            harga = edHarga.getText().toString();
        }

        switch (v.getId()) {
            case R.id.button_submit:
                // insert data barang baru
                barang = dataSource.createBarang(nama, merk, harga);

                // konfirmasi kesuksesan
                Toast.makeText(this, "Masuk Barang\n " +
                        "\nNama : " + barang.getNama_barang() +
                        "\nMerk : " + barang.getMerk_barang() +
                        "\nHarga : " + barang.getHarga_barang(), Toast.LENGTH_LONG
                ).show();
                break;
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
