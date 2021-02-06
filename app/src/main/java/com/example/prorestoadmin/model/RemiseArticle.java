package com.example.prorestoadmin.model;

public class RemiseArticle {

    private   String   CodeClient  ;
    private   String  RaisonSociale  ;
    private  String  CodeArticle  ;
    private  String   Designation   ;
    private  double TauxRemise  ;
    private   double PrixVenteTTC  ;


    private   double PrixVenteHT  ;
    private  int CodeTVA ;
    private   double TauxTVA  ;

    private double  PrixVenteTTCRemise ;




    public RemiseArticle( String codeArticle, String designation, double tauxRemise, double prixVenteTTC, double prixVenteHT, int codeTVA, double tauxTVA) {

        CodeArticle = codeArticle;
        Designation = designation;
        TauxRemise = tauxRemise;
        PrixVenteTTC = prixVenteTTC;
        PrixVenteHT = prixVenteHT;
        CodeTVA = codeTVA;
        TauxTVA = tauxTVA;

        double _montant_ht =   PrixVenteHT;
        double _taux_tva = TauxTVA ;


        double _taux_remise = TauxRemise;
        double _montant_remise = _montant_ht * _taux_remise /100 ;

        double _TempHT = _montant_ht - _montant_remise  ;


        double _montant_tva = _TempHT * _taux_tva / 100;



        PrixVenteTTCRemise  = _montant_ht + _montant_tva - _montant_remise;

    }



    public RemiseArticle(String codeClient, String raisonSociale, String codeArticle, String designation, double tauxRemise, double prixVenteTTC, double prixVenteHT, int codeTVA, double tauxTVA) {
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        CodeArticle = codeArticle;
        Designation = designation;
        TauxRemise = tauxRemise;
        PrixVenteTTC = prixVenteTTC;
        PrixVenteHT = prixVenteHT;
        CodeTVA = codeTVA;
        TauxTVA = tauxTVA;


        double _montant_ht =   PrixVenteHT;
        double _taux_tva = TauxTVA ;


        double _taux_remise = TauxRemise;
        double _montant_remise = _montant_ht * _taux_remise /100 ;

        double _TempHT = _montant_ht - _montant_remise  ;


        double _montant_tva = _TempHT * _taux_tva / 100;
        PrixVenteTTCRemise  = _montant_ht + _montant_tva - _montant_remise;

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

    public String getCodeArticle() {
        return CodeArticle;
    }

    public void setCodeArticle(String codeArticle) {
        CodeArticle = codeArticle;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public double getTauxRemise() {
        return TauxRemise;
    }

    public void setTauxRemise(double tauxRemise) {
        TauxRemise = tauxRemise;
    }

    public double getPrixVenteTTC() {
        return PrixVenteTTC;
    }

    public void setPrixVenteTTC(double prixVenteTTC) {
        PrixVenteTTC = prixVenteTTC;
    }

    public double getPrixVenteHT() {
        return PrixVenteHT;
    }

    public void setPrixVenteHT(double prixVenteHT) {
        PrixVenteHT = prixVenteHT;
    }

    public int getCodeTVA() {
        return CodeTVA;
    }

    public void setCodeTVA(int codeTVA) {
        CodeTVA = codeTVA;
    }

    public double getTauxTVA() {
        return TauxTVA;
    }

    public void setTauxTVA(double tauxTVA) {
        TauxTVA = tauxTVA;
    }

    public double getPrixVenteTTCRemise() {
        return PrixVenteTTCRemise;
    }

    public void setPrixVenteTTCRemise(double prixVenteTTCRemise) {
        PrixVenteTTCRemise = prixVenteTTCRemise;
    }
}
