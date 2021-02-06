package com.example.prorestoadmin.model;

public class Depot {

    private   String  CodeDepot   ;
    private   String  Libelle  ;
    private   int  totalQuantiteStock   ;
    private   int  totalTransfertStaock  ;

    public Depot(String codeDepot, String libelle, int totalQuantiteStock) {
        CodeDepot = codeDepot;
        Libelle = libelle;
        this.totalQuantiteStock = totalQuantiteStock;
    }


    public Depot(String codeDepot, String libelle, int totalQuantiteStock, int totalTransfertStaock) {
        CodeDepot = codeDepot;
        Libelle = libelle;
        this.totalQuantiteStock = totalQuantiteStock;
        this.totalTransfertStaock = totalTransfertStaock;
    }

    public Depot(String codeDepot, String libelle) {
        CodeDepot = codeDepot;
        Libelle = libelle;
    }


    public String getCodeDepot() {
        return CodeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        CodeDepot = codeDepot;
    }

    public String getLibelle() {
        return Libelle;
    }

    public void setLibelle(String libelle) {
        Libelle = libelle;
    }


    public int getTotalQuantiteStock() {
        return totalQuantiteStock;
    }

    public void setTotalQuantiteStock(int totalQuantiteStock) {
        this.totalQuantiteStock = totalQuantiteStock;
    }

    public int getTotalTransfertStaock() {
        return totalTransfertStaock;
    }

    public void setTotalTransfertStaock(int totalTransfertStaock) {
        this.totalTransfertStaock = totalTransfertStaock;
    }

    @Override
    public String toString() {
        return "Depot{" +
                "CodeDepot='" + CodeDepot + '\'' +
                ", Libelle='" + Libelle + '\'' +
                '}';
    }
}
