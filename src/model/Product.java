package model;

public abstract class Product {
    String nom;
    int quantite;
    double prix;
    int seuil ;
    Product(String nom, int quantite, double prix, int seuil){
        this.nom = nom;
        this.quantite = quantite;
        this.prix = prix;
        this.seuil = seuil;
    }
    public String getNom() { 
        return nom;
    };
    public void setNom(String nom){
        this.nom = nom;
    }
    public int getQuantite(){
        return quantite;
    };
    public void setQuantite(int quantite){
        this.quantite = quantite;
    }
    public double getPrix(){
        return prix;
    }


    public int getSeuil() {

        return seuil;
    }

    public void setSeuil(int seuil) {

        this.seuil = seuil;
    }

    public void setPrix(double prix) {

        this.prix = prix;
    }
    @Override
    public String toString() {
        return "Produit: " + nom + ", Prix: " + prix + ", Quantité: " + quantite + ", Seuil: " + seuil;
    }

}