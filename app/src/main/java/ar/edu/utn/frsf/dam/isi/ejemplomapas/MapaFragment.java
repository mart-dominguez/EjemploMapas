package ar.edu.utn.frsf.dam.isi.ejemplomapas;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapaFragment extends SupportMapFragment implements OnMapReadyCallback {

    public interface OnMapaListener {
        public void coordenadasSeleccionadas(LatLng c);
    }

    private GoogleMap mapa;
    private boolean FLAG_NUEVO_LUGAR;
    private OnMapaListener listener;
    private List<Lugar> lugaresMostrar;

    private static final BitmapDescriptor[] colores =
    {
        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE),
        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE),
        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE),
        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED),
        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN),
        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ROSE)
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        FLAG_NUEVO_LUGAR = false;
        getMapAsync(this);
        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mapa = map;
        // Posicionar el mapa en una localización y con un nivel de zoom
        LatLng latLng = new LatLng(36.679582, -5.444791);
        float zoom = 16;
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        for(Lugar l : lugaresMostrar){
            this.agregarMarcadorColor(l);
        }
    }

    public void agregarMarcadorColor(Lugar l){
        if(mapa!=null) {
            mapa.addMarker(
                    new MarkerOptions()
                            .position(l.getCoordenadas())
                            .title(l.getLugar())
                            .icon(colores[l.getTipoMarcador()])
            );
        }
    }

    public void agregarMarcadorImagen(Lugar l){
        if(mapa!=null) {
            mapa.addMarker(
                    new MarkerOptions()
                            .position(l.getCoordenadas())
                            .title(l.getLugar())
                            .icon(colores[l.getTipoMarcador()])
            );
        }
    }

    private GoogleMap.OnMapLongClickListener listenerClickLargo = new GoogleMap.OnMapLongClickListener() {
        @Override
        public void onMapLongClick(LatLng latLng) {
                  if(FLAG_NUEVO_LUGAR){
                      listener.coordenadasSeleccionadas(latLng);
                      FLAG_NUEVO_LUGAR = false;
                  }
        }
    };

    public void configurarNuevoLugar(){
        FLAG_NUEVO_LUGAR = true;
    }

    public OnMapaListener getListener() {
        return listener;
    }

    public void setListener(OnMapaListener listener) {
        this.listener = listener;
    }

    public List<Lugar> getLugaresMostrar() {
        return lugaresMostrar;
    }

    public void setLugaresMostrar(List<Lugar> lugaresMostrar) {
        this.lugaresMostrar = lugaresMostrar;
    }
}
