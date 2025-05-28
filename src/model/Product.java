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
    public String getNom() { return nom;};
    public void setNom(String nom){
        this.nom = nom;
    }
    public int getQuantité(){return quantité;};
    public void setQuantité(int quantité){
        this.quantité = quantité;
    }
    public double getPrix(){return prix;};
    public void SetPrix( double prix){
        this.prix = prix;
    }

    public int getSeuil() {
        return seuil;
    }

    public void setSeuil(int seuil) {
        this.seuil = seuil;
    }
}