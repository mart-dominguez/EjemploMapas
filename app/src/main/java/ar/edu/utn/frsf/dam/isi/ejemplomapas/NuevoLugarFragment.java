package ar.edu.utn.frsf.dam.isi.ejemplomapas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.maps.model.LatLng;

public class NuevoLugarFragment extends Fragment {

    public interface OnNuevoLugarListener {
        public void obtenerCoordenadas();
        public void guardarLugar(String nombre, LatLng coordenadas);
    }

    private Button btnGuardar;
    private Button btnCoordenadas;
    private EditText edtTit;
    private EditText edtCoord;
    private LatLng coordenadas;
    private OnNuevoLugarListener listener;

    public NuevoLugarFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.nuevo_lugar, container, false);
        edtTit = (EditText) v.findViewById(R.id.nombre_lugar);
        edtCoord = (EditText) v.findViewById(R.id.coordenadas);
        edtCoord.setEnabled(false);
        btnGuardar = (Button) v.findViewById(R.id.btnGuardar);
        btnCoordenadas = (Button) v.findViewById(R.id.btnCoordenadas);
        btnCoordenadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.obtenerCoordenadas();
            }
        });
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.guardarLugar(edtTit.getText().toString(),coordenadas);
            }
        });
        return v;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
        edtCoord.setText(coordenadas.latitude+ ","+coordenadas.longitude);
    }
}
