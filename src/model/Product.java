package model;

public abstract class Product {
    protected String nom;
    protected int quantité;
    protected double prix;
    Product(String nom, int quantité, double prix){
        this.nom = nom;
        this.quantité = quantité;
        this.prix = prix;
    }
    public String getNom() { return nom;};
    public void setNom(String Nom){
        this.nom = Nom;
    }
    public int getQuantité(){return quantité;};
    public void setQuantité(int Quantité){
        this.quantité = Quantité;
    }
    public double getPrix(){return prix;};
    public void SetPrix( double Prix){
        this.prix = Prix;
    }

}
