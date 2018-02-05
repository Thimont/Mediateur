package organisation;

public class Etudiant {
    private int ID_Etudiant;
    private String nom;
    private String prenom;
    private String provenance;
    private String formationPrecedente;
    private String DateDeNaissance;
    private String Telephone;
    private String paysFormationPrecedente;
    private int anneeDebut;
    private int age;
    private String niveauInsertion;

    public Etudiant(int ID_Etudiant, String nom, String prenom, String provenance, String formationPrecedente,
                    String paysFormationPrecedente, int anneeDebut, int age, String niveauInsertion,String dateDeNaissance, String telephone) {
        this.ID_Etudiant = ID_Etudiant;
        this.nom = nom;
        this.prenom = prenom;
        this.provenance = provenance;
        this.formationPrecedente = formationPrecedente;
        this.paysFormationPrecedente = paysFormationPrecedente;
        this.anneeDebut = anneeDebut;
        this.age = age;
        this.niveauInsertion = niveauInsertion;
        this.DateDeNaissance = dateDeNaissance;
        this.Telephone = telephone;
    }

    public int getID_Etudiant() {
        return ID_Etudiant;
    }

    public void setID_Etudiant(int ID_Etudiant) {
        this.ID_Etudiant = ID_Etudiant;
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

    public String getProvenance() {
        return provenance;
    }

    public void setProvenance(String provenance) {
        this.provenance = provenance;
    }

    public String getFormationPrecedente() {
        return formationPrecedente;
    }

    public void setFormationPrecedente(String formationPrecedente) {
        this.formationPrecedente = formationPrecedente;
    }

    public String getPaysFormationPrecedente() {
        return paysFormationPrecedente;
    }

    public void setPaysFormationPrecedente(String paysFormationPrecedente) {
        this.paysFormationPrecedente = paysFormationPrecedente;
    }

    public int getAnneeDebut() {
        return anneeDebut;
    }

    public void setAnneeDebut(int anneeDebut) {
        this.anneeDebut = anneeDebut;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNiveauInsertion() {
        return niveauInsertion;
    }

    public void setNiveauInsertion(String niveauInsertion) {
        this.niveauInsertion = niveauInsertion;
    }

    public String getDateDeNaissance() {
        return DateDeNaissance;
    }

    public void setDateDeNaissance(String dateDeNaissance) {
        DateDeNaissance = dateDeNaissance;
    }

    public String getTelephone() {
        return Telephone;
    }

    public void setTelephone(String telephone) {
        Telephone = telephone;
    }
}
