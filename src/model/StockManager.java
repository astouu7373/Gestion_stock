package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockManager {
    private List<Product> produits ;

    public StockManager (){
        produits = new ArrayList<>();

    }

    public List<Product> afficherProduits(){
        return produits ;
    }

    public boolean ajouterProduit(Product produit){
        for (Product p : produits) {
            if(p.getNom().equalsIgnoreCase(produit.getNom())){
                return false ;
            }
        }
        produits.add(produit);
        return true;
    }

    public boolean supprimerUnProduit(String nom , User u ) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getNom().equalsIgnoreCase(nom) && u.getRole().equals("Admin") ) {
                produits.remove(i);
                return true; // le produit est supprimé
            }
        }
        return false; // le produit est introuvable
    }


    public Product rechercherUnProduit(String nom){
        for (Product p : produits) {
            if (Objects.equals(p.getNom(), nom)) {
                return p;
            }

        }
        return null;
    }

    public Product produitSousSeuilCritique(){
        for (Product p : produits) {
            if ( p.getQuantite() <= p.getSeuil() ){
                return p ;
            }
        }
        return null ;
    }

    public boolean modifierUnProduit(String nom, String newNom, double newPrix, int newQuantite, int newSeuil , LocalDate newDatePeremption , Integer newGarantie) {
        Product produit = rechercherUnProduit(nom);
         Product p2 = null ;
         boolean nomTrouve = false ;

            // Vérifier qu'aucun autre produit n'a déjà le nouveau nom
            for (Product p : produits) {
                if (p.equals(produit) ) {
                    p2 = produit ;
                }
                if (!p.equals(produit) && p.getNom().equalsIgnoreCase(newNom)) {
                    nomTrouve = true;
                }
            }

            if ( p2 != null && !nomTrouve){
                produit.setNom(newNom);
                produit.setPrix(newPrix);
                produit.setQuantite(newQuantite);
                produit.setSeuil(newSeuil);

                if (produit instanceof FoodProduct && newDatePeremption != null) {
                    ((FoodProduct) produit).setDatePeremption(newDatePeremption);
                }

                if (produit instanceof ElectronicProduct && newGarantie != null) {
                    ((ElectronicProduct) produit).setGarantie(newGarantie);
                }
                return true;
            }
        return false;
    }

}
