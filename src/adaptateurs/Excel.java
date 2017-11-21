package adaptateurs;

import organisation.Etudiant;

import java.sql.*;
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
            this.conn = DriverManager.getConnection("jdbc:odbc:\\Z:\\ID\\TD1 - Médiateur\\Sources de données\\source1.xls", "", "");
        } catch (SQLException ex) {
            System.err.println("Excel Erreur de connexion à la base de données.");
        }
    }

    public void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Excel Erreur de deconnexion à la base de données.");
        }
    }

    //méthode pour récupérer tous les étudiants dont le pays de provenance n'est pas la france
    public List<Etudiant> getEtudiantNonProvenanceFrance() {
        List<Etudiant> etudiants = null;
        try {
            String sql = "select * from [2006$]";
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                if(rs.getString("Statut")=="etudiant") {
                    etudiants.add(new Etudiant(rs.getInt("ID"),rs.getString("Nom"), rs.getString("Prenom"),
                            rs.getString("Statut"), rs.getString("FormationPrecedente"), "",2006, 0, ""));
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return etudiants;
    }

    //afficher le nombre de cours par Type (CM, TD, TP)

    //Afficher la note maximale des cours par Type(CM, TD, TP)
}

