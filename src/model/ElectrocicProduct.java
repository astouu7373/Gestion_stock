package model;

public class ElectrocicProduct extends  Product{
    private int garantie;

    ElectrocicProduct(String nom, int quantité, double prix, double seuil, int garantie) {
        super(nom, quantité, prix,seuil);
        this.garantie = garantie;
    }

    public int getGarantie() {
        return garantie;
    }
    public void setGarantie(int garantie){
        this.garantie = garantie;
    }
}
