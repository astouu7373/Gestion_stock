package model;
import java.io.File;
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
        UserManager.chargerUser();
        utilisateurs.addAll(UserManager.getUtilisateurs());
    }

    /**
     * La methode fais un clear des produits avant de commencer.
     * Lorsqu'il y a un fichier existant qui a sauvegardé les produits alimentaires ou électroniques, il s'effectue un chargement des produits.
     * Si l'utilisateur n'est pas connecté l'option connecter ou créer un compte s'ouvre à lui.
     * S'il a un compte et qu'il met les bons identifiants, le message connection réussie en tant que @login s'affiche.
     * S'il n'a pas de compte, il pourra en créer un.
     * Par @default il verra choix invalide
     */
    public void demarrer() {
        System.out.println("Bienvenue dans le système de gestion de stock");
        stockManager.getProduits().clear();
        File fileF = new File(fileNameF);
        if(fileF.exists()){
            stockManager.chargerFoodProduct(fileNameF);
        }
        File fileE = new File(fileNameE);
        if(fileE.exists()){
            stockManager.chargerElectronicProduct(fileNameE);
        }
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

        System.out.println("Identifiants incorrects ou compte inexistant.");
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
        UserManager.sauvegarderUser();
        System.out.println("Compte créé avec succès !");
    }

    /**
     * On demandera à l'utilisateur (Admin/Employé) une fois connectée de faire un choix entre 0 et 9 (Admin) ou 0 et 9 (Employé).
     * S'il choisit autre chose, on va lui redemander en boucle.
     * @return lorsque le format n'est pas bon.
     */
    public int demanderChoix() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    /**
     *  Une fois connectée, l'utilisateur choisi 1 pour ajouter un produit
     *  Pour cela, il devra mettre le nom du produit, sa quantité stockée, son prix, son seuil et son type (alimentaire/electronique).
     *  Si c'est un produit alimentaire, il devra saisir sa date de peremption sous la forme (AAAA-MM-JJ).
     *  Si c'est un produit electronique, il devra saisir sa garantie en mois.
     *  À la fin, il verra produit ajouté avec succès.
     */
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

    /**
     * L'utilisateur (admin) connecté doit choisir l'option 3.
     * @param utilisateur pour specifier que seul l'admin peut supprimer un produit
     */
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

    /** L'utilisateur connecté doit choisir l'option 2.
     * Il devra alors modifier un produit en rentrant le nom du produit à modifier.
     * Il doit être sûr que le produit existe déjà.
     * Il doit ensuite lui donner un nouveau nom, une nouvelle quantité, un nouveau prix et un nouveau seuil.
     * Si le produit est un produit alimentaire, il doit lui donner une nouvelle date de peremption.
     * Si c'est un produit électronique, il doit saisir une nouvelle garantie.
     */
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

    /** L'utilisateur doit rechercher un produit en indiquant son nom.
     * Si le produit existe, il s'affichera avec toutes ces informations sinon le système affichera produit non trouvé.
     */
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

    /** L'utilisateur veut afficher tous les produits alimentaires
     * Si la liste est vide, il verra "Aucun produit alimentaire en stock", sinon il verra la liste des produits alimentaires.
     */
    public void afficherTousFoodProduct() {
        System.out.println(">>>>>> Produits alimentaires disponibles en stock <<<<<<");
        List<Product> foodProduct = stockManager.afficherFoodProduct();
        if (foodProduct.isEmpty()) {
            System.out.println("Aucun produit alimentaire en stock!");
        } else {
            for (Product p : foodProduct) {
                System.out.println(p);
            }
        }
    }

    /** L'utilisateur veut afficher la liste de tous les produits électroniques.
     * Si la liste est vide, il verra "Aucun produit électronique en stock", sinon il verra la liste des produits électroniques.
     */
    public void afficherTousElectronicProduct(){
        System.out.println(">>>>>> Produits électroniques disponibles en stock <<<<<<");
        List<Product> electronicProduct = stockManager.afficherElectronicProduct();
        if(electronicProduct.isEmpty()){
            System.out.println("Aucun produit électronique en stock!");
        } else{
            for(Product p: electronicProduct){
                System.out.println(p);
            }
        }
    }

    /** L'utilisateur veut afficher tous les produits sous seuil critique.
     * S'il existe des produits sous seuil, il verra la liste de ces derniers.
     * Sinon, il verra "Aucun produit sous seuil critique.".
     */
    public void afficherProduitSousSeuil() {

        List<Product> seuil= stockManager.produitSousSeuilCritique();
        if (!seuil.isEmpty()) {
            System.out.println("Produit sous seuil:");
           for(Product p : seuil){
               System.out.println(p);
           }
        } else {
            System.out.println("Aucun produit sous seuil critique.");
        }
    }

    String fileNameF = "StockFood.CSV";

    /** Sauvegarde les produits alimentaires présents dans le stock
     * eb utilisant la méthode {@code sauvegardeFoodProduct}
     * le fichier cible est {@code fileNameF}
     */
       public void sauvegardeFoodStock() {
           stockManager.sauvegardeFoodProduct(fileNameF);
        }
    String fileNameE = "StockElectronic.CSV";

    /** Sauvegarde les produits électroniques présents dans le stock
     * eb utilisant la méthode {@code sauvegardeElectronicProduct}
     * le fichier cible est {@code fileNameE}
     */
       public void sauvegardeElectronicStock(){
           stockManager.sauvegardeElectronicProduct(fileNameE);
       }


}


