package organisation;

public class Inscription {
    private int ID_Etudiant;
    private int ID_Cours;
    private int annee;
    private int note;

    public Inscription(int ID_Etudiant, int ID_Cours, int annee, int note) {
        this.ID_Etudiant = ID_Etudiant;
        this.ID_Cours = ID_Cours;
        this.annee = annee;
        this.note = note;
    }

    public int getID_Etudiant() {
        return ID_Etudiant;
    }

    public void setID_Etudiant(int ID_Etudiant) {
        this.ID_Etudiant = ID_Etudiant;
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

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }
}
