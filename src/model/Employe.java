package model;

import java.util.Scanner;

public class Employe extends User {

    public Employe(String login, String motDePasse) {
        super(login, motDePasse);
    }

    @Override
    public void afficherMenu(InterfaceConsole ui) {
        Scanner scanner = new Scanner(System.in);
        int choix;

        do {
            System.out.println("\n--- Menu Employé ---");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Modifier un produit");
            System.out.println("3. Rechercher un produit");
            System.out.println("4. Afficher tous les produits");
            System.out.println("5. Afficher un produit sous le seuil critique");
            System.out.println("0. Déconnexion");

            System.out.print("Choix : ");
            choix = ui.demanderChoix();

            switch (choix) {
                case 1 -> ui.ajoutProduit();
                case 2 -> ui.modificationProduit();
                case 3 -> ui.rechercheProduit();
                case 4 -> ui.afficherTousProduits();
                case 5 -> ui.afficherProduitSousSeuil();
                case 0 -> System.out.println("Déconnexion...");
                default -> System.out.println("Choix invalide !");
            }
        } while (choix != 0);
    }

    @Override
    public String getRole() {
        return "Employe";
    }
}
