package com.example.prorestoadmin.model.draft;

import java.util.ArrayList;
import java.util.Date;

public class DraftReglement {

    private String NumeroDraftClient;
    private Date   DateDraftClient;
    private String CodeClient;
    private String RaisonSociale;
    private String CodeRepresentant;
    private String NomRepresentant;
    private String Observation;
    private String NomUtilisateur;
    private Date   DateCreation;
    private Date   HeureCreation;
    private double Montant;
    private  int cloture ;
    private  int annuler ;



    private double MontantLittre;


    private String  etat  ;

    private ArrayList<DetailDraftReglementClient>  detailDraftReg ;

    public DraftReglement
            (String numeroDraftClient, Date heureCreation, String codeClient, String raisonSociale, double montant, String etat) {
        NumeroDraftClient = numeroDraftClient;
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        HeureCreation = heureCreation;
        Montant = montant;
        this.etat = etat;
    }



    public DraftReglement(String numeroDraftClient, Date dateDraftClient, String codeClient, String raisonSociale, String codeRepresentant, String nomRepresentant, String observation, String nomUtilisateur, Date dateCreation, Date heureCreation, double montant, int cloture, int annuler) {
        NumeroDraftClient = numeroDraftClient;
        DateDraftClient = dateDraftClient;
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        CodeRepresentant = codeRepresentant;
        NomRepresentant = nomRepresentant;
        Observation = observation;
        NomUtilisateur = nomUtilisateur;
        DateCreation = dateCreation;
        HeureCreation = heureCreation;
        Montant = montant;
        this.cloture = cloture;
        this.annuler = annuler;
    }

    public DraftReglement(String numeroDraftClient, Date dateDraftClient, String codeClient, String raisonSociale, String codeRepresentant, String nomRepresentant, String observation, String nomUtilisateur, Date dateCreation, Date heureCreation, double montant, double MontantLittre) {
        NumeroDraftClient = numeroDraftClient;
        DateDraftClient = dateDraftClient;
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        CodeRepresentant = codeRepresentant;
        NomRepresentant = nomRepresentant;
        Observation = observation;
        NomUtilisateur = nomUtilisateur;
        DateCreation = dateCreation;
        HeureCreation = heureCreation;
        Montant = montant;
        this.MontantLittre = MontantLittre ;
    }

    public String getNumeroDraftClient() {
        return NumeroDraftClient;
    }

    public void setNumeroDraftClient(String numeroDraftClient) {
        NumeroDraftClient = numeroDraftClient;
    }

    public Date getDateDraftClient() {
        return DateDraftClient;
    }

    public void setDateDraftClient(Date dateDraftClient) {
        DateDraftClient = dateDraftClient;
    }

    public String getCodeClient() {
        return CodeClient;
    }

    public void setCodeClient(String codeClient) {
        CodeClient = codeClient;
    }

    public String getRaisonSociale() {
        return RaisonSociale;
    }

    public void setRaisonSociale(String raisonSociale) {
        RaisonSociale = raisonSociale;
    }

    public String getCodeRepresentant() {
        return CodeRepresentant;
    }

    public void setCodeRepresentant(String codeRepresentant) {
        CodeRepresentant = codeRepresentant;
    }

    public String getNomRepresentant() {
        return NomRepresentant;
    }

    public void setNomRepresentant(String nomRepresentant) {
        NomRepresentant = nomRepresentant;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
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

    public double getMontant() {
        return Montant;
    }

    public void setMontant(double montant) {
        Montant = montant;
    }

    public double getMontantLittre() {
        return MontantLittre;
    }

    public void setMontantLittre(double montantLittre) {
        MontantLittre = montantLittre;
    }


    public int getCloture() {
        return cloture;
    }

    public void setCloture(int cloture) {
        this.cloture = cloture;
    }

    public int getAnnuler() {
        return annuler;
    }

    public void setAnnuler(int annuler) {
        this.annuler = annuler;
    }


    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public ArrayList<DetailDraftReglementClient> getDetailDraftReg() {
        return detailDraftReg;
    }

    public void setDetailDraftReg(ArrayList<DetailDraftReglementClient> detailDraftReg) {
        this.detailDraftReg = detailDraftReg;
    }

    @Override
    public String toString() {

        return "DraftReglement{" +
                "NumeroDraftClient='" + NumeroDraftClient + '\'' +
                ", DateDraftClient=" + DateDraftClient +
                ", CodeClient='" + CodeClient + '\'' +
                ", RaisonSociale='" + RaisonSociale + '\'' +
                ", CodeRepresentant='" + CodeRepresentant + '\'' +
                ", NomRepresentant='" + NomRepresentant + '\'' +
                ", Observation='" + Observation + '\'' +
                ", NomUtilisateur='" + NomUtilisateur + '\'' +
                ", DateCreation=" + DateCreation +
                ", HeureCreation=" + HeureCreation +
                ", Montant=" + Montant +
                '}';
    }
}
