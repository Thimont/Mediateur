package organisation;

public class Enseigne {
    private int ID_Enseignant;
    private int ID_Cours;
    private int annee;
    private int heures;

    public Enseigne(int ID_Enseignant, int ID_Cours, int annee, int heures) {
        this.ID_Enseignant = ID_Enseignant;
        this.ID_Cours = ID_Cours;
        this.annee = annee;
        this.heures = heures;
    }

    public int getID_Enseignant() {
        return ID_Enseignant;
    }

    public void setID_Enseignant(int ID_Enseignant) {
        this.ID_Enseignant = ID_Enseignant;
    }

    public int getID_Cours() {
        return ID_Cours;
    }

    public void setID_Cours(int ID_Cours) {
        this.ID_Cours = ID_Cours;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }
}
