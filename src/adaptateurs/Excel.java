package adaptateurs;

import organisation.Cours;
import organisation.Etudiant;
import organisation.Inscription;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Excel {
    private Connection conn;

    public Excel() {
        super();
    }

    public void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
//            this.conn = DriverManager.getConnection("jdbc:odbc:\\D:\\source1.xls", "", "");

            String myDB = "jdbc:odbc:Driver={Microsoft Excel Driver (*.xls)};DBQ=D:/source1.xls";
            this.conn = DriverManager.getConnection(myDB);
        } catch (SQLException ex) {
            System.err.println(ex.toString());
            System.err.println("Excel Erreur de connexion à la base de données.");
        }
    }

    public void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Excel Erreur de deconnexion é la base de données.");
        }
    }

    public HashMap<String, Etudiant> getEtudiants() {
        HashMap<String, Etudiant> etudiants = new HashMap<String, Etudiant>();
        try {
            String sql = "select * from ["+2006+"$]";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next() && rs.getString("Statut").equals("etudiant")) {
                int id_etudiant = rs.getInt("ID");
                String nom = rs.getString("Nom");
                String prenom = rs.getString("Prenom");
                String provenance = rs.getString("Provenance");
                String formationPrecedente = rs.getString("FormationPrecedente");
                String niveauInsertion = rs.getString("NiveauInsertion");
                if(!etudiants.containsKey(Integer.toString(id_etudiant))) {
                    etudiants.put(Integer.toString(id_etudiant), new Etudiant(id_etudiant, nom, prenom, provenance, formationPrecedente, "", 0, 0, niveauInsertion));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        return etudiants;
    }

    //afficher le nombre de cours par Type (CM, TD, TP)
    public HashMap<String, Cours> getCours() {
        HashMap<String, Cours> cours = new HashMap<String, Cours>();
        try {
            String sql = "select * from ["+2006+"$]";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                int id_cours = rs.getInt("ID_Cours");
                String libelle_cours = rs.getString("Libelle_Cours");
                String type_cours = rs.getString("Type_Cours");
                String niveau_cours = rs.getString("Niveau_Cours");
                if(!cours.containsKey(Integer.toString(id_cours))) {
                    cours.put(Integer.toString(id_cours), new Cours(id_cours, libelle_cours, type_cours, niveau_cours, 0));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return cours;
    }

    //récuperer toutes les notes
    public HashMap<String, Inscription> getNotes() {
        HashMap<String, Inscription> inscriptions = new HashMap<String, Inscription>(); //contient toutes les notes
        try {
            String sql = "select * from ["+2006+"$]";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next() && rs.getString("Statut").equals("etudiant")) {
                int id_etudiant = rs.getInt("ID");
                int id_cours = rs.getInt("ID_Cours");
                String annee = rs.getString("Niveau_Cours");
                int note = rs.getInt("Note");
                if(!(inscriptions.containsKey(Integer.toString(id_cours)+Integer.toString(id_etudiant)+annee))) {
                    inscriptions.put(Integer.toString(id_cours)+Integer.toString(id_etudiant)+annee, new Inscription(id_etudiant, id_cours, annee, note));
                }
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return inscriptions;
    }


}

