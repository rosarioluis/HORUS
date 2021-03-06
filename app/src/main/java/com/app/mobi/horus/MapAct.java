package com.app.mobi.horus;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapAct extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    Mensaje sms = new Mensaje(this);
    //Declaraciòn de variables
    String contraseña ="123456";
    String mensaje;
    String noTelefono = "3121105454";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        //Inflate the menu; this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return  true;
    }
    //Depende la opciòn seleccionada en el menu, se ejecturà una determinada actividad
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.admin) {
            Intent intent1 = new Intent(this,AdministradorActivity.class);
            this.startActivity(intent1);
            return true;
        }
        if (id == R.id.pass) {
            Intent intent2 = new Intent(this,ContrasenaActivity.class);
            this.startActivity(intent2);
            return true;
        }
        if (id == R.id.armado) {
            mensaje = "arm"+contraseña;
            sms.enviarMensaje(noTelefono, mensaje);
            Toast.makeText(this, "Armar dispositivo", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.alarma) {
            Intent intent4 = new Intent(this,PopAlarma.class);
            this.startActivity(intent4);
            return true;
        }
        if (id == R.id.reiniciar) {
            Toast.makeText(this, "Reiniciar dispositivo", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
