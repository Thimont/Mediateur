package organisation;

public class Enseignant {
    private String ID_Enseignant;
    private String nom;
    private String prenom;
    private String adresseMail;

    public Enseignant(String ID_Enseignant, String nom, String prenom, String adresseMail) {
        this.ID_Enseignant = ID_Enseignant;
        this.nom = nom;
        this.prenom = prenom;
        this.adresseMail = adresseMail;
    }

    public String getID_Enseignant() {
        return ID_Enseignant;
    }

    public void setID_Enseignant(String ID_Enseignant) {
        this.ID_Enseignant = ID_Enseignant;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresseMail() {
        return adresseMail;
    }

    public void setAdresseMail(String adresseMail) {
        this.adresseMail = adresseMail;
    }
}
