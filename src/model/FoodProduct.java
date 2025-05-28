package model;

import java.time.LocalDate;
public class FoodProduct extends Product{
    private LocalDate date_peremption;
    FoodProduct(String nom, int quantité, double prix, int seuil, LocalDate date_peremption){
        super(nom, quantité, prix, seuil);
        this.date_peremption= date_peremption;
    }
    public LocalDate getDate_peremption()

    {
        return date_peremption;
    };

    public void setDate_peremption(LocalDate date_peremption) {
        this.date_peremption = date_peremption;
    }
}
