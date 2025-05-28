package model;

public class ElectrocicProduct extends  Product{
    private double garantie;

    ElectrocicProduct(String nom, int quantité, double prix, double garantie) {
        super(nom, quantité, prix);
        this.garantie = garantie;
    }

    public double getGarantie() {
        return garantie;
    }
    public void setGarantie(double garantie){
        this.garantie = garantie;
    }
}
