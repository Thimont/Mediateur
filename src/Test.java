import organisation.Inscription;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) throws SQLException {
        Mediateur mediateur = new Mediateur();
        mediateur.connexionExcel();
        mediateur.connexionOracle();
        System.out.println("Nombre d'etudiants dont le pays n'est pas la France : " + mediateur.getEtudiantsNonFrance());
        int[] nbCours = mediateur.getNbCoursByType();
        System.out.println("Nombre de CM : " + nbCours[0]);
        System.out.println("Nombre de TD : " + nbCours[1]);
        System.out.println("Nombre de TP : " + nbCours[2]);
        HashMap<String, Inscription> meilleuresNotes = mediateur.getMeilleureNoteCoursParType();
        System.out.println("Meilleures notes :");
        for (Map.Entry<String, Inscription> entry1 : meilleuresNotes.entrySet()) {
            System.out.println(entry1.getKey() + " : " + entry1.getValue().getNote());
        }
    }
}
