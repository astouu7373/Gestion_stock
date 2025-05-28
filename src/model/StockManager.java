package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockManager {
    private List<Product> produits ;

    public StockManager (){
        produits = new ArrayList<>();

    }

    public List<Product> afficherProduit(){
        return produits ;
    }

    public void ajouterProduit(Product produit){
        produits.add(produit);
    }

    public void supprimerProduit(Product produit) {
        produits.remove(produit);
    }

    public Product rechercherProduit(String nom){
        for (Product p : produits) {
            if (Objects.equals(p.getNom(), nom)) {
                return p;
            }

        }
        return null;
    }

    public Product produitSousSeuilCritique(){
        for (Product p : produits) {
            if ( p.getQuantité() <= p.getSeuil() ){
                return p ;
            }
        }
        return null ;
    }
}
