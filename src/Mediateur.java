import adaptateurs.Excel;
import adaptateurs.Oracle;
import adaptateurs.XML;
import organisation.Cours;
import organisation.Etudiant;
import organisation.Inscription;

import java.util.*;

public class Mediateur {
    Excel excel = new Excel();
    Oracle oracle = new Oracle();
    XML xml = new XML();

    public Mediateur() {
    }

    public void connexionExcel() {
        try {
            this.excel.connexion();
        } catch (Exception e) {
            System.err.println("Connexion impossible");
        }
    }
    public void connexionOracle() {
        Oracle oracle = new Oracle();
        try {
            oracle.connexion();
        } catch (Exception e) {
            System.err.println("Connexion impossible");
        }
    }

    public int getEtudiantsNonFrance() {
        HashMap<String, Etudiant> etudiants = this.excel.getEtudiants();
        int nbEtudiantsEtrangers = 0;
        for (Map.Entry<String, Etudiant> entry : etudiants.entrySet()) {
            String provenance = entry.getValue().getProvenance();
            if(!provenance.equals("France")) {
                nbEtudiantsEtrangers ++;
            }
        }

        return nbEtudiantsEtrangers;
    }

    public int[] getNbCoursByType() {
        HashMap<String, Cours> cours = this.excel.getCours();
        int nb_cm = 0;
        int nb_td = 0;
        int nb_tp = 0;
        for(Map.Entry<String, Cours> entry : cours.entrySet()) {
            String type = entry.getValue().getType();
            if (type.equals("CM"))
                nb_cm++;
            else if (type.equals("TD"))
                nb_td++;
            else if (type.equals("TP"))
                nb_tp++;
        }
        int[] nbCours = new int[3];
        nbCours[0] = nb_cm;
        nbCours[1] = nb_td;
        nbCours[2] = nb_tp;
        return nbCours;
    }

    public HashMap<String, Inscription> getMeilleureNoteCoursParType() {
        HashMap<String, Inscription> inscriptions = this.excel.getNotes();
        HashMap<String, Cours> cours = this.excel.getCours();
        HashMap<String, Inscription> meilleuresnotes = new HashMap<String, Inscription>();

        Inscription meilleureinscription = null;

        for (Map.Entry<String, Cours> entry : cours.entrySet()) {
            int id_cours = entry.getValue().getID_Cours();
            String nomCours = entry.getValue().getLibelle() + "(" + entry.getValue().getType() + ")";
            int meilleureNote = -1;
            for (Map.Entry<String, Inscription> entry1 : inscriptions.entrySet()) {
                if (entry1.getValue().getID_Cours() == id_cours) {
                    int note = entry1.getValue().getNote();
                    if (note > meilleureNote) {
                        meilleureNote = note;
                        meilleureinscription = entry1.getValue();
                    }
                }
            }
            meilleuresnotes.put(nomCours, meilleureinscription);
        }
        return meilleuresnotes;
    }
}
