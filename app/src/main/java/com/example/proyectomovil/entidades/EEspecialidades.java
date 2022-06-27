package com.example.proyectomovil.entidades;

public class EEspecialidades {
    String idespecialidad, idservicio, idusuario, descripcion, tarifa;

    public EEspecialidades() {
    }

    public EEspecialidades(String idespecialidad, String idservicio, String idusuario, String descripcion, String tarifa) {
        this.idespecialidad = idespecialidad;
        this.idservicio = idservicio;
        this.idusuario = idusuario;
        this.descripcion = descripcion;
        this.tarifa = tarifa;
    }


    public String getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(String idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public String getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(String idservicio) {
        this.idservicio = idservicio;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }
}
