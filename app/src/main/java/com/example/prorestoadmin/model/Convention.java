package com.example.prorestoadmin.model;

import java.util.Date;

public class Convention {

    private String CodeArticle;
    private String Designation;
    private int QuantiteMinimum;
    private int QuantiteGratuite;
    private String Convention;
    private int QuantiteVendu;
    private int QuantiteRetour;
    private int QTPalierGratuite;
    private  int  nbr_click  ;
    private  int  Periode  ;

    public Convention(String codeArticle, String designation, int quantiteMinimum, int quantiteGratuite, String convention, int quantiteVendu, int quantiteRetour, int QTPalierGratuite,int  Periode ) {
        CodeArticle = codeArticle;
        Designation = designation;
        QuantiteMinimum = quantiteMinimum;
        QuantiteGratuite = quantiteGratuite;
        Convention = convention;
        QuantiteVendu = quantiteVendu;
        QuantiteRetour = quantiteRetour;
        this.QTPalierGratuite = QTPalierGratuite;
        this.Periode=Periode ;
    }

    public Convention(String codeArticle, String designation, int quantiteMinimum, int quantiteGratuite, String convention, int quantiteVendu, int quantiteRetour, int QTPalierGratuite,int  Periode  , int nbr_click) {
        CodeArticle = codeArticle;
        Designation = designation;
        QuantiteMinimum = quantiteMinimum;
        QuantiteGratuite = quantiteGratuite;
        Convention = convention;
        QuantiteVendu = quantiteVendu;
        QuantiteRetour = quantiteRetour;
        this.QTPalierGratuite = QTPalierGratuite;
        this.Periode=Periode ;
        this.nbr_click = nbr_click;
    }

    public String getCodeArticle() {
        return CodeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        CodeArticle = codeArticle;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public int getQuantiteMinimum() {
        return QuantiteMinimum;
    }

    public void setQuantiteMinimum(int quantiteMinimum) {
        QuantiteMinimum = quantiteMinimum;
    }

    public int getQuantiteGratuite() {
        return QuantiteGratuite;
    }

    public void setQuantiteGratuite(int quantiteGratuite) {
        QuantiteGratuite = quantiteGratuite;
    }

    public String getConvention() {
        return Convention;
    }

    public void setConvention(String convention) {
        Convention = convention;
    }

    public int getQuantiteVendu() {
        return QuantiteVendu;
    }

    public void setQuantiteVendu(int quantiteVendu) {
        QuantiteVendu = quantiteVendu;
    }

    public int getQuantiteRetour() {
        return QuantiteRetour;
    }

    public void setQuantiteRetour(int quantiteRetour) {
        QuantiteRetour = quantiteRetour;
    }

    public int getQTPalierGratuite() {
        return QTPalierGratuite;
    }

    public void setQTPalierGratuite(int QTPalierGratuite) {
        this.QTPalierGratuite = QTPalierGratuite;
    }


    public int getNbr_click() {
        return nbr_click;
    }

    public void setNbr_click(int nbr_click) {
        this.nbr_click = nbr_click;
    }


    public int getPeriode() {
        return Periode;
    }

    public void setPeriode(int periode) {
        Periode = periode;
    }

    @Override
    public String toString() {
        return "Convention{" +
                "CodeArticle='" + CodeArticle + '\'' +
                ", Designation='" + Designation + '\'' +
                ", QuantiteMinimum=" + QuantiteMinimum +
                ", QuantiteGratuite=" + QuantiteGratuite +
                ", Convention='" + Convention + '\'' +
                ", QuantiteVendu=" + QuantiteVendu +
                ", QuantiteRetour=" + QuantiteRetour +
                ", QTPalierGratuite=" + QTPalierGratuite +
                '}';
    }
}
