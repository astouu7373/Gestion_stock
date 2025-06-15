package model;

import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StockManager {
    private final List<Product> produits;

    public StockManager() {
        produits = new ArrayList<>();

    }

    /**
     * Récupère tous les produits alimentaires du stock.
     * @return une liste de produit qui sont des instances de {@code foodProduct}.
     */
    public List<Product> afficherFoodProduct() {
        List<Product> foodProduct = new ArrayList<>();
        for(Product p : produits) {
            if (p instanceof FoodProduct) {
                foodProduct.add(p);
            }
        }
        return foodProduct;
    }

    /**
     *  Récupère tous les produits électroniques du stock.
     * @return une liste de produit qui sont des instances de {@code electronicProduct}.
     */
    public List<Product> afficherElectronicProduct(){
        List<Product> electronicProduct = new ArrayList<>();
        for(Product p: produits){
            if(p instanceof ElectronicProduct){
                electronicProduct.add(p);
            }
        }
        return electronicProduct;
    }

    public List<Product> getProduits() {
        return produits;
    }

    /**
     *  Ajoute un produit à la liste des produits si le produit n'existe pas déjà
     *  2 produits sont identiques s'ils ont le même nom
      * @param produit est le produit à ajouter
     * @return {@code true} si le produit a bien été ajouté sinon {@code false}.
     */
    public boolean ajouterProduit(Product produit) {
        boolean existe = produits.stream()
                .anyMatch(p -> p.getNom().equalsIgnoreCase(produit.getNom()));
        if (existe) {
            return false;
        }
        produits.add(produit);
        return true;
    }

    /**
     * Supprimer un produit de la liste s'il existe
     * Un produit existe si son nom existe.
     * @param nom du produit à supprimer
     * @param u l'utilisateur qui doit être l'admin
     * @return {@code true} si le produit a bien été supprimé sinon {@code false}.
     */
    public boolean supprimerUnProduit(String nom, User u) {
        for (int i = 0; i < produits.size(); i++) {
            if (produits.get(i).getNom().equalsIgnoreCase(nom) && u.getRole().equals("Admin")) {
                produits.remove(i);
                return true; // le produit est supprimé.
            }
        }
        return false; // le produit est introuvable.
    }

    /**
     * Rechercher un produit dans la liste des produits
     * Un produit est recherché par son nom
     * @param nom du produit à rechercher
     * @return le produit recherché en utilisant API stream
     */

    public Product rechercherUnProduit(String nom) {
        return produits.stream()
                .filter(p -> Objects.equals(p.getNom(), nom))
                .findFirst()
                .orElse(null);

    }

    /**
     * Recupère la liste des produits sous seuil
     * @return les produits qui sont sous seuil.
     */
    public List<Product> produitSousSeuilCritique() {
        return produits.stream()
                .filter(p -> p.getQuantite() <= p.getSeuil())
                .toList();
    }

    /**
     * Verifie si le nouveau nom à donner au produit est déjà utilisé
     * @param newNom nouveau nom déjà utilisé par un autre produit autre que celui à modifier
     * @param actualProduct le produit à modifier
     * @return true si un produit utilise déjà ce nom et false dans le cas contraire.
     */
    private boolean nameUsed(String newNom,  Product actualProduct){
        return produits.stream()
                .anyMatch(p -> !p.equals(actualProduct) && p.getNom().equalsIgnoreCase(newNom));
    }

    /**
     * Applique la modification sur le produit
     * @param p le produit à modifier
     * @param newNom son nouveau nom
     * @param newPrix son nouveau prix
     * @param newQuantite sa nouvelle quantité
     * @param newSeuil son nouveau seuil
     * @param newDatePeremption sa nouvelle date de peremption si produit alimentaire
     * @param newGarantie sa nouvelle garantie si produit électronique
     */
    private void modificationappliquer(Product p, String newNom, double newPrix, int newQuantite, int newSeuil, LocalDate newDatePeremption, Integer newGarantie){
        p.setNom(newNom);
        p.setPrix(newPrix);
        p.setQuantite(newQuantite);
        p.setSeuil(newSeuil);
        if(p instanceof FoodProduct && newDatePeremption !=null){
            ((FoodProduct)p).setDatePeremption(newDatePeremption);
        }
        if(p instanceof ElectronicProduct && newGarantie !=null){
            ((ElectronicProduct)p).setGarantie(newGarantie);
        }
    }

    /**
     *  Modifie les informations d'un produit existant
     *  Recherche le produit par son nom et applique les nouvelles valeurs
     * @param nom nom du produit à modifier
     * @param newNom son nouveau nom
     * @param newPrix son nouveau prix
     * @param newQuantite sa nouvelle quantité
     * @param newSeuil son nouveau seuil
     * @param newDatePeremption sa nouvelle date de peremption si produit alimentaire
     * @param newGarantie sa nouvelle garantie si produit électronique
     * @return True si la modification a réussie sinon false.
     */
    public boolean modifierUnProduit(String nom, String newNom, double newPrix, int newQuantite, int newSeuil, LocalDate newDatePeremption, Integer newGarantie) {
     Product produit = rechercherUnProduit(nom);
     if(produit == null || nameUsed(newNom,produit)){
         return false;
     }
     modificationappliquer(produit, newNom, newPrix, newQuantite, newSeuil, newDatePeremption, newGarantie);
        return true;

   }


    /**
     * Sauvegarde de tous les produits alimentaires ajoutés
     * @param fileNameF  dans lequel est sauvegardés les produits alimentaires.
     */
    public void sauvegardeFoodProduct(String fileNameF) {
        try (FileWriter fw = new FileWriter(fileNameF)) {
            fw.write("nom, quantite, prix, seuil, datePeremption\n");
            for (Product p : produits) {
                if (p instanceof FoodProduct fp) {
                    String ligne = fp.getNom() + "," + fp.getQuantite() + "," + fp.getPrix() + "," + fp.getSeuil() + ","+ fp.getDatePeremption();

                    fw.write(ligne + "\n");
                }
            }
            System.out.println("Sauvegarde du produit alimentaire réussie!");
        } catch (IOException ioe) {
            System.out.println("Erreur lors de la sauvegarde:" + ioe.getMessage());

        }
    }

    /**
     * Chargement de tous les produits alimentaires sauvegardés.
     * @param fileNameF dans lequel est sauvegardés les produits alimentaires
     */
    public void chargerFoodProduct(String fileNameF) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileNameF))) {
            br.readLine();
            String ligne;
            while ((ligne = br.readLine()) != null) {
                String[] parts = ligne.split(",");
                if (parts.length < 5){
                    continue;
                }
                String nom = parts[0];
                int quantite = Integer.parseInt(parts[1].trim());
                double prix = Double.parseDouble(parts[2].trim());
                int seuil = Integer.parseInt(parts[3].trim());
                LocalDate datePeremption = LocalDate.parse(parts[4].trim());
                FoodProduct fp = new FoodProduct(nom, quantite, prix, seuil, datePeremption);
                produits.add(fp);
            }
        } catch (IOException ioe) {
            System.out.println("Erreur lors du chargement!" + ioe.getMessage());

        }
    }
    /**
     * Sauvegarde de tous les produits électroniques ajoutés
     * @param fileNameE  dans lequel est sauvegardés les produits électroniques.
     */
    public void sauvegardeElectronicProduct(String fileNameE){
        try(FileWriter fw = new FileWriter(fileNameE)){
            fw.write("nom, quantité, prix,seuil, garantie\n");
            for(Product p: produits){
                if(p instanceof ElectronicProduct ep){
                    String ligne= ep.getNom()+","+ep.getQuantite()+","+ep.getPrix()+","+ep.getSeuil()+","+ep.getGarantie();
                    fw.write(ligne + "\n");
                }
            }
            System.out.println("Sauvegarde du produit électronique réussie!");
        } catch (IOException ioe){
            System.out.println("Erreur lors de la sauvegarde:"+ioe.getMessage());
        }
    }
    /**
     * Chargement de tous les produits électroniques sauvegardés.
     * @param fileNameE dans lequel est sauvegardés les produits électroniques.
     */
    public void chargerElectronicProduct(String fileNameE){
        try(BufferedReader br = new BufferedReader(new FileReader(fileNameE))){
            br.readLine();
            String ligne;
            while((ligne= br.readLine())!=null){
                String[] parts = ligne.split(",");
                if(parts.length < 5) {
                    continue;
                }
                String nom = parts[0];
                int quantite = Integer.parseInt(parts[1].trim());
                double prix = Double.parseDouble(parts[2].trim());
                int seuil = Integer.parseInt(parts[3].trim());
                double garantie = Double.parseDouble(parts[4].trim());
                ElectronicProduct ep = new ElectronicProduct(nom, quantite, prix, seuil,garantie);
                produits.add(ep);
            }
        } catch (IOException ioe){
            System.out.println("Erreur lors de la charge!"+ioe.getMessage());
        }
    }
}


