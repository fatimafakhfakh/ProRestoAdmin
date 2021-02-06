package com.example.prorestoadmin.model;

import java.util.Date;

public class RecouvrementParJour {

    private Date  Datevente  ;
    private double TotalVente;
    private double TotalRetour;
    private double ToatlRegJ;
    private double TotalRecouvrement;
    private double ResteACredit;
    private double taux_recouvrement;

    public RecouvrementParJour(Date  Datevente   ,double totalVente, double totalRetour, double toatlRegJ, double totalRecouvrement) {
        this.Datevente  = Datevente  ;
        TotalVente = totalVente;
        TotalRetour = totalRetour;
        ToatlRegJ = toatlRegJ;
        TotalRecouvrement = totalRecouvrement;

         this.ResteACredit = TotalVente-TotalRetour-ToatlRegJ  ;
         this.taux_recouvrement  = ToatlRegJ /(TotalVente-TotalRetour)   * 100  ;

    }

    public Date getDatevente() {
        return Datevente;
    }

    public void setDatevente(Date datevente) {
        Datevente = datevente;
    }

    public double getTotalVente() {
        return TotalVente;
    }

    public void setTotalVente(double totalVente) {
        TotalVente = totalVente;
    }

    public double getTotalRetour() {
        return TotalRetour;
    }

    public void setTotalRetour(double totalRetour) {
        TotalRetour = totalRetour;
    }

    public double getToatlRegJ() {
        return ToatlRegJ;
    }

    public void setToatlRegJ(double toatlRegJ) {
        ToatlRegJ = toatlRegJ;
    }

    public double getTotalRecouvrement() {
        return TotalRecouvrement;
    }

    public void setTotalRecouvrement(double totalRecouvrement) {
        TotalRecouvrement = totalRecouvrement;
    }

    public double getResteACredit() {
        return ResteACredit;
    }

    public void setResteACredit(double resteACredit) {
        ResteACredit = resteACredit;
    }

    public double getTaux_recouvrement() {
        return taux_recouvrement;
    }

    public void setTaux_recouvrement(double taux_recouvrement) {
        this.taux_recouvrement = taux_recouvrement;
    }
}
