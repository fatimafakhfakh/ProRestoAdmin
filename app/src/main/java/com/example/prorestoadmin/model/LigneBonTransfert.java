package com.example.prorestoadmin.model;

public class LigneBonTransfert {


    private String NumeroBonTransfert;
    private String CodeArticle;
    private String DesignationArticle;
    private int NumeroOrdre;
    private double PrixAchatHT;
    private int Quantite;
    private double MontantHT;
    private double TauxTVA ;
    private double MontantTVA;
    private double MontantTTC ;
    private String Observation ;
    private double  PrixUnitaireNetTTC  ;
    private double   TauxRemise;
    private double  MontantRemise;
    private double   NetHT ;


    public LigneBonTransfert(String numeroBonTransfert, String codeArticle, String designationArticle, int numeroOrdre, double prixAchatHT, int quantite, double montantHT, double tauxTVA, double montantTVA, double montantTTC, String observation, double prixUnitaireNetTTC, double tauxRemise, double montantRemise, double netHT) {
        NumeroBonTransfert = numeroBonTransfert;
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
        PrixUnitaireNetTTC = prixUnitaireNetTTC;
        TauxRemise = tauxRemise;
        MontantRemise = montantRemise;
        NetHT = netHT;
    }


    public LigneBonTransfert(String CodeArticle ,String designationArticle, int quantite) {
        this.CodeArticle = CodeArticle ;
        DesignationArticle = designationArticle;
        Quantite = quantite;
    }

    public String getNumeroBonTransfert() {
        return NumeroBonTransfert;
    }

    public void setNumeroBonTransfert(String numeroBonTransfert) {
        NumeroBonTransfert = numeroBonTransfert;
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

    public double getPrixUnitaireNetTTC() {
        return PrixUnitaireNetTTC;
    }

    public void setPrixUnitaireNetTTC(double prixUnitaireNetTTC) {
        PrixUnitaireNetTTC = prixUnitaireNetTTC;
    }

    public double getTauxRemise() {
        return TauxRemise;
    }

    public void setTauxRemise(double tauxRemise) {
        TauxRemise = tauxRemise;
    }

    public double getMontantRemise() {
        return MontantRemise;
    }

    public void setMontantRemise(double montantRemise) {
        MontantRemise = montantRemise;
    }

    public double getNetHT() {
        return NetHT;
    }

    public void setNetHT(double netHT) {
        NetHT = netHT;
    }

    @Override
    public String toString() {
        return  " LigneBonTransfert{" +
                " NumeroBonTransfert='" + NumeroBonTransfert + '\'' +
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
                ", PrixUnitaireNetTTC=" + PrixUnitaireNetTTC +
                ", TauxRemise=" + TauxRemise +
                ", MontantRemise=" + MontantRemise +
                ", NetHT=" + NetHT +
                '}';
    }
}
