package com.example.prorestoadmin.model;

public class LigneBonGratuite {


    private String NumeroBonGratuiteVente;
    private String CodeArticle;
    private int NumeroOrdre;
    private String DesignationArticle;
    private double PrixVenteHT;
    private int Quantite;
    private double MontantHT;
    private double TauxRemise;
    private double MontantRemise;
    private double RemiseQt;
    private double MontantRemiseQT;
    private double NetHT;
    private double MontantFodec;
    private double TauxTVA;
    private double MontantTVA;
    private double MontantTTC;
    private double PrixAchatNet;
    private double Colisage;
    private double Volume;
    private double Poids;
    private String CodeUnite;
    private double NoteArticle;
    private double PrixUnitaireNetTTC;
    private String Observation;
    private double ValeurUniteVente;
    private double PrixRistourne;


    public LigneBonGratuite(String codeArticle, String designationArticle, int quantite) {
        CodeArticle = codeArticle;
        DesignationArticle = designationArticle;
        Quantite = quantite;
    }

    public LigneBonGratuite(String numeroBonGratuiteVente, String codeArticle, int numeroOrdre, String designationArticle, double prixVenteHT, int quantite, double montantHT, double tauxRemise, double montantRemise, double remiseQt, double montantRemiseQT, double netHT, double montantFodec, double tauxTVA, double montantTVA, double montantTTC, double prixAchatNet, double colisage, double volume, double poids, String codeUnite, double noteArticle, double prixUnitaireNetTTC, String observation, double valeurUniteVente, double prixRistourne) {
        NumeroBonGratuiteVente = numeroBonGratuiteVente;
        CodeArticle = codeArticle;
        NumeroOrdre = numeroOrdre;
        DesignationArticle = designationArticle;
        PrixVenteHT = prixVenteHT;
        Quantite = quantite;
        MontantHT = montantHT;
        TauxRemise = tauxRemise;
        MontantRemise = montantRemise;
        RemiseQt = remiseQt;
        MontantRemiseQT = montantRemiseQT;
        NetHT = netHT;
        MontantFodec = montantFodec;
        TauxTVA = tauxTVA;
        MontantTVA = montantTVA;
        MontantTTC = montantTTC;
        PrixAchatNet = prixAchatNet;
        Colisage = colisage;
        Volume = volume;
        Poids = poids;
        CodeUnite = codeUnite;
        NoteArticle = noteArticle;
        PrixUnitaireNetTTC = prixUnitaireNetTTC;
        Observation = observation;
        ValeurUniteVente = valeurUniteVente;
        PrixRistourne = prixRistourne;
    }


    public String getNumeroBonGratuiteVente() {
        return NumeroBonGratuiteVente;
    }

    public void setNumeroBonGratuiteVente(String numeroBonGratuiteVente) {
        NumeroBonGratuiteVente = numeroBonGratuiteVente;
    }

    public String getCodeArticle() {
        return CodeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        CodeArticle = codeArticle;
    }

    public int getNumeroOrdre() {
        return NumeroOrdre;
    }

    public void setNumeroOrdre(int numeroOrdre) {
        NumeroOrdre = numeroOrdre;
    }

    public String getDesignationArticle() {
        return DesignationArticle;
    }

    public void setDesignationArticle(String designationArticle) {
        DesignationArticle = designationArticle;
    }

    public double getPrixVenteHT() {
        return PrixVenteHT;
    }

    public void setPrixVenteHT(double prixVenteHT) {
        PrixVenteHT = prixVenteHT;
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

    public double getRemiseQt() {
        return RemiseQt;
    }

    public void setRemiseQt(double remiseQt) {
        RemiseQt = remiseQt;
    }

    public double getMontantRemiseQT() {
        return MontantRemiseQT;
    }

    public void setMontantRemiseQT(double montantRemiseQT) {
        MontantRemiseQT = montantRemiseQT;
    }

    public double getNetHT() {
        return NetHT;
    }

    public void setNetHT(double netHT) {
        NetHT = netHT;
    }

    public double getMontantFodec() {
        return MontantFodec;
    }

    public void setMontantFodec(double montantFodec) {
        MontantFodec = montantFodec;
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

    public double getPrixAchatNet() {
        return PrixAchatNet;
    }

    public void setPrixAchatNet(double prixAchatNet) {
        PrixAchatNet = prixAchatNet;
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

    public double getNoteArticle() {
        return NoteArticle;
    }

    public void setNoteArticle(double noteArticle) {
        NoteArticle = noteArticle;
    }

    public double getPrixUnitaireNetTTC() {
        return PrixUnitaireNetTTC;
    }

    public void setPrixUnitaireNetTTC(double prixUnitaireNetTTC) {
        PrixUnitaireNetTTC = prixUnitaireNetTTC;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public double getValeurUniteVente() {
        return ValeurUniteVente;
    }

    public void setValeurUniteVente(double valeurUniteVente) {
        ValeurUniteVente = valeurUniteVente;
    }

    public double getPrixRistourne() {
        return PrixRistourne;
    }

    public void setPrixRistourne(double prixRistourne) {
        PrixRistourne = prixRistourne;
    }


    @Override
    public String toString() {
        return "LigneBonGratuite{" +
                "NumeroBonGratuiteVente='" + NumeroBonGratuiteVente + '\'' +
                ", CodeArticle='" + CodeArticle + '\'' +
                ", NumeroOrdre=" + NumeroOrdre +
                ", DesignationArticle='" + DesignationArticle + '\'' +
                ", PrixVenteHT=" + PrixVenteHT +
                ", Quantite=" + Quantite +
                ", MontantHT=" + MontantHT +
                ", TauxRemise=" + TauxRemise +
                ", MontantRemise=" + MontantRemise +
                ", RemiseQt=" + RemiseQt +
                ", MontantRemiseQT=" + MontantRemiseQT +
                ", NetHT=" + NetHT +
                ", MontantFodec=" + MontantFodec +
                ", TauxTVA=" + TauxTVA +
                ", MontantTVA=" + MontantTVA +
                ", MontantTTC=" + MontantTTC +
                ", PrixAchatNet=" + PrixAchatNet +
                ", Colisage=" + Colisage +
                ", Volume=" + Volume +
                ", Poids=" + Poids +
                ", CodeUnite='" + CodeUnite + '\'' +
                ", NoteArticle=" + NoteArticle +
                ", PrixUnitaireNetTTC=" + PrixUnitaireNetTTC +
                ", Observation='" + Observation + '\'' +
                ", ValeurUniteVente=" + ValeurUniteVente +
                ", PrixRistourne=" + PrixRistourne +
                '}';
    }
}
