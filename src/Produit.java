public class Produit {
    String Nom;
    String Quantité;
    String Prix;
    Produit(String nom, String quantité, String prix){
        this.Nom = nom;
        this.Quantité = quantité;
        this.Prix = prix;
    }
    public String getNom() { return Nom;};
    public void setNom(String Nom){
       this.Nom = Nom;
    }
    public String getQuantité(){return Quantité;};
    public void setQuantité(String Quantité){
        this.Quantité = Quantité;
    }
    public String getPrix(){return Prix;};
    public void SetPrix( String Prix){
        this.Prix = Prix;
    }
}
