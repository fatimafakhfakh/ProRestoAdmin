package com.example.prorestoadmin.model;



import java.util.ArrayList;
import java.util.Date;

public class GratuiteClient {



    private   String  CodeClient   ;
    private   String  RaisonSocial   ;
    private Date DateDebut   ;
    private Date DateFin   ;

    private  boolean  gratuit_atteint ;
    private  boolean  gratuit_proche_atteint ;

    private   ArrayList<Convention>  listConvention  ;

    public GratuiteClient(String codeClient, String raisonSocial, Date dateDebut, Date dateFin) {
        CodeClient = codeClient;
        RaisonSocial = raisonSocial;
        DateDebut = dateDebut;
        DateFin = dateFin;
    }

    public String getCodeClient() {
        return CodeClient;
    }

    public void setCodeClient(String codeClient) {
        CodeClient = codeClient;
    }

    public String getRaisonSocial() {
        return RaisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        RaisonSocial = raisonSocial;
    }

    public Date getDateDebut() {
        return DateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        DateDebut = dateDebut;
    }

    public Date getDateFin() {
        return DateFin;
    }

    public void setDateFin(Date dateFin) {
        DateFin = dateFin;
    }

    public ArrayList<Convention> getListConvention() {
        return listConvention;
    }

    public void setListConvention(ArrayList<Convention> listConvention) {
        this.listConvention = listConvention;
    }

    public boolean isGratuit_atteint() {
        return gratuit_atteint;
    }

    public void setGratuit_atteint(boolean gratuit_atteint) {
        this.gratuit_atteint = gratuit_atteint;
    }

    public boolean isGratuit_proche_atteint() {
        return gratuit_proche_atteint;
    }

    public void setGratuit_proche_atteint(boolean gratuit_proche_atteint) {
        this.gratuit_proche_atteint = gratuit_proche_atteint;
    }
}
