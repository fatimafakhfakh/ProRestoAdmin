package com.example.prorestoadmin.model;

import java.util.Date;

public class PieceRappelPaiment {


    private Date DatePiece;
    private String TypePiece;
    private String NumeroPiece;

    private double Montant;
    private double Reglement;
    private double ResteAPayer;
    private double RecuDraft;


    public PieceRappelPaiment(Date datePiece, String typePiece, String numeroPiece, double montant, double reglement, double resteAPayer,double RecuDraft) {
        DatePiece = datePiece;
        TypePiece = typePiece;
        NumeroPiece = numeroPiece;
        Montant = montant;
        Reglement = reglement;
        ResteAPayer = resteAPayer;
        this.RecuDraft=RecuDraft ;
    }


    public Date getDatePiece() {
        return DatePiece;
    }

    public void setDatePiece(Date datePiece) {
        DatePiece = datePiece;
    }

    public String getTypePiece() {
        return TypePiece;
    }

    public void setTypePiece(String typePiece) {
        TypePiece = typePiece;
    }

    public String getNumeroPiece() {
        return NumeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        NumeroPiece = numeroPiece;
    }

    public double getMontant() {
        return Montant;
    }

    public void setMontant(double montant) {
        Montant = montant;
    }

    public double getReglement() {
        return Reglement;
    }

    public void setReglement(double reglement) {
        Reglement = reglement;
    }

    public double getResteAPayer() {
        return ResteAPayer;
    }

    public void setResteAPayer(double resteAPayer) {
        ResteAPayer = resteAPayer;
    }


    public double getRecuDraft() {
        return RecuDraft;
    }

    public void setRecuDraft(double recuDraft) {
        RecuDraft = recuDraft;
    }

    @Override
    public String toString() {

        return "PieceRappelPaiment{" +
                "DatePiece=" + DatePiece +
                ", TypePiece='" + TypePiece + '\'' +
                ", NumeroPiece='" + NumeroPiece + '\'' +
                ", Montant=" + Montant +
                ", Reglement=" + Reglement +
                ", ResteAPayer=" + ResteAPayer +
                '}';

    }
}
