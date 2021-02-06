package com.example.prorestoadmin.model;

public class MouvementArticle {

    private String CodeArticle;
    private String DesignationArticle;
    private int QTProd;

    private int QTSortieTransfert;
    private int QTEntreeTransfert;

    private int QTRetour;
    private int QTLivraison;
    private int QTGratuit;

    private int QtSortie;
    private int QtEntree;


    public MouvementArticle(String codeArticle,String DesignationArticle , int QTProd, int QTSortieTransfert, int QTEntreeTransfert, int QTRetour, int QTLivraison, int QTGratuit) {
        CodeArticle = codeArticle;
        this.DesignationArticle =DesignationArticle  ;
        this.QTProd = QTProd;
        this.QTSortieTransfert = QTSortieTransfert;
        this.QTEntreeTransfert = QTEntreeTransfert;
        this.QTRetour = QTRetour;
        this.QTLivraison = QTLivraison;
        this.QTGratuit = QTGratuit;


        this.QtSortie =    this.QTSortieTransfert+    this.QTGratuit + this.QTLivraison ;
        this.QtEntree   =  this.QTEntreeTransfert +     this.QTRetour ;
    }


    public String getCodeArticle() {
        return CodeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        CodeArticle = codeArticle;
    }

    public int getQTProd() {
        return QTProd;
    }

    public void setQTProd(int QTProd) {
        this.QTProd = QTProd;
    }

    public int getQTSortieTransfert() {
        return QTSortieTransfert;
    }

    public void setQTSortieTransfert(int QTSortieTransfert) {
        this.QTSortieTransfert = QTSortieTransfert;
    }

    public int getQTEntreeTransfert() {
        return QTEntreeTransfert;
    }

    public void setQTEntreeTransfert(int QTEntreeTransfert) {
        this.QTEntreeTransfert = QTEntreeTransfert;
    }

    public int getQTRetour() {
        return QTRetour;
    }

    public void setQTRetour(int QTRetour) {
        this.QTRetour = QTRetour;
    }

    public int getQTLivraison() {
        return QTLivraison;
    }

    public void setQTLivraison(int QTLivraison) {
        this.QTLivraison = QTLivraison;
    }

    public int getQTGratuit() {
        return QTGratuit;
    }

    public void setQTGratuit(int QTGratuit) {
        this.QTGratuit = QTGratuit;
    }

    public int getQtSortie() {
        return QtSortie;
    }

    public void setQtSortie(int qtSortie) {
        QtSortie = qtSortie;
    }

    public int getQtEntree() {
        return QtEntree;
    }

    public void setQtEntree(int qtEntree) {
        QtEntree = qtEntree;
    }


    public String getDesignationArticle() {
        return DesignationArticle;
    }

    public void setDesignationArticle(String designationArticle) {
        DesignationArticle = designationArticle;
    }

    @Override
    public String toString() {

        return "MouvementArticle{" +
                "CodeArticle='" + CodeArticle + '\'' +
                ", QTProd=" + QTProd +
                ", QTSortieTransfert=" + QTSortieTransfert +
                ", QTEntreeTransfert=" + QTEntreeTransfert +
                ", QTRetour=" + QTRetour +
                ", QTLivraison=" + QTLivraison +
                ", QTGratuit=" + QTGratuit +
                ", QtSortie=" + QtSortie +
                ", QtEntree=" + QtEntree +
                '}';

    }
}
