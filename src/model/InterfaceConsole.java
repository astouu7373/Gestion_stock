package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class InterfaceConsole {
    private final Scanner scanner;
    private final List<User> utilisateurs;
    private final StockManager stockManager;

    public InterfaceConsole() {
        scanner = new Scanner(System.in);
        utilisateurs = new ArrayList<>();
        stockManager = new StockManager();
    }

    public void demarrer() {
        System.out.println("Bienvenue dans le système de gestion de stock");

        boolean connecte = false;

        while (!connecte) {
            System.out.println("1. Se connecter");
            System.out.println("2. Créer un compte");
            System.out.print("Choix : ");
            String choix = scanner.nextLine();

            switch (choix) {
                case "1":
                    User utilisateurConnecte = connexion();
                    if (utilisateurConnecte != null) {
                        connecte = true;
                        System.out.println("Connexion réussie en tant que " + utilisateurConnecte.getLogin());
                        utilisateurConnecte.afficherMenu(this);
                    }
                    break;
                case "2":
                    creerCompte();
                    break;
                default:
                    System.out.println("Choix invalide.");
            }
        }
    }

    private User connexion() {
        System.out.print("Nom utilisateur : ");
        String login = scanner.nextLine();
        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        for (User u : utilisateurs) {
            if (u.getLogin().equals(login) && u.verifierMotDePasse(mdp)) {
                return u;
            }
        }

        System.out.println("Identifiants incorrects.");
        return null;
    }

    private void creerCompte() {
        System.out.print("Nom d'utilisateur : ");
        String login = scanner.nextLine();

        for (User u : utilisateurs) {
            if (u.getLogin().equals(login)) {
                System.out.println("Nom d'utilisateur déjà pris !");
                return;
            }
        }

        System.out.print("Mot de passe : ");
        String mdp = scanner.nextLine();

        String role = "";
        while (!"Admin".equalsIgnoreCase(role) && !"Employe".equalsIgnoreCase(role)) {
            System.out.print("Rôle (admin/employe) : ");
            role = scanner.nextLine().toLowerCase();
        }

        User nouveau = Objects.equals(role, "admin") ? new Admin(login, mdp) : new Employe(login, mdp);
        utilisateurs.add(nouveau);
        System.out.println("Compte créé avec succès !");
    }

    public int demanderChoix() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    public void ajoutProduit() {
        System.out.println(">>>>> Ajout produit <<<<<<");
        System.out.print("Nom du produit : ");
        String nom = scanner.nextLine();

        System.out.print("Prix : ");
        double prix = Double.parseDouble(scanner.nextLine());

        System.out.print("Quantité : ");
        int quantite = Integer.parseInt(scanner.nextLine());

        System.out.print("Seuil : ");
        int seuil = Integer.parseInt(scanner.nextLine());

        System.out.print("Type de produit (alimentaire/electronique) : ");
        String type = scanner.nextLine().toLowerCase();

        Product produit;
        if (Objects.equals(type, "alimentaire")) {
            System.out.print("Date de péremption (AAAA-MM-JJ) : ");
            LocalDate date = LocalDate.parse(scanner.nextLine());
            produit = new FoodProduct(nom, quantite, prix, seuil, date);
        } else {
            System.out.print("Garantie (mois) : ");
            int garantie = Integer.parseInt(scanner.nextLine());
            produit = new ElectronicProduct(nom, quantite, prix, seuil, garantie);
        }

        boolean ok = stockManager.ajouterProduit(produit);
        if (ok) {
            System.out.println("Produit ajouté avec succès.");
        } else {
            System.out.println("Le produit existe déjà.");
        }
    }

    public void suppressionProduit(User utilisateur) {
        System.out.println(">>>>> Suppression de produit <<<<<<");
        System.out.print("Nom du produit à supprimer : ");
        String nom = scanner.nextLine();

        boolean ok = stockManager.supprimerUnProduit(nom, utilisateur);
        if (ok) {
            System.out.println("Produit supprimé avec succès.");
        } else {
            System.out.println("Échec de la suppression (produit inexistant ou droit insuffisant).\n");
        }
    }

    public void modificationProduit() {
        System.out.println(">>>>> Modification de produit  <<<<<<");
        System.out.print("Nom du produit à modifier : ");
        String ancienNom = scanner.nextLine();

        System.out.print("Nouveau nom : ");
        String newNom = scanner.nextLine();

        System.out.print("Nouveau prix : ");
        double newPrix = Double.parseDouble(scanner.nextLine());

        System.out.print("Nouvelle quantité : ");
        int newQuantite = Integer.parseInt(scanner.nextLine());

        System.out.print("Nouveau seuil : ");
        int newSeuil = Integer.parseInt(scanner.nextLine());

        Product p = stockManager.rechercherUnProduit(ancienNom);
        LocalDate date = null;
        Integer garantie = null;

        if (p instanceof FoodProduct) {
            System.out.print("Nouvelle date de péremption (AAAA-MM-JJ) : ");
            date = LocalDate.parse(scanner.nextLine());
        } else if (p instanceof ElectronicProduct) {
            System.out.print("Nouvelle garantie (mois) : ");
            garantie = Integer.parseInt(scanner.nextLine());
        }

        boolean ok = stockManager.modifierUnProduit(ancienNom, newNom, newPrix, newQuantite, newSeuil, date, garantie);
        if (ok) {
            System.out.println("Produit modifié avec succès.");
        } else {
            System.out.println("Échec de la modification.");
        }
    }

    public void rechercheProduit() {
        System.out.println(">>>>> Recherche de produit <<<<<<");
        System.out.print("Nom du produit à rechercher : ");
        String nom = scanner.nextLine();

        Product produit = stockManager.rechercherUnProduit(nom);
        if (produit != null) {
            System.out.println("Produit trouvé : " + produit);
        } else {
            System.out.println("Produit non trouvé.");
        }
    }

    public void afficherTousProduits() {
        System.out.println(">>>>> Produits disponibles en stock <<<<<<");
        List<Product> liste = stockManager.afficherProduits();
        if (liste.isEmpty()) {
            System.out.println("Aucun produit en stock.");
        } else {
            for (Product p : liste) {
                System.out.println(p);
            }
        }
    }

    public void afficherProduitSousSeuil() {

        Product p = stockManager.produitSousSeuilCritique();
        if (p != null) {
            System.out.println("Produit sous seuil critique : " + p);
        } else {
            System.out.println("Aucun produit sous seuil critique.");
        }
    }
}
