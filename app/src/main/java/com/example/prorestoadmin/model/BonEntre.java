package com.example.prorestoadmin.model;

import java.util.ArrayList;
import java.util.Date;

public class BonEntre {

    private String NumeroBonEntrer;
    private Date DateBonEntrer;
    private String CodeDepot;
    private String LibelleDepot;
    private double TotalHT;
    private double TotalTVA;
    private double TotalTTC;
    private String NomUtilisateur;
    private Date DateCreation;
    private Date HeureCreation;
    private String Observation;
    private double NoteInterne;
    private double TotalVolume;
    private double TotalPoids;
    private double TotalColisage;
    private int NBImpression;
    private String Utilisateurintervenant;
    private String NumeroFicheLancement;
    private String NumeroEtat;
    private String LibeleEtat;
    private Date DateAnnulation;
    private String NomAnnulation;


    private ArrayList<LigneBonEntre>   listLigneBonEntree  ;

    public BonEntre(String numeroBonEntrer, Date dateBonEntrer, String codeDepot, String libelleDepot, String numeroEtat, String libeleEtat , String nomUtilisateur) {
        NumeroBonEntrer = numeroBonEntrer;
        DateBonEntrer = dateBonEntrer;
        CodeDepot = codeDepot;
        LibelleDepot = libelleDepot;
        NomUtilisateur = nomUtilisateur;
        NumeroEtat = numeroEtat;
        LibeleEtat = libeleEtat;
    }

    public BonEntre(String numeroBonEntrer, Date dateBonEntrer, String codeDepot, double totalHT, double totalTVA, double totalTTC, String nomUtilisateur, Date dateCreation, Date heureCreation, String observation, double noteInterne, double totalVolume, double totalPoids, double totalColisage, int NBImpression, String utilisateurintervenant, String numeroFicheLancement, String numeroEtat, Date dateAnnulation, String nomAnnulation) {
        NumeroBonEntrer = numeroBonEntrer;
        DateBonEntrer = dateBonEntrer;
        CodeDepot = codeDepot;
        TotalHT = totalHT;
        TotalTVA = totalTVA;
        TotalTTC = totalTTC;
        NomUtilisateur = nomUtilisateur;
        DateCreation = dateCreation;
        HeureCreation = heureCreation;
        Observation = observation;
        NoteInterne = noteInterne;
        TotalVolume = totalVolume;
        TotalPoids = totalPoids;
        TotalColisage = totalColisage;
        this.NBImpression = NBImpression;
        Utilisateurintervenant = utilisateurintervenant;
        NumeroFicheLancement = numeroFicheLancement;
        NumeroEtat = numeroEtat;
        DateAnnulation = dateAnnulation;
        NomAnnulation = nomAnnulation;
    }


    public String getNumeroBonEntrer() {
        return NumeroBonEntrer;
    }

    public void setNumeroBonEntrer(String numeroBonEntrer) {
        NumeroBonEntrer = numeroBonEntrer;
    }

    public Date getDateBonEntrer() {
        return DateBonEntrer;
    }

    public void setDateBonEntrer(Date dateBonEntrer) {
        DateBonEntrer = dateBonEntrer;
    }

    public String getCodeDepot() {
        return CodeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        CodeDepot = codeDepot;
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

    public double getNoteInterne() {
        return NoteInterne;
    }

    public void setNoteInterne(double noteInterne) {
        NoteInterne = noteInterne;
    }

    public double getTotalVolume() {
        return TotalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        TotalVolume = totalVolume;
    }

    public double getTotalPoids() {
        return TotalPoids;
    }

    public void setTotalPoids(double totalPoids) {
        TotalPoids = totalPoids;
    }

    public double getTotalColisage() {
        return TotalColisage;
    }

    public void setTotalColisage(double totalColisage) {
        TotalColisage = totalColisage;
    }

    public int getNBImpression() {
        return NBImpression;
    }

    public void setNBImpression(int NBImpression) {
        this.NBImpression = NBImpression;
    }

    public String getUtilisateurintervenant() {
        return Utilisateurintervenant;
    }

    public void setUtilisateurintervenant(String utilisateurintervenant) {
        Utilisateurintervenant = utilisateurintervenant;
    }

    public String getNumeroFicheLancement() {
        return NumeroFicheLancement;
    }

    public void setNumeroFicheLancement(String numeroFicheLancement) {
        NumeroFicheLancement = numeroFicheLancement;
    }

    public String getNumeroEtat() {
        return NumeroEtat;
    }

    public void setNumeroEtat(String numeroEtat) {
        NumeroEtat = numeroEtat;
    }

    public Date getDateAnnulation() {
        return DateAnnulation;
    }

    public void setDateAnnulation(Date dateAnnulation) {
        DateAnnulation = dateAnnulation;
    }

    public String getNomAnnulation() {
        return NomAnnulation;
    }

    public void setNomAnnulation(String nomAnnulation) {
        NomAnnulation = nomAnnulation;
    }

    public String getLibelleDepot() {
        return LibelleDepot;
    }

    public void setLibelleDepot(String libelleDepot) {
        LibelleDepot = libelleDepot;
    }

    public String getLibeleEtat() {
        return LibeleEtat;
    }

    public void setLibeleEtat(String libeleEtat) {
        LibeleEtat = libeleEtat;
    }

    public ArrayList<LigneBonEntre> getListLigneBonEntree() {
        return listLigneBonEntree;
    }

    public void setListLigneBonEntree(ArrayList<LigneBonEntre> listLigneBonEntree) {
        this.listLigneBonEntree = listLigneBonEntree;
    }

    @Override
    public String toString() {
        return "BonEntre{" +
                "NumeroBonEntrer='" + NumeroBonEntrer + '\'' +
                ", DateBonEntrer=" + DateBonEntrer +
                ", CodeDepot='" + CodeDepot + '\'' +
                ", TotalHT=" + TotalHT +
                ", TotalTVA=" + TotalTVA +
                ", TotalTTC=" + TotalTTC +
                ", NomUtilisateur='" + NomUtilisateur + '\'' +
                ", DateCreation=" + DateCreation +
                ", HeureCreation=" + HeureCreation +
                ", Observation='" + Observation + '\'' +
                ", NoteInterne=" + NoteInterne +
                ", TotalVolume=" + TotalVolume +
                ", TotalPoids=" + TotalPoids +
                ", TotalColisage=" + TotalColisage +
                ", NBImpression=" + NBImpression +
                ", Utilisateurintervenant='" + Utilisateurintervenant + '\'' +
                ", NumeroFicheLancement='" + NumeroFicheLancement + '\'' +
                ", NumeroEtat='" + NumeroEtat + '\'' +
                ", DateAnnulation=" + DateAnnulation +
                ", NomAnnulation='" + NomAnnulation + '\'' +
                '}';
    }
}
