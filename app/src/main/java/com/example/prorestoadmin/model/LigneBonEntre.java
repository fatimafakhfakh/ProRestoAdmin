package com.example.prorestoadmin.model;

public class LigneBonEntre {


    private String NumeroBonEntrer;
    private String CodeArticle;
    private String DesignationArticle;
    private int NumeroOrdre;
    private double PrixAchatHT;
    private int Quantite;
    private double MontantHT;
    private double TauxTVA;
    private double MontantTVA;
    private double MontantTTC;
    private String Observation;
    private double Colisage;
    private double Volume;
    private double Poids;
    private String CodeUnite;
    private double ValeurUniteVente;


    public LigneBonEntre(String codeArticle, String designationArticle, int quantite) {
        CodeArticle = codeArticle;
        DesignationArticle = designationArticle;
        Quantite = quantite;
    }



    public LigneBonEntre(String numeroBonEntrer, String codeArticle, String designationArticle, int numeroOrdre, double prixAchatHT, int quantite, double montantHT, double tauxTVA, double montantTVA, double montantTTC, String observation, double colisage, double volume, double poids, String codeUnite, double valeurUniteVente) {
        NumeroBonEntrer = numeroBonEntrer;
        CodeArticle = codeArticle;
        DesignationArticle = designationArticle;
        NumeroOrdre = numeroOrdre;
        PrixAchatHT = prixAchatHT;
        Quantite = quantite;
        MontantHT = montantHT;
        TauxTVA = tauxTVA;
        MontantTVA = montantTVA;
        MontantTTC = montantTTC;
        Observation = observation;
        Colisage = colisage;
        Volume = volume;
        Poids = poids;
        CodeUnite = codeUnite;
        ValeurUniteVente = valeurUniteVente;
    }


    public String getNumeroBonEntrer() {
        return NumeroBonEntrer;
    }

    public void setNumeroBonEntrer(String numeroBonEntrer) {
        NumeroBonEntrer = numeroBonEntrer;
    }

    public String getCodeArticle() {
        return CodeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        CodeArticle = codeArticle;
    }

    public String getDesignationArticle() {
        return DesignationArticle;
    }

    public void setDesignationArticle(String designationArticle) {
        DesignationArticle = designationArticle;
    }

    public int getNumeroOrdre() {
        return NumeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        NumeroOrdre = numeroOrdre;
    }

    public double getPrixAchatHT() {
        return PrixAchatHT;
    }

    public void setPrixAchatHT(double prixAchatHT) {
        PrixAchatHT = prixAchatHT;
    }

    public int getQuantite() {
        return Quantite;
    }

    public void setQuantite(int quantite) {
        Quantite = quantite;
    }

    public double getMontantHT() {
        return MontantHT;
    }

    public void setMontantHT(double montantHT) {
        MontantHT = montantHT;
    }

    public double getTauxTVA() {
        return TauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        TauxTVA = tauxTVA;
    }

    public double getMontantTVA() {
        return MontantTVA;
    }

    public void setMontantTVA(double montantTVA) {
        MontantTVA = montantTVA;
    }

    public double getMontantTTC() {
        return MontantTTC;
    }

    public void setMontantTTC(double montantTTC) {
        MontantTTC = montantTTC;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public double getColisage() {
        return Colisage;
    }

    public void setColisage(double colisage) {
        Colisage = colisage;
    }

    public double getVolume() {
        return Volume;
    }

    public void setVolume(double volume) {
        Volume = volume;
    }

    public double getPoids() {
        return Poids;
    }

    public void setPoids(double poids) {
        Poids = poids;
    }

    public String getCodeUnite() {
        return CodeUnite;
    }

    public void setCodeUnite(String codeUnite) {
        CodeUnite = codeUnite;
    }

    public double getValeurUniteVente() {
        return ValeurUniteVente;
    }

    public void setValeurUniteVente(double valeurUniteVente) {
        ValeurUniteVente = valeurUniteVente;
    }

    @Override
    public String toString() {
        return "LigneBonEntre{" +
                "NumeroBonEntrer='" + NumeroBonEntrer + '\'' +
                ", CodeArticle='" + CodeArticle + '\'' +
                ", DesignationArticle='" + DesignationArticle + '\'' +
                ", NumeroOrdre=" + NumeroOrdre +
                ", PrixAchatHT=" + PrixAchatHT +
                ", Quantite=" + Quantite +
                ", MontantHT=" + MontantHT +
                ", TauxTVA=" + TauxTVA +
                ", MontantTVA=" + MontantTVA +
                ", MontantTTC=" + MontantTTC +
                ", Observation='" + Observation + '\'' +
                ", Colisage=" + Colisage +
                ", Volume=" + Volume +
                ", Poids=" + Poids +
                ", CodeUnite='" + CodeUnite + '\'' +
                ", ValeurUniteVente=" + ValeurUniteVente +
                '}';
    }
}
