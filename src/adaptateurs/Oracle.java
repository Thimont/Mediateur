package adaptateurs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Oracle {
    private Connection conn;

    public Oracle() {
        super();
    }

    private void connexion() throws SQLException, ClassNotFoundException {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.err.println("Erreur de chargement du driver.");
        }

        try {
            this.conn = DriverManager.getConnection("jdbc:oracle:thin:@miage03.dmiage.u-paris10.fr:1521:miage", "videlcro", "miage");
//"jdbc:oracle:thin:@172.19.255.3:1521:MIAGE"

        } catch (SQLException ex) {
            System.err.println("Erreur de connexion � la base de donn�es.");
        }
    }

    private void deconnexion() {
        try {
            this.conn.close();
        } catch (SQLException ex) {
            System.err.println("Erreur de deconnexion � la base de donn�es.");
        }
    }
}
