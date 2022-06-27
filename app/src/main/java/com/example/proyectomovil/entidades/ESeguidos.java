package com.example.proyectomovil.entidades;

public class ESeguidos {

    String idfollowing, nombres, apellidos, fechaseguido, estado;

    public ESeguidos() {
    }

    public ESeguidos(String idfollowing, String nombres, String apellidos, String fechaseguido, String estado) {
        this.idfollowing = idfollowing;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.fechaseguido = fechaseguido;
        this.estado = estado;
    }

    public String getIdfollowing() {
        return idfollowing;
    }

    public void setIdfollowing(String idfollowing) {
        this.idfollowing = idfollowing;
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

    public String getEstado() {
        return estado;
    }
    public void setEstado(String estado) {
        this.estado = estado;
    }
}
