package ar.edu.utn.frsf.dam.isi.ejemplomapas;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MapaFragment.OnMapaListener, NuevoLugarFragment.OnNuevoLugarListener{

    private NuevoLugarFragment nuevoLugar;
    private MapaFragment mapaFragment;
    private List<Lugar> lugares;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.barraHerramientas);
        setSupportActionBar(myToolbar);
        lugares = new ArrayList<>();
        nuevoLugar= new NuevoLugarFragment();
        mapaFragment= new MapaFragment();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mi_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        FragmentManager frgMngr =  getSupportFragmentManager();
        switch (item.getItemId()) {
            case R.id.verMapa:
                frgMngr.beginTransaction()
                        .replace(R.id.contenedorFragmento,mapaFragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            case R.id.lugar:
                frgMngr.beginTransaction()
                        .replace(R.id.contenedorFragmento,nuevoLugar)
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(this,"ESTA OPCION NO TIENE ACCION ASOCIADA",Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void coordenadasSeleccionadas(LatLng c) {
        nuevoLugar.setCoordenadas(c);
        FragmentManager frgMngr =  getSupportFragmentManager();
        frgMngr.beginTransaction()
                .replace(R.id.contenedorFragmento,nuevoLugar)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void obtenerCoordenadas() {
        mapaFragment.configurarNuevoLugar();
        FragmentManager frgMngr =  getSupportFragmentManager();
        frgMngr.beginTransaction()
                .replace(R.id.contenedorFragmento,mapaFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void guardarLugar(String nombre, LatLng coordenadas) {
        Lugar aux = new Lugar(nombre,"DET --- "+nombre,coordenadas);
        lugares.add(aux);
        mapaFragment.agregarMarcadorColor(aux);
        FragmentManager frgMngr =  getSupportFragmentManager();
        frgMngr.beginTransaction()
                .replace(R.id.contenedorFragmento,mapaFragment)
                .addToBackStack(null)
                .commit();
    }
}
