package model;

public class Product {
    String nom;
    int quantité;
    double prix;
    int seuil ;
    Product(String nom, int quantité, double prix){
        this.nom = nom;
        this.quantité = quantité;
        this.prix = prix;
    }
    public String getNom() { return Nom;};
    public void setNom(String Nom){
        this.Nom = Nom;
    }
    public int getQuantité(){return Quantité;};
    public void setQuantité(int Quantité){
        this.Quantité = Quantité;
    }
    public double getPrix(){return Prix;};
    public void SetPrix( double Prix){
        this.Prix = Prix;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }
}
