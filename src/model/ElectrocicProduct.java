package model;

public class ElectrocicProduct extends  Product{
    private double garantie;

    ElectrocicProduct(String nom, int quantite, double prix, int seuil, double garantie) {
        super(nom, quantite, prix, seuil);
        this.garantie = garantie;
    }

    public double getGarantie() {
        return garantie;
    }

    public void setGarantie(double garantie) {
        this.garantie = garantie;
    }
}
