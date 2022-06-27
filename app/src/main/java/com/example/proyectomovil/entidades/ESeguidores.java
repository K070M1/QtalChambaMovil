package com.example.proyectomovil.entidades;

public class ESeguidores {

    String idfollower, nombres, apellidos, fechaseguido;


    public ESeguidores() {
    }

    public ESeguidores(String idfollower, String nombres, String apellidos, String fechaseguido) {
        this.idfollower = idfollower;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaseguido = fechaseguido;
    }

    public String getIdfollower() {
        return idfollower;
    }

    public void setIdfollower(String idfollower) {
        this.idfollower = idfollower;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getFechaseguido() {
        return fechaseguido;
    }

    public void setFechaseguido(String fechaseguido) {
        this.fechaseguido = fechaseguido;
    }
}
