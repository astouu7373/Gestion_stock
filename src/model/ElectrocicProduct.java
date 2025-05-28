package model;

public class ElectrocicProduct extends  Product{
    private double garantie;

    ElectrocicProduct(String nom, int quantité, double prix, int seuil, double garantie) {
        super(nom, quantité, prix, seuil);
        this.garantie = garantie;
    }

    public double getGarantie() {
        return garantie;
    }

    public void setGarantie(double garantie) {
        this.garantie = garantie;
    }
}
