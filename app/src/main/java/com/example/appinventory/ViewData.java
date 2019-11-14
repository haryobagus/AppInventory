package com.example.appinventory;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ViewData extends ListActivity implements AdapterView.OnItemLongClickListener {

    private DBDataSource dataSource;

    private ArrayList<Barang> values;
    private Button editButton;
    private Button delButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewdata);
        dataSource = new DBDataSource(this);

        dataSource.open();

        values = dataSource.getAllBarang();

        ArrayAdapter<Barang> adapter = new ArrayAdapter<Barang>(this, android.R.layout.simple_list_item_1, values);

        setListAdapter(adapter);

        ListView lv = findViewById(android.R.id.list);
        lv.setOnItemLongClickListener(this);
    }

    @Override
    public boolean onItemLongClick(final AdapterView<?> adapter, View v, int pos, final long id) {

        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_view);
        dialog.setTitle("Pilih Aksi");
        dialog.show();
        final Barang b = (Barang) getListAdapter().getItem(pos);
        editButton = dialog.findViewById(R.id.button_edit_data);
        delButton = dialog.findViewById(R.id.button_delete_data);

        editButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // TODO Auto-generated method stub
                        switchToEdit(b.getId());
                        dialog.dismiss();
                    }
                }
        );

        delButton.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View v) {
                        // Delete barang
                        dataSource.deleteBarang(b.getId());
                        dialog.dismiss();
                        finish();
                        startActivity(getIntent());
                    }
                }
        );
        return true;
    }
    public void switchToEdit(long id)
    {
        Barang b = dataSource.getBarang(id);
        Intent i = new Intent(this, EditData.class);
        Bundle bun = new Bundle();
        bun.putLong("id", b.getId());
        bun.putString("nama", b.getNama_barang());
        bun.putString("merk", b.getMerk_barang());
        bun.putString("harga", b.getHarga_barang());
        i.putExtras(bun);
        finale();
        startActivity(i);
    }
    public void finale()
    {
        ViewData.this.finish();
        dataSource.close();
    }
    @Override
    protected void onResume() {
        dataSource.open();
        super.onResume();
    }
    @Override
    protected void onPause() {
        dataSource.close();
        super.onPause();
    }
}
