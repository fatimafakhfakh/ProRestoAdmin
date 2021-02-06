package com.example.prorestoadmin.model;

import java.util.Date;

public class RappelPaiementClientGlobal {

    private String TypePiece;
    private String CodeDepot;
    private String NumeroPiece;
    private String CodeClient;
    private String RaisonSociale;

    private String CodeRepresentant;
    private String CodeZone;
    private String CodeVille;
    private String CodeSousRegion;
    private Date DatePiece;

    private double Dedit;
    private double Credit;
    private   double  RecuDraft ;


    public RappelPaiementClientGlobal(String typePiece, String codeDepot, String numeroPiece, String codeClient, String raisonSociale, String codeRepresentant, String codeZone, String codeVille, String codeSousRegion, Date datePiece, double dedit, double credit,double  RecuDraft) {
        TypePiece = typePiece;
        CodeDepot = codeDepot;
        NumeroPiece = numeroPiece;
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        CodeRepresentant = codeRepresentant;
        CodeZone = codeZone;
        CodeVille = codeVille;
        CodeSousRegion = codeSousRegion;
        DatePiece = datePiece;
        Dedit = dedit;
        Credit = credit;
        this.RecuDraft =RecuDraft ;
    }


    public String getTypePiece() {
        return TypePiece;
    }

    public void setTypePiece(String typePiece) {
        TypePiece = typePiece;
    }

    public String getCodeDepot() {
        return CodeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        CodeDepot = codeDepot;
    }

    public String getNumeroPiece() {
        return NumeroPiece;
    }

    public void setNumeroPiece(String numeroPiece) {
        NumeroPiece = numeroPiece;
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

    public String getCodeZone() {
        return CodeZone;
    }

    public void setCodeZone(String codeZone) {
        CodeZone = codeZone;
    }

    public String getCodeVille() {
        return CodeVille;
    }

    public void setCodeVille(String codeVille) {
        CodeVille = codeVille;
    }

    public String getCodeSousRegion() {
        return CodeSousRegion;
    }

    public void setCodeSousRegion(String codeSousRegion) {
        CodeSousRegion = codeSousRegion;
    }

    public Date getDatePiece() {
        return DatePiece;
    }

    public void setDatePiece(Date datePiece) {
        DatePiece = datePiece;
    }

    public double getDedit() {
        return Dedit;
    }

    public void setDedit(double dedit) {
        Dedit = dedit;
    }

    public double getCredit() {
        return Credit;
    }

    public void setCredit(double credit) {
        Credit = credit;
    }


    public double getRecuDraft() {
        return RecuDraft;
    }

    public void setRecuDraft(double recuDraft) {
        RecuDraft = recuDraft;
    }

    @Override
    public String toString() {

        return "RappelPaiementClientGlobal{" +
                "TypePiece='" + TypePiece + '\'' +
                ", CodeDepot='" + CodeDepot + '\'' +
                ", NumeroPiece='" + NumeroPiece + '\'' +
                ", CodeClient='" + CodeClient + '\'' +
                ", RaisonSociale='" + RaisonSociale + '\'' +
                ", CodeRepresentant='" + CodeRepresentant + '\'' +
                ", CodeZone='" + CodeZone + '\'' +
                ", CodeVille='" + CodeVille + '\'' +
                ", CodeSousRegion='" + CodeSousRegion + '\'' +
                ", DatePiece=" + DatePiece +
                ", Dedit=" + Dedit +
                ", Credit=" + Credit +
                '}';
    }

}
