package com.example.prorestoadmin.model;

import java.util.ArrayList;
import java.util.Date;

public class BonTransfert {


    private String NumeroBonTransfert;
    private Date DateBonTransfert;
    private String CodeDepotSource;
    private String DepotSource;
    private String CodeDepotDestination;
    private String DepotDestination;
    private double TotalHT;
    private double TotalTVA;
    private double TotalTTC;
    private double TotalRemise;

    private double TotalNetHT;

    private String NomUtilisateur;
    private Date DateCreation;
    private Date HeureCreation;
    private String Observation;
    private String NumeroEtat;
    private String AffecteA ;
    private String Verificateur ;
    private String LibelleEtat;



    private ArrayList<LigneBonTransfert>  listLigneBonTransfert ;


    public BonTransfert(String numeroBonTransfert, Date dateBonTransfert, String codeDepotSource, String codeDepotDestination, double totalHT, double totalTVA, double totalTTC, double totalRemise, double totalNetHT,  String nomUtilisateur, Date dateCreation, Date heureCreation, String observation, String numeroEtat ,String AffecteA ,String Verificateur ) {
        NumeroBonTransfert = numeroBonTransfert;
        DateBonTransfert = dateBonTransfert;
        CodeDepotSource = codeDepotSource;
        CodeDepotDestination = codeDepotDestination;
        TotalHT = totalHT;
        TotalTVA = totalTVA;
        TotalTTC = totalTTC;
        TotalRemise = totalRemise;

        TotalNetHT = totalNetHT;

        NomUtilisateur = nomUtilisateur;
        DateCreation = dateCreation;
        HeureCreation = heureCreation;
        Observation = observation;
        NumeroEtat = numeroEtat;
        this.AffecteA = AffecteA ;
        this.Verificateur =Verificateur ;
    }


    public BonTransfert(String numeroBonTransfert, Date dateBonTransfert, String codeDepotSource, String depotSource, String codeDepotDestination, String depotDestination, String numeroEtat, String libelleEtat) {
        NumeroBonTransfert = numeroBonTransfert;
        DateBonTransfert = dateBonTransfert;
        CodeDepotSource = codeDepotSource;
        DepotSource = depotSource;
        CodeDepotDestination = codeDepotDestination;
        DepotDestination = depotDestination;
        NumeroEtat = numeroEtat;
        LibelleEtat = libelleEtat;
    }

    public String getNumeroBonTransfert() {
        return NumeroBonTransfert;
    }

    public void setNumeroBonTransfert(String numeroBonTransfert) {
        NumeroBonTransfert = numeroBonTransfert;
    }

    public Date getDateBonTransfert() {
        return DateBonTransfert;
    }

    public void setDateBonTransfert(Date dateBonTransfert) {
        DateBonTransfert = dateBonTransfert;
    }

    public String getCodeDepotSource() {
        return CodeDepotSource;
    }

    public void setCodeDepotSource(String codeDepotSource) {
        CodeDepotSource = codeDepotSource;
    }

    public String getCodeDepotDestination() {
        return CodeDepotDestination;
    }

    public void setCodeDepotDestination(String codeDepotDestination) {
        CodeDepotDestination = codeDepotDestination;
    }

    public double getTotalHT() {
        return TotalHT;
    }

    public void setTotalHT(double totalHT) {
        TotalHT = totalHT;
    }

    public double getTotalTVA() {
        return TotalTVA;
    }

    public void setTotalTVA(double totalTVA) {
        TotalTVA = totalTVA;
    }

    public double getTotalTTC() {
        return TotalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        TotalTTC = totalTTC;
    }

    public double getTotalRemise() {
        return TotalRemise;
    }

    public void setTotalRemise(double totalRemise) {
        TotalRemise = totalRemise;
    }



    public double getTotalNetHT() {
        return TotalNetHT;
    }

    public void setTotalNetHT(double totalNetHT) {
        TotalNetHT = totalNetHT;
    }



    public String getNomUtilisateur() {
        return NomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        NomUtilisateur = nomUtilisateur;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public Date getHeureCreation() {
        return HeureCreation;
    }

    public void setHeureCreation(Date heureCreation) {
        HeureCreation = heureCreation;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public String getNumeroEtat() {
        return NumeroEtat;
    }

    public void setNumeroEtat(String numeroEtat) {
        NumeroEtat = numeroEtat;
    }

    public String getAffecteA() {
        return AffecteA;
    }

    public void setAffecteA(String affecteA) {
        AffecteA = affecteA;
    }

    public String getDepotSource() {
        return DepotSource;
    }

    public void setDepotSource(String depotSource) {
        DepotSource = depotSource;
    }

    public String getDepotDestination() {
        return DepotDestination;
    }

    public void setDepotDestination(String depotDestination) {
        DepotDestination = depotDestination;
    }

    public String getLibelleEtat() {
        return LibelleEtat;
    }

    public void setLibelleEtat(String libelleEtat) {
        LibelleEtat = libelleEtat;
    }

    public ArrayList<LigneBonTransfert> getListLigneBonTransfert() {
        return listLigneBonTransfert;
    }

    public void setListLigneBonTransfert(ArrayList<LigneBonTransfert> listLigneBonTransfert) {
        this.listLigneBonTransfert = listLigneBonTransfert;
    }


    public String getVerificateur() {
        return Verificateur;
    }

    public void setVerificateur(String verificateur) {
        Verificateur = verificateur;
    }

    @Override
    public String toString() {
        return "BonTransfert{" +
                "NumeroBonTransfert='" + NumeroBonTransfert + '\'' +
                ", DateBonTransfert=" + DateBonTransfert +
                ", CodeDepotSource='" + CodeDepotSource + '\'' +
                ", CodeDepotDestination='" + CodeDepotDestination + '\'' +
                ", TotalHT=" + TotalHT +
                ", TotalTVA=" + TotalTVA +
                ", TotalTTC=" + TotalTTC +
                ", TotalRemise=" + TotalRemise +

                ", TotalNetHT=" + TotalNetHT +

                ", NomUtilisateur='" + NomUtilisateur + '\'' +
                ", DateCreation=" + DateCreation +
                ", HeureCreation=" + HeureCreation +
                ", Observation='" + Observation + '\'' +
                ", NumeroEtat='" + NumeroEtat + '\'' +
                '}';
    }
}
