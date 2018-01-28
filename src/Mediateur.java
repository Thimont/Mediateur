import adaptateurs.Excel;
import adaptateurs.Oracle;
import adaptateurs.XML;
import organisation.Cours;
import organisation.Etudiant;
import organisation.Inscription;

import java.sql.SQLException;
import java.util.*;

public class Mediateur {
    private Excel excel = new Excel();
    private Oracle oracle = new Oracle();
    XML xml = new XML();

    public Mediateur() {
    }

    public void connexionExcel() {
        try {
            this.excel.connexion();
        } catch (Exception e) {
            System.err.println("Connexion au fichier Excel impossible");
        }
    }
    public void connexionOracle() {
        try {
            this.oracle.connexion();
        } catch (Exception e) {
            System.err.println("Connexion a la base Oracle impossible");
        }
    }

    public void connexionXML() {
        try {
            this.xml.lire_XML("D:\\MIAGE\\ID\\TD1 - Médiateur\\Sources de données\\source3.xml");
        } catch (Exception e) {
            System.err.println("Lecture du fichier xml impossible");
        }
    }

    public int getEtudiantsNonFrance() {
        HashMap<String, Etudiant> etudiantsExcel = this.excel.getEtudiants();
        HashMap<String, Etudiant> etudiantsOracle = new HashMap<String, Etudiant>();

        try {
            etudiantsOracle = this.oracle.getEtudiants();
        } catch (Exception e) {
            System.err.println("Recuperation des etudiants impossible");
        }

        HashMap<String, Etudiant> etudiants = new HashMap<String, Etudiant>();

        etudiants.putAll(etudiantsExcel);
        etudiants.putAll(etudiantsOracle);

        int nbEtudiantsEtrangers = 0;
        for (Map.Entry<String, Etudiant> entry : etudiants.entrySet()) {
            String provenance = entry.getValue().getProvenance();
            if(!(provenance.equals("France") || provenance.equals("fr"))) {
                nbEtudiantsEtrangers ++;
            }
        }

        return nbEtudiantsEtrangers;
    }

    public int[] getNbCoursByType() {
        HashMap<String, Cours> coursExcel = this.excel.getCours();
        HashMap<String, Cours> coursOracle = this.oracle.getCours();
        HashMap<String, Cours> cours = new HashMap<String, Cours>();

        cours.putAll(coursExcel);
        cours.putAll(coursOracle);

        int nb_cm = 0;
        int nb_td = 0;
        int nb_tp = 0;
        //recuperation des cours excel
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
        HashMap<String, Inscription> inscriptionsExcel = this.excel.getNotes();
        HashMap<String, Cours> coursExcel = this.excel.getCours();
        HashMap<String, Inscription> inscriptionsOracle = this.oracle.getNotes();
        HashMap<String, Cours> coursOracle = this.oracle.getCours();

        HashMap<String, Inscription> inscriptions = new HashMap<String, Inscription>();
        HashMap<String, Cours> cours = new HashMap<String, Cours>();

        inscriptions.putAll(inscriptionsExcel);
        inscriptions.putAll(inscriptionsOracle);
        cours.putAll(coursExcel);
        cours.putAll(coursOracle);

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
