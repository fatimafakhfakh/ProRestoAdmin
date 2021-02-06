package com.example.prorestoadmin.model.draft;

public class DetailDraftReglementClient {


    private String  NumeroDraftClient	;
    private String NumeroPiece;
    private double  TotalTVA	;
    private double MontantPieceTTC	;
    private String  Observation;
    private double  TotalRecuReglement	;
    private double  TotalRecuDraft	;
    private double TotalRestant	;
    private double TotalPayee	;
    private double TotalRetenu;
    private String  TypePiece	;


    public DetailDraftReglementClient(String numeroPiece, double montantPieceTTC, double totalRecuDraft) {
        NumeroPiece = numeroPiece;
        MontantPieceTTC = montantPieceTTC;
        TotalRecuDraft = totalRecuDraft;
    }

    public DetailDraftReglementClient(String numeroDraftClient, String numeroPiece, double totalTVA, double montantPieceTTC, String observation, double totalRecuReglement, double totalRecuDraft, double totalRestant, double totalPayee, double totalRetenu, String typePiece) {
        NumeroDraftClient = numeroDraftClient;
        NumeroPiece = numeroPiece;
        TotalTVA = totalTVA;
        MontantPieceTTC = montantPieceTTC;
        Observation = observation;
        TotalRecuReglement = totalRecuReglement;
        TotalRecuDraft = totalRecuDraft;
        TotalRestant = totalRestant;
        TotalPayee = totalPayee;
        TotalRetenu = totalRetenu;
        TypePiece = typePiece;
    }

    public String getNumeroDraftClient() {
        return NumeroDraftClient;
    }

    public void setNumeroDraftClient(String numeroDraftClient) {
        NumeroDraftClient = numeroDraftClient;
    }

    public String getNumeroPiece() {
        return NumeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        NumeroPiece = numeroPiece;
    }

    public double getTotalTVA() {
        return TotalTVA;
    }

    public void setTotalTVA(double totalTVA) {
        TotalTVA = totalTVA;
    }

    public double getMontantPieceTTC() {
        return MontantPieceTTC;
    }

    public void setMontantPieceTTC(double montantPieceTTC) {
        MontantPieceTTC = montantPieceTTC;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public double getTotalRecuReglement() {
        return TotalRecuReglement;
    }

    public void setTotalRecuReglement(double totalRecuReglement) {
        TotalRecuReglement = totalRecuReglement;
    }

    public double getTotalRecuDraft() {
        return TotalRecuDraft;
    }

    public void setTotalRecuDraft(double totalRecuDraft) {
        TotalRecuDraft = totalRecuDraft;
    }

    public double getTotalRestant() {
        return TotalRestant;
    }

    public void setTotalRestant(double totalRestant) {
        TotalRestant = totalRestant;
    }

    public double getTotalPayee() {
        return TotalPayee;
    }

    public void setTotalPayee(double totalPayee) {
        TotalPayee = totalPayee;
    }

    public double getTotalRetenu() {
        return TotalRetenu;
    }

    public void setTotalRetenu(double totalRetenu) {
        TotalRetenu = totalRetenu;
    }

    public String getTypePiece() {
        return TypePiece;
    }

    public void setTypePiece(String typePiece) {
        TypePiece = typePiece;
    }


    @Override
    public String toString() {
        return "DetailDraftReglementClient{" +
                "NumeroDraftClient='" + NumeroDraftClient + '\'' +
                ", NumeroPiece='" + NumeroPiece + '\'' +
                ", TotalTVA=" + TotalTVA +
                ", MontantPieceTTC=" + MontantPieceTTC +
                ", Observation='" + Observation + '\'' +
                ", TotalRecuReglement=" + TotalRecuReglement +
                ", TotalRecuDraft=" + TotalRecuDraft +
                ", TotalRestant=" + TotalRestant +
                ", TotalPayee=" + TotalPayee +
                ", TotalRetenu=" + TotalRetenu +
                ", TypePiece='" + TypePiece + '\'' +
                '}';
    }
}
