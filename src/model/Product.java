package model;

public abstract class Product {
    protected String nom;
    protected int quantité;
    protected double prix;
    protected double seuil;
    Product(String nom, int quantité, double prix, double seuil){
        this.nom = nom;
        this.quantité = quantité;
        this.prix = prix;
        this.seuil = seuil;
    }
    public String getNom() { return nom;};
    public void setNom(String nom){
        this.nom = nom;
    }
    public int getQuantité(){return quantité;};
    public void setQuantité(int Quantité){
        this.quantité = quantité;
    }

    public double getPrix(){return prix;};
    public void SetPrix( double prix){
        this.prix = prix;
    }

    public double getSeuil() {
        return seuil;
    }

    public void setSeuil(double seuil) {
        this.seuil = seuil;
    }
}
