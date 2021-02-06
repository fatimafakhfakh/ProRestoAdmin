package com.example.prorestoadmin.model;

import java.util.ArrayList;
import java.util.Date;

public class BonGratuiteVente {


    private String NumeroBonGratuiteVente;
    private Date DateBonGratuiteVente;
    private String ReferenceBonLivraisonVente;
    private String CodeDepot;
    private String CodeClient;
    private String RaisonSociale;
    private String MartriculeFiscale;
    private String Adresse1;
    private String CodeModeReglement;
    private String NumeroExonoration;
    private Date DateDebutExonoration;
    private Date DateFinExonoration;
    private String NumeroExportation;
    private String CodeDevise;
    private String CoursDevise;
    private String NumeroFactureVente;
    private double MontantRecu;
    private double MontantRendu;
    private String CodeRepresentant;
    private String CodeForceVente;
    private int Classic;
    private int Promo;
    private int Comptoir;
    private int RemiseQuantite;
    private String TypeRemiseChoisie;
    private double TotalHT;
    private double TotalRemise;
    private double TotalNetHT;
    private double TotalFodec;
    private double TotalTVA;
    private double TotalTTC;
    private double TotalNote;

    private double TotalVolume;
    private double TotalPoids;
    private double TotalColisage;

    private String NumeroEtat;
    private String NomUtilisateur;
    private Date DateCreation;
    private Date HeureCreation;
    private String Observation;
    private String NoteInterne;
    private String CodeVoiture;
    private String CodeChauffeur;
    private String LibelleMission;
    private String NomValidateur;
    private String DateValidation;
    private String NomAnnulation;
    private String DateAnnulation;
    private int NbImpression;
    private String NumeroMission;
    private int PrixUsine;
    private String UtilisateurIntervenant;


    private ArrayList<LigneBonGratuite> list_ligne_bon_gratuite ;

    public BonGratuiteVente(String NumeroBonGratuiteVente, Date heureCreation, String codeClient, String raisonSociale, String NomUtilisateur, double TotalTTC, String numeroEtat) {
        this.NumeroBonGratuiteVente = NumeroBonGratuiteVente;
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        this.NomUtilisateur = NomUtilisateur;
        NumeroEtat = numeroEtat;
        this.TotalTTC = TotalTTC;
        HeureCreation = heureCreation;
    }


    public BonGratuiteVente(String numeroBonGratuiteVente, Date dateBonGratuiteVente, String referenceBonLivraisonVente, String codeDepot, String codeClient, String raisonSociale, String martriculeFiscale, String adresse1, String codeModeReglement, String numeroExonoration, Date dateDebutExonoration, Date dateFinExonoration, String numeroExportation, String codeDevise, String coursDevise, String numeroFactureVente, double montantRecu, double montantRendu, String codeRepresentant, String codeForceVente, int classic, int promo, int comptoir, int remiseQuantite, String typeRemiseChoisie, double totalHT, double totalRemise, double totalNetHT, double totalFodec, double totalTVA, double totalTTC, double totalNote, double totalVolume, double totalPoids, double totalColisage, String numeroEtat, String nomUtilisateur, Date dateCreation, Date heureCreation, String observation, String noteInterne, String codeVoiture, String codeChauffeur, String libelleMission, String nomValidateur, String dateValidation, String nomAnnulation, String dateAnnulation, int nbImpression, String numeroMission, int prixUsine, String utilisateurIntervenant) {
        NumeroBonGratuiteVente = numeroBonGratuiteVente;
        DateBonGratuiteVente = dateBonGratuiteVente;
        ReferenceBonLivraisonVente = referenceBonLivraisonVente;
        CodeDepot = codeDepot;
        CodeClient = codeClient;
        RaisonSociale = raisonSociale;
        MartriculeFiscale = martriculeFiscale;
        Adresse1 = adresse1;
        CodeModeReglement = codeModeReglement;
        NumeroExonoration = numeroExonoration;
        DateDebutExonoration = dateDebutExonoration;
        DateFinExonoration = dateFinExonoration;
        NumeroExportation = numeroExportation;
        CodeDevise = codeDevise;
        CoursDevise = coursDevise;
        NumeroFactureVente = numeroFactureVente;
        MontantRecu = montantRecu;
        MontantRendu = montantRendu;
        CodeRepresentant = codeRepresentant;
        CodeForceVente = codeForceVente;
        Classic = classic;
        Promo = promo;
        Comptoir = comptoir;
        RemiseQuantite = remiseQuantite;
        TypeRemiseChoisie = typeRemiseChoisie;
        TotalHT = totalHT;
        TotalRemise = totalRemise;
        TotalNetHT = totalNetHT;
        TotalFodec = totalFodec;
        TotalTVA = totalTVA;
        TotalTTC = totalTTC;
        TotalNote = totalNote;
        TotalVolume = totalVolume;
        TotalPoids = totalPoids;
        TotalColisage = totalColisage;
        NumeroEtat = numeroEtat;
        NomUtilisateur = nomUtilisateur;
        DateCreation = dateCreation;
        HeureCreation = heureCreation;
        Observation = observation;
        NoteInterne = noteInterne;
        CodeVoiture = codeVoiture;
        CodeChauffeur = codeChauffeur;
        LibelleMission = libelleMission;
        NomValidateur = nomValidateur;
        DateValidation = dateValidation;
        NomAnnulation = nomAnnulation;
        DateAnnulation = dateAnnulation;
        NbImpression = nbImpression;
        NumeroMission = numeroMission;
        PrixUsine = prixUsine;
        UtilisateurIntervenant = utilisateurIntervenant;
    }


    public String getNumeroBonGratuiteVente() {
        return NumeroBonGratuiteVente;
    }

    public void setNumeroBonGratuiteVente(String numeroBonGratuiteVente) {
        NumeroBonGratuiteVente = numeroBonGratuiteVente;
    }

    public Date getDateBonGratuiteVente() {
        return DateBonGratuiteVente;
    }

    public void setDateBonGratuiteVente(Date dateBonGratuiteVente) {
        DateBonGratuiteVente = dateBonGratuiteVente;
    }

    public String getReferenceBonLivraisonVente() {
        return ReferenceBonLivraisonVente;
    }

    public void setReferenceBonLivraisonVente(String referenceBonLivraisonVente) {
        ReferenceBonLivraisonVente = referenceBonLivraisonVente;
    }

    public String getCodeDepot() {
        return CodeDepot;
    }

    public void setCodeDepot(String codeDepot) {
        CodeDepot = codeDepot;
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

    public String getMartriculeFiscale() {
        return MartriculeFiscale;
    }

    public void setMartriculeFiscale(String martriculeFiscale) {
        MartriculeFiscale = martriculeFiscale;
    }

    public String getAdresse1() {
        return Adresse1;
    }

    public void setAdresse1(String adresse1) {
        Adresse1 = adresse1;
    }

    public String getCodeModeReglement() {
        return CodeModeReglement;
    }

    public void setCodeModeReglement(String codeModeReglement) {
        CodeModeReglement = codeModeReglement;
    }

    public String getNumeroExonoration() {
        return NumeroExonoration;
    }

    public void setNumeroExonoration(String numeroExonoration) {
        NumeroExonoration = numeroExonoration;
    }

    public Date getDateDebutExonoration() {
        return DateDebutExonoration;
    }

    public void setDateDebutExonoration(Date dateDebutExonoration) {
        DateDebutExonoration = dateDebutExonoration;
    }

    public Date getDateFinExonoration() {
        return DateFinExonoration;
    }

    public void setDateFinExonoration(Date dateFinExonoration) {
        DateFinExonoration = dateFinExonoration;
    }

    public String getNumeroExportation() {
        return NumeroExportation;
    }

    public void setNumeroExportation(String numeroExportation) {
        NumeroExportation = numeroExportation;
    }

    public String getCodeDevise() {
        return CodeDevise;
    }

    public void setCodeDevise(String codeDevise) {
        CodeDevise = codeDevise;
    }

    public String getCoursDevise() {
        return CoursDevise;
    }

    public void setCoursDevise(String coursDevise) {
        CoursDevise = coursDevise;
    }

    public String getNumeroFactureVente() {
        return NumeroFactureVente;
    }

    public void setNumeroFactureVente(String numeroFactureVente) {
        NumeroFactureVente = numeroFactureVente;
    }

    public double getMontantRecu() {
        return MontantRecu;
    }

    public void setMontantRecu(double montantRecu) {
        MontantRecu = montantRecu;
    }

    public double getMontantRendu() {
        return MontantRendu;
    }

    public void setMontantRendu(double montantRendu) {
        MontantRendu = montantRendu;
    }

    public String getCodeRepresentant() {
        return CodeRepresentant;
    }

    public void setCodeRepresentant(String codeRepresentant) {
        CodeRepresentant = codeRepresentant;
    }

    public String getCodeForceVente() {
        return CodeForceVente;
    }

    public void setCodeForceVente(String codeForceVente) {
        CodeForceVente = codeForceVente;
    }

    public int getClassic() {
        return Classic;
    }

    public void setClassic(int classic) {
        Classic = classic;
    }

    public int getPromo() {
        return Promo;
    }

    public void setPromo(int promo) {
        Promo = promo;
    }

    public int getComptoir() {
        return Comptoir;
    }

    public void setComptoir(int comptoir) {
        Comptoir = comptoir;
    }

    public int getRemiseQuantite() {
        return RemiseQuantite;
    }

    public void setRemiseQuantite(int remiseQuantite) {
        RemiseQuantite = remiseQuantite;
    }

    public String getTypeRemiseChoisie() {
        return TypeRemiseChoisie;
    }

    public void setTypeRemiseChoisie(String typeRemiseChoisie) {
        TypeRemiseChoisie = typeRemiseChoisie;
    }

    public double getTotalHT() {
        return TotalHT;
    }

    public void setTotalHT(double totalHT) {
        TotalHT = totalHT;
    }

    public double getTotalRemise() {
        return TotalRemise;
    }

    public void setTotalRemise(double totalRemise) {
        TotalRemise = totalRemise;
    }

    public double getTotalNetHT() {
        return TotalNetHT;
    }

    public void setTotalNetHT(double totalNetHT) {
        TotalNetHT = totalNetHT;
    }

    public double getTotalFodec() {
        return TotalFodec;
    }

    public void setTotalFodec(double totalFodec) {
        TotalFodec = totalFodec;
    }

    public double getTotalTVA() {
        return TotalTVA;
    }

    public void setTotalTVA(double totalTVA) {
        TotalTVA = totalTVA;
    }

    public double getTotalTTC() {
        return TotalTTC;
    }

    public void setTotalTTC(double totalTTC) {
        TotalTTC = totalTTC;
    }

    public double getTotalNote() {
        return TotalNote;
    }

    public void setTotalNote(double totalNote) {
        TotalNote = totalNote;
    }

    public double getTotalVolume() {
        return TotalVolume;
    }

    public void setTotalVolume(double totalVolume) {
        TotalVolume = totalVolume;
    }

    public double getTotalPoids() {
        return TotalPoids;
    }

    public void setTotalPoids(double totalPoids) {
        TotalPoids = totalPoids;
    }

    public double getTotalColisage() {
        return TotalColisage;
    }

    public void setTotalColisage(double totalColisage) {
        TotalColisage = totalColisage;
    }

    public String getNumeroEtat() {
        return NumeroEtat;
    }

    public void setNumeroEtat(String numeroEtat) {
        NumeroEtat = numeroEtat;
    }

    public String getNomUtilisateur() {
        return NomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        NomUtilisateur = nomUtilisateur;
    }

    public Date getDateCreation() {
        return DateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        DateCreation = dateCreation;
    }

    public Date getHeureCreation() {
        return HeureCreation;
    }

    public void setHeureCreation(Date heureCreation) {
        HeureCreation = heureCreation;
    }

    public String getObservation() {
        return Observation;
    }

    public void setObservation(String observation) {
        Observation = observation;
    }

    public String getNoteInterne() {
        return NoteInterne;
    }

    public void setNoteInterne(String noteInterne) {
        NoteInterne = noteInterne;
    }

    public String getCodeVoiture() {
        return CodeVoiture;
    }

    public void setCodeVoiture(String codeVoiture) {
        CodeVoiture = codeVoiture;
    }

    public String getCodeChauffeur() {
        return CodeChauffeur;
    }

    public void setCodeChauffeur(String codeChauffeur) {
        CodeChauffeur = codeChauffeur;
    }

    public String getLibelleMission() {
        return LibelleMission;
    }

    public void setLibelleMission(String libelleMission) {
        LibelleMission = libelleMission;
    }

    public String getNomValidateur() {
        return NomValidateur;
    }

    public void setNomValidateur(String nomValidateur) {
        NomValidateur = nomValidateur;
    }

    public String getDateValidation() {
        return DateValidation;
    }

    public void setDateValidation(String dateValidation) {
        DateValidation = dateValidation;
    }

    public String getNomAnnulation() {
        return NomAnnulation;
    }

    public void setNomAnnulation(String nomAnnulation) {
        NomAnnulation = nomAnnulation;
    }

    public String getDateAnnulation() {
        return DateAnnulation;
    }

    public void setDateAnnulation(String dateAnnulation) {
        DateAnnulation = dateAnnulation;
    }

    public int getNbImpression() {
        return NbImpression;
    }

    public void setNbImpression(int nbImpression) {
        NbImpression = nbImpression;
    }

    public String getNumeroMission() {
        return NumeroMission;
    }

    public void setNumeroMission(String numeroMission) {
        NumeroMission = numeroMission;
    }

    public int getPrixUsine() {
        return PrixUsine;
    }

    public void setPrixUsine(int prixUsine) {
        PrixUsine = prixUsine;
    }

    public String getUtilisateurIntervenant() {
        return UtilisateurIntervenant;
    }

    public void setUtilisateurIntervenant(String utilisateurIntervenant) {
        UtilisateurIntervenant = utilisateurIntervenant;
    }


    public ArrayList<LigneBonGratuite> getList_ligne_bon_gratuite() {
        return list_ligne_bon_gratuite;
    }

    public void setList_ligne_bon_gratuite(ArrayList<LigneBonGratuite> list_ligne_bon_gratuite) {
        this.list_ligne_bon_gratuite = list_ligne_bon_gratuite;
    }

    @Override
    public String toString() {
        return "BonGratuiteVente{" +
                "NumeroBonGratuiteVente='" + NumeroBonGratuiteVente + '\'' +
                ", DateBonGratuiteVente=" + DateBonGratuiteVente +
                ", ReferenceBonLivraisonVente='" + ReferenceBonLivraisonVente + '\'' +
                ", CodeDepot='" + CodeDepot + '\'' +
                ", CodeClient='" + CodeClient + '\'' +
                ", RaisonSociale='" + RaisonSociale + '\'' +
                ", MartriculeFiscale='" + MartriculeFiscale + '\'' +
                ", Adresse1='" + Adresse1 + '\'' +
                ", CodeModeReglement='" + CodeModeReglement + '\'' +
                ", NumeroExonoration='" + NumeroExonoration + '\'' +
                ", DateDebutExonoration=" + DateDebutExonoration +
                ", DateFinExonoration=" + DateFinExonoration +
                ", NumeroExportation='" + NumeroExportation + '\'' +
                ", CodeDevise='" + CodeDevise + '\'' +
                ", CoursDevise='" + CoursDevise + '\'' +
                ", NumeroFactureVente='" + NumeroFactureVente + '\'' +
                ", MontantRecu=" + MontantRecu +
                ", MontantRendu=" + MontantRendu +
                ", CodeRepresentant='" + CodeRepresentant + '\'' +
                ", CodeForceVente='" + CodeForceVente + '\'' +
                ", Classic=" + Classic +
                ", Promo=" + Promo +
                ", Comptoir=" + Comptoir +
                ", RemiseQuantite=" + RemiseQuantite +
                ", TypeRemiseChoisie='" + TypeRemiseChoisie + '\'' +
                ", TotalHT=" + TotalHT +
                ", TotalRemise=" + TotalRemise +
                ", TotalNetHT=" + TotalNetHT +
                ", TotalFodec=" + TotalFodec +
                ", TotalTVA=" + TotalTVA +
                ", TotalTTC=" + TotalTTC +
                ", TotalNote=" + TotalNote +
                ", TotalVolume=" + TotalVolume +
                ", TotalPoids=" + TotalPoids +
                ", TotalColisage=" + TotalColisage +
                ", NumeroEtat='" + NumeroEtat + '\'' +
                ", NomUtilisateur='" + NomUtilisateur + '\'' +
                ", DateCreation=" + DateCreation +
                ", HeureCreation=" + HeureCreation +
                ", Observation='" + Observation + '\'' +
                ", NoteInterne='" + NoteInterne + '\'' +
                ", CodeVoiture='" + CodeVoiture + '\'' +
                ", CodeChauffeur='" + CodeChauffeur + '\'' +
                ", LibelleMission='" + LibelleMission + '\'' +
                ", NomValidateur='" + NomValidateur + '\'' +
                ", DateValidation='" + DateValidation + '\'' +
                ", NomAnnulation='" + NomAnnulation + '\'' +
                ", DateAnnulation='" + DateAnnulation + '\'' +
                ", NbImpression=" + NbImpression +
                ", NumeroMission='" + NumeroMission + '\'' +
                ", PrixUsine=" + PrixUsine +
                ", UtilisateurIntervenant='" + UtilisateurIntervenant + '\'' +
                '}';
    }
}
