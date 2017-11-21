package organisation;

public class Cours {
    private int ID_Cours;
    private String libelle;
    private String type;
    private String niveau;
    private int heures;

    public Cours(int ID_Cours, String libelle, String type, String niveau, int heures) {
        this.ID_Cours = ID_Cours;
        this.libelle = libelle;
        this.type = type;
        this.niveau = niveau;
        this.heures = heures;
    }

    public int getID_Cours() {
        return ID_Cours;
    }

    public void setID_Cours(int ID_Cours) {
        this.ID_Cours = ID_Cours;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNiveau() {
        return niveau;
    }

    public void setNiveau(String niveau) {
        this.niveau = niveau;
    }

    public int getHeures() {
        return heures;
    }

    public void setHeures(int heures) {
        this.heures = heures;
    }
}
