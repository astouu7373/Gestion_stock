package model;

import java.time.LocalDate;

public class FoodProduct extends Product{
    private LocalDate datePeremption;
    FoodProduct(String nom, int quantité, double prix, int seuil, LocalDate datePeremption){
        super(nom, quantité, prix, seuil);
        this.datePeremption= datePeremption;
    }
    public LocalDate getDatePeremption() {
        return datePeremption;
    };

    public void setDatePeremption(LocalDate datePeremption) {
        this.datePeremption = datePeremption;
    }
    @Override
    public String toString() {
        return super.toString() + ", Date de péremption: " + datePeremption;
    }
}
