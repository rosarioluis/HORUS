package com.app.mobi.horus;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends AppCompatActivity {

    ImageButton floatButton;
    //Array que contiene los dispositivos dados de alta
    String[] dispositivosArray = {"Dipositivo 1", "Dispositivo de prueba","mapa"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permission is not granted, requesting", Toast.LENGTH_LONG).show();
            //Log.d("PLAYGROUND", "Permission is not granted, requesting");
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, 123);
        } else {
            Toast.makeText(this, "Permission is not granted", Toast.LENGTH_LONG).show();
        }
        //Muestra el botón flotante para agreagar un nuevo dispositivo
        floatButton =(ImageButton) findViewById(R.id.imageButton);
        floatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), DispositivoActivity.class);
                startActivityForResult(myIntent, 0);
                //  Toast.makeText(getApplicationContext(), "Button is clicked", Toast.LENGTH_LONG).show();
            }
        });

        //Muestra en la listview los dispositivos
        //SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, new HorusDB(this).obtenerDispositivos(),new String[]{"ID","NOMBRE"});
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, dispositivosArray);
        final ListView listView = (ListView) findViewById(R.id.listDispositivos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Muestra mensaje con la posicion del elemento seleccionado
                Toast.makeText(getBaseContext(), parent.getItemIdAtPosition(position) + " fue seleccionado", Toast.LENGTH_LONG).show();
                //Obtiene el nombre del elemento seleccionado
                String data = (String) listView.getAdapter().getItem(position);
                //Se define el intent, indica a la clase a la que se pasara la informacion
                Intent intent = new Intent(MainActivity.this, MapAct.class);
                intent.putExtra("idDispositivo", data);
                startActivity(intent);

            }

        });

    }
}
