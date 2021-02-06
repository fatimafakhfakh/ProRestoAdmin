package com.example.prorestoadmin.model.draft;

import java.util.Date;

public class LigneDraftReglement {


    private String NumeroDraftClient;
    private String CodeModeReglement;
    private String Reference;
    private String Echeance;
    private double Montant;



    public LigneDraftReglement(String numeroDraftClient, String codeModeReglement, String reference, String echeance, double montant ) {
        NumeroDraftClient = numeroDraftClient;
        CodeModeReglement = codeModeReglement;
        Reference = reference;
        Echeance = echeance;
        Montant = montant;

    }


    public String getNumeroDraftClient() {
        return NumeroDraftClient;
    }

    public void setNumeroDraftClient(String numeroDraftClient) {
        NumeroDraftClient = numeroDraftClient;
    }

    public String getCodeModeReglement() {
        return CodeModeReglement;
    }

    public void setCodeModeReglement(String codeModeReglement) {
        CodeModeReglement = codeModeReglement;
    }

    public String getReference() {
        return Reference;
    }

    public void setReference(String reference) {
        Reference = reference;
    }

    public String getEcheance() {
        return Echeance;
    }

    public void setEcheance(String echeance) {
        Echeance = echeance;
    }

    public double getMontant() {
        return Montant;
    }

    public void setMontant(double montant) {
        Montant = montant;
    }



    @Override
    public String toString() {
        return "LigneDraftReglement{" +
                "NumeroDraftClient='" + NumeroDraftClient + '\'' +
                ", CodeModeReglement='" + CodeModeReglement + '\'' +
                ", Reference='" + Reference + '\'' +
                ", Echeance=" + Echeance +
                ", Montant=" + Montant +

                '}';
    }
}
