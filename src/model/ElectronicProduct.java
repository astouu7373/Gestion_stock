package model;

public class ElectronicProduct extends  Product{
    private double garantie;

    ElectronicProduct(String nom, int quantite, double prix, int seuil, double garantie) {
        super(nom, quantite, prix, seuil);
        this.garantie = garantie;
    }

    public double getGarantie() {

        return garantie;
    }

    public void setGarantie(double garantie) {

        this.garantie = garantie;
    }
    @Override
    public String toString() {
        return super.toString() + ", Garantie (mois): " + garantie;
    }

}
