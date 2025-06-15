package model;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Admin extends User {

    public Admin(String login, String motDePasse) {
        super(login, motDePasse);
    }


    @Override
    public void afficherMenu(InterfaceConsole ui) {
        int choix;

        do {
            afficherMenuAdmin();
            choix = ui.demanderChoix();
        } while (executerchoix(ui, choix));
    }

    private void afficherMenuAdmin() {
        System.out.println("\n--- Menu Admin ---");
        System.out.println("1. Ajouter un produit");
        System.out.println("2. Modifier un produit");
        System.out.println("3. Supprimer un produit");
        System.out.println("4. Rechercher un produit");
        System.out.println("5. Afficher tous les produits alimentaires");
        System.out.println("6. Afficher tous les produits électroniques");
        System.out.println("7. Afficher un produit sous le seuil critique");
        System.out.println("8. Sauvegarder le/les produit/s alimentaire/s ajouté/s ou modifié/s");
        System.out.println("9. Sauvegarder le/les produit/s électronique/s ajouté/s ou modifié/s");
        System.out.println("10. Déconnexion");
        System.out.println("0. Quitter");
        System.out.print("Choix : ");
    }

    private boolean executerchoix(InterfaceConsole ui, int choix) {
     Map<Integer, Consumer<InterfaceConsole>> actions = new HashMap<>();
     actions.put(1, this::ajouterProduit);
     actions.put(2, this::modifierProduit);
     actions.put(3, this:: supprimeProduit);
     actions.put(4, this::rechercherProduit);
     actions.put(5,this::afficherFoodProduct);
     actions.put(6, this::afficherElectronicProduct);
     actions.put(7,this::afficherProduitSousSeuil);
     actions.put(8, this::sauvegarderFoodStock);
     actions.put(9, this::sauvegarderElectronicStock);
           if(choix == 10) {
                System.out.println("Déconnexion...");
                return false;
            }
            else if(choix==0){
                System.out.println("Au revoir! A bientôt!");
                System.exit(0);
            }else{
                Consumer<InterfaceConsole> action = actions.get(choix);
                if(action!=null){
                    action.accept(ui);
                }else{
                    System.out.println("choix Invalide !");
                }
           }
        return true;
    }
    private void ajouterProduit(InterfaceConsole ui) {
        ui.ajoutProduit();
    }

    private void modifierProduit(InterfaceConsole ui) {
        ui.modificationProduit();
    }

    private void supprimeProduit(InterfaceConsole ui) {
        ui.suppressionProduit(this);
    }
    private void rechercherProduit(InterfaceConsole ui) {
        ui.rechercheProduit();
    }
    private void afficherFoodProduct(InterfaceConsole ui) {
        ui.afficherTousFoodProduct();
    }
    private void afficherElectronicProduct(InterfaceConsole ui) {
        ui.afficherTousElectronicProduct();
    }
    private void afficherProduitSousSeuil(InterfaceConsole ui) {
        ui.afficherProduitSousSeuil();
    }
    private void sauvegarderFoodStock(InterfaceConsole ui) {
        ui.sauvegardeFoodStock();
    }
    private void sauvegarderElectronicStock(InterfaceConsole ui) {
        ui.sauvegardeElectronicStock();

    }
    @Override
    public String getRole() {
        return "Admin";
    }
}

