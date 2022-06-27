package com.example.proyectomovil.entidades;

public class ECardsServicios {

    String idespecialidad, especialidad, nombres, email, nombreservicio , idusuario, idservicio, telefono, tarifa;

    public ECardsServicios() {
    }

    public ECardsServicios(String idespecialidad, String especialidad, String nombres, String email, String nombreservicio, String idusuario, String idservicio, String telefono, String tarifa) {
        this.idespecialidad = idespecialidad;
        this.especialidad = especialidad;
        this.nombres = nombres;
        this.email = email;
        this.nombreservicio = nombreservicio;
        this.idusuario = idusuario;
        this.idservicio = idservicio;
        this.telefono = telefono;
        this.tarifa = tarifa;
    }


    public String getIdespecialidad() {
        return idespecialidad;
    }

    public void setIdespecialidad(String idespecialidad) {
        this.idespecialidad = idespecialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreservicio() {
        return nombreservicio;
    }

    public void setNombreservicio(String nombreservicio) {
        this.nombreservicio = nombreservicio;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getIdservicio() {
        return idservicio;
    }

    public void setIdservicio(String idservicio) {
        this.idservicio = idservicio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getTarifa() {
        return tarifa;
    }

    public void setTarifa(String tarifa) {
        this.tarifa = tarifa;
    }
}
