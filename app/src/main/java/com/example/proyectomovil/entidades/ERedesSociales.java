package com.example.proyectomovil.entidades;

public class ERedesSociales {

    String idredsocial, idusuario, redsocial, vinculo;

    public ERedesSociales(String idredsocial, String idusuario, String redsocial, String vinculo) {
        this.idredsocial = idredsocial;
        this.idusuario = idusuario;
        this.redsocial = redsocial;
        this.vinculo = vinculo;
    }

    public ERedesSociales() {
    }

    public String getIdredsocial() {
        return idredsocial;
    }

    public void setIdredsocial(String idredsocial) {
        this.idredsocial = idredsocial;
    }

    public String getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(String idusuario) {
        this.idusuario = idusuario;
    }

    public String getRedsocial() {
        return redsocial;
    }

    public void setRedsocial(String redsocial) {
        this.redsocial = redsocial;
    }

    public String getVinculo() {
        return vinculo;
    }

    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
}
