package com.example.prorestoadmin.model;

public class Personnel {


    private  String  CodeResponsable ;
    private  String  NomResponsable  ;
    private  String  CodeFonction   ;
    private  String  Fonction    ;
    private  String  NomUtilisateur     ;
    private   int   QTLivraison  ;
    private   double   TTCLivraison  ;
    private   int   QTRetour  ;
    private   double   TTCRetour  ;
    private   int   QTGratuite  ;
    private   double   TotalDraftRèglement  ;
   // private   double   Caisse  ;

    public Personnel(String codeResponsable, String nomResponsable) {
        CodeResponsable = codeResponsable;
        NomResponsable = nomResponsable;
    }

    public Personnel(String codeResponsable, String nomResponsable, String codeFonction, String fonction, String nomUtilisateur, int QTLivraison, double TTCLivraison, int QTRetour, double TTCRetour, int QTGratuite, double totalDraftRèglement ) {
        CodeResponsable = codeResponsable;
        NomResponsable = nomResponsable;
        CodeFonction = codeFonction;
        Fonction = fonction;
        NomUtilisateur = nomUtilisateur;
        this.QTLivraison = QTLivraison;
        this.TTCLivraison = TTCLivraison;
        this.QTRetour = QTRetour;
        this.TTCRetour = TTCRetour;
        this.QTGratuite = QTGratuite;
        TotalDraftRèglement = totalDraftRèglement;
      //  Caisse = caisse;
    }

    public Personnel (String codeResponsable, String nomResponsable, String codeFonction, String fonction, String nomUtilisateur, int QTLivraison, int QTRetour, int QTGratuite, double totalDraftRèglement ) {
        CodeResponsable = codeResponsable;
        NomResponsable = nomResponsable;
        CodeFonction = codeFonction;
        Fonction = fonction;
        NomUtilisateur = nomUtilisateur;
        this.QTLivraison = QTLivraison;
        this.QTRetour = QTRetour;
        this.QTGratuite = QTGratuite;
        TotalDraftRèglement = totalDraftRèglement;

    }


    public String getCodeResponsable() {
        return CodeResponsable;
    }

    public void setCodeResponsable(String codeResponsable) {
        CodeResponsable = codeResponsable;
    }

    public String getNomResponsable() {
        return NomResponsable;
    }

    public void setNomResponsable(String nomResponsable) {
        NomResponsable = nomResponsable;
    }

    public String getCodeFonction() {
        return CodeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        CodeFonction = codeFonction;
    }

    public String getFonction() {
        return Fonction;
    }

    public void setFonction(String fonction) {
        Fonction = fonction;
    }

    public String getNomUtilisateur() {
        return NomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        NomUtilisateur = nomUtilisateur;
    }

    public int getQTLivraison() {
        return QTLivraison;
    }

    public void setQTLivraison(int QTLivraison) {
        this.QTLivraison = QTLivraison;
    }

    public int getQTRetour() {
        return QTRetour;
    }

    public void setQTRetour(int QTRetour) {
        this.QTRetour = QTRetour;
    }

    public int getQTGratuite() {
        return QTGratuite;
    }

    public void setQTGratuite(int QTGratuite) {
        this.QTGratuite = QTGratuite;
    }

    public double getTotalDraftRèglement() {
        return TotalDraftRèglement;
    }

    public void setTotalDraftRèglement(double totalDraftRèglement) {
        TotalDraftRèglement = totalDraftRèglement;
    }




    public double getTTCLivraison() {
        return TTCLivraison;
    }

    public void setTTCLivraison(double TTCLivraison) {
        this.TTCLivraison = TTCLivraison;
    }

    public double getTTCRetour() {
        return TTCRetour;
    }

    public void setTTCRetour(double TTCRetour) {
        this.TTCRetour = TTCRetour;
    }

    @Override
    public String toString() {

        return "Personnel{" +
                "CodeResponsable='" + CodeResponsable + '\'' +
                ", NomResponsable='" + NomResponsable + '\'' +
                ", CodeFonction='" + CodeFonction + '\'' +
                ", Fonction='" + Fonction + '\'' +
                ", NomUtilisateur='" + NomUtilisateur + '\'' +
                ", QTLivraison=" + QTLivraison +
                ", QTRetour=" + QTRetour +
                ", QTGratuite=" + QTGratuite +
                ", TotalDraftRèglement=" + TotalDraftRèglement +
                '}';
    }
}
