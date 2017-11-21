import adaptateurs.Excel;
import adaptateurs.Oracle;
import adaptateurs.XML;

public class Mediateur {

    public Mediateur() {
    }

    public void connexionExcel() {
        Excel excel = new Excel();
        try {
            excel.connexion();
        } catch (Exception e) {
            System.err.println("Connexion impossible");
        }
    }
}
