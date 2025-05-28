package model;

public class ElectrocicProduct extends  Product{
    private String nom;
    private double garantie;

    ElectrocicProduct(String nom, int quantité, double prix) {
        super(nom, quantité, prix);
    }
}
