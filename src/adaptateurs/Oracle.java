package adaptateurs;

import organisation.Cours;
import organisation.Etudiant;
import organisation.Inscription;

import java.sql.*;
import java.util.Date;
import java.util.HashMap;


public class Oracle {
    private Connection conn;

    public Oracle() {
        super();
    }

    public void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:xe", "flthiebl", "flthiebl");
//"jdbc:oracle:thin:@172.19.255.3:1521:MIAGE"

        } catch (SQLException ex) {
            System.err.println("Erreur de connexion à la base de données.");
        }
    }

    public void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Erreur de deconnexion à la base de données.");
        }
    }

    public HashMap<String, Etudiant> getEtudiants(){
        HashMap<String, Etudiant> etudiants = new HashMap<String, Etudiant>();

        try {
            Statement stmt;
            ResultSet rs;
            String query;

            stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            query = "SELECT * from ETUDIANT order by ID_ETUDIANT";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id_etudiant = rs.getInt("ID_ETUDIANT");
                String nom = rs.getString("NOM");
                String prenom = rs.getString("PRENOM");
                String provenance = rs.getString("PROVENANCE");
                String formation_precedente = rs.getString("FORMATION_PRECEDENTE");
                String pays_formation_precedente = rs.getString("PAYS_FORMATION_PRECEDENTE");
                int annee_debut = rs.getInt("ANNEE_DEBUT");
                //TODO conversion de la date de naissance en age
                Date date_naissance = rs.getDate("DATENAISSANCE");
                String niveau_insertion = rs.getString("NIVEAU_INSCRIPTION");
                etudiants.put(Integer.toString(id_etudiant),
                        new Etudiant(id_etudiant,
                                nom,
                                prenom,
                                provenance,
                                formation_precedente,
                                pays_formation_precedente,
                                annee_debut,
                                0,
                                niveau_insertion)
                );
            }
            return etudiants;
        } catch (SQLException e) {
            System.err.println("Erreur de requete");
        }

        return etudiants;
    }

    public HashMap<String, Cours> getCours() {
        HashMap<String, Cours> cours = new HashMap<String, Cours>();

        try {
            Statement stmt;
            ResultSet rs;
            String query;

            stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            query = "SELECT * from COURS order by NUMCOURS";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id_cours = rs.getInt("NUMCOURS");
                String libelle = rs.getString("LIBELE");
                String type = rs.getString("TYPE");
                String niveau = rs.getString("NIVEAU");
                int heures = 0;
                cours.put(Integer.toString(id_cours),
                        new Cours(id_cours,
                                libelle,
                                type,
                                niveau,
                                0)
                );
            }
            return cours;
        } catch (SQLException e) {
            System.err.println("Erreur de requete");
        }

        return cours;
    }

    public HashMap<String, Inscription> getNotes() {
        HashMap<String, Inscription> notes = new HashMap<String, Inscription>();

        try {
            Statement stmt;
            ResultSet rs;
            String query;

            stmt = this.conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
            query = "SELECT * FROM INSCRIPTION order by NUMET, NUMCOURS, ANNEE";

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id_etudiant = rs.getInt("NUMET");
                int id_cours = rs.getInt("NUMCOURS");
                String annee = rs.getString("ANNEE");
                int note = rs.getInt("NOTE_COURS");
                String id_note = Integer.toString(id_cours+id_etudiant) + annee;
                notes.put(id_note,
                        new Inscription(id_etudiant,
                                id_cours,
                                annee,
                                note)
                );
            }
            return notes;
        } catch (SQLException e) {
            System.err.println("Erreur de requete");
        }

        return notes;
    }
}

