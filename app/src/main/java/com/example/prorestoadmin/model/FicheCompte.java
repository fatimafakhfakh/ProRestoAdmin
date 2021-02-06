package com.example.prorestoadmin.model;

import java.util.Date;

public class FicheCompte {

    private   String  CodeCompte  ;
    private Date DateOperation  ;
    private   String  NumeroPiece  ;
    private   String  Libelle  ;
    private   String  Reference  ;
    private Date Echeance  ;
    private  double Debit  ;
    private  double Credit    ;
    private Date HeureCreation  ;
    private   String  CodeModeReglement  ;
    private  double Solde    ;
    private  double  solde_en_cours ;

    public FicheCompte(String codeCompte, Date dateOperation, String numeroPiece, String libelle, String reference, Date echeance, double debit, double credit, Date heureCreation, String codeModeReglement, double solde, double  solde_en_cours) {
        CodeCompte = codeCompte;
        DateOperation = dateOperation;
        NumeroPiece = numeroPiece;
        Libelle = libelle;
        Reference = reference;
        Echeance = echeance;
        Debit = debit;
        Credit = credit;
        HeureCreation = heureCreation;
        CodeModeReglement = codeModeReglement;
        Solde = solde;

        this.solde_en_cours=solde_en_cours;
    }


    public String getCodeCompte() {
        return CodeCompte;
    }

    public void setCodeCompte(String codeCompte) {
        CodeCompte = codeCompte;
    }

    public Date getDateOperation() {
        return DateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        DateOperation = dateOperation;
    }

    public String getNumeroPiece() {
        return NumeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        NumeroPiece = numeroPiece;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public Date getEcheance() {
        return Echeance;
    }

    public void setEcheance(Date echeance) {
        Echeance = echeance;
    }

    public double getDebit() {
        return Debit;
    }

    public void setDebit(double debit) {
        Debit = debit;
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        Credit = credit;
    }

    public Date getHeureCreation() {
        return HeureCreation;
    }

    public void setHeureCreation(Date heureCreation) {
        HeureCreation = heureCreation;
    }

    public String getCodeModeReglement() {
        return CodeModeReglement;
    }

    public void setCodeModeReglement(String codeModeReglement) {
        CodeModeReglement = codeModeReglement;
    }

    public double getSolde() {
        return Solde;
    }

    public void setSolde(double solde) {
        Solde = solde;
    }

    public double getSolde_en_cours() {
        return solde_en_cours;
    }

    public void setSolde_en_cours(double solde_en_cours) {
        this.solde_en_cours = solde_en_cours;
    }

    @Override
    public String toString() {
        return "FicheCompte{" +
                "CodeCompte='" + CodeCompte + '\'' +
                ", DateOperation=" + DateOperation +
                ", NumeroPiece='" + NumeroPiece + '\'' +
                ", Libelle='" + Libelle + '\'' +
                ", Reference='" + Reference + '\'' +
                ", Echeance=" + Echeance +
                ", Debit=" + Debit +
                ", Credit=" + Credit +
                ", HeureCreation=" + HeureCreation +
                ", CodeModeReglement='" + CodeModeReglement + '\'' +
                ", Solde=" + Solde +
                '}';
    }
}
