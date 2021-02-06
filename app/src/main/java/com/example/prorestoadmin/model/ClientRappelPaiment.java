package com.example.prorestoadmin.model;

import java.util.ArrayList;

public class ClientRappelPaiment {



    private  String  CodeClient ;
    private  String RaisonSocial ;
    private  double TotalMontant ;
    private  double TotalReglement  ;
    private  double TotalResteAPayer  ;
    private  double SoldeClient ;
    private  double TotalRecuDraft ;


    private ArrayList<PieceRappelPaiment> listRappelPaiment  ;


    public ClientRappelPaiment() {
    }

    public ClientRappelPaiment(String codeClient, String raisonSocial, double totalMontant, double totalReglement, double totalResteAPayer, double soldeClient,double TotalRecuDraft) {
        CodeClient = codeClient;
        RaisonSocial = raisonSocial;
        TotalMontant = totalMontant;
        TotalReglement = totalReglement;
        TotalResteAPayer = totalResteAPayer;
        SoldeClient = soldeClient;
        this.TotalRecuDraft=TotalRecuDraft ;
    }


    public String getCodeClient() {
        return CodeClient;
    }

    public void setCodeClient(String codeClient) {
        CodeClient = codeClient;
    }

    public String getRaisonSocial() {
        return RaisonSocial;
    }

    public void setRaisonSocial(String raisonSocial) {
        RaisonSocial = raisonSocial;
    }

    public double getTotalMontant() {
        return TotalMontant;
    }

    public void setTotalMontant(double totalMontant) {
        TotalMontant = totalMontant;
    }

    public double getTotalReglement() {
        return TotalReglement;
    }

    public void setTotalReglement(double totalReglement) {
        TotalReglement = totalReglement;
    }

    public double getTotalResteAPayer() {
        return TotalResteAPayer;
    }

    public void setTotalResteAPayer(double totalResteAPayer) {
        TotalResteAPayer = totalResteAPayer;
    }

    public double getSoldeClient() {
        return SoldeClient;
    }

    public void setSoldeClient(double soldeClient) {
        SoldeClient = soldeClient;
    }

    public double getTotalRecuDraft() {
        return TotalRecuDraft;
    }

    public void setTotalRecuDraft(double totalRecuDraft) {
        TotalRecuDraft = totalRecuDraft;
    }

    public ArrayList<PieceRappelPaiment> getListRappelPaiment() {
        return listRappelPaiment;
    }

    public void setListRappelPaiment(ArrayList<PieceRappelPaiment> listRappelPaiment) {
        this.listRappelPaiment = listRappelPaiment;
    }


    @Override
    public String toString() {
        return "ClientRappelPaiment{" +
                "CodeClient='" + CodeClient + '\'' +
                ", RaisonSocial='" + RaisonSocial + '\'' +
                ", TotalMontant=" + TotalMontant +
                ", TotalReglement=" + TotalReglement +
                ", TotalResteAPayer=" + TotalResteAPayer +
                ", SoldeClient=" + SoldeClient +
                ", listRappelPaiment=" + listRappelPaiment +
                '}';
    }
}
