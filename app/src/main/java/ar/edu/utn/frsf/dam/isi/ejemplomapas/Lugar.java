package ar.edu.utn.frsf.dam.isi.ejemplomapas;

import com.google.android.gms.maps.model.LatLng;

import java.util.Random;

public class Lugar {
    private String lugar;
    private String descripcion;
    private LatLng coordenadas;
    private Integer tipoMarcador;

    public Lugar(String lugar, String descripcion, LatLng coordenadas) {
        this.lugar = lugar;
        this.descripcion = descripcion;
        this.coordenadas = coordenadas;
        Random r = new Random();
        this.tipoMarcador = r.nextInt(5);
    }

    public Lugar() {
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public LatLng getCoordenadas() {
        return coordenadas;
    }

    public void setCoordenadas(LatLng coordenadas) {
        this.coordenadas = coordenadas;
    }

    public Integer getTipoMarcador() {
        return tipoMarcador;
    }

    public void setTipoMarcador(Integer tipoMarcador) {
        this.tipoMarcador = tipoMarcador;
    }
}
