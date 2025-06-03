package model;

import java.util.Scanner;

public class InterfaceConsole {
    private StockManager stockManager ;
    private Scanner scanner ;

     public InterfaceConsole(){
         stockManager = new StockManager() ;
         scanner = new Scanner(System.in);
     }

     public void demarrer () {
         System.out.println("Bienvenue dans le système de gestion de stock");
         boolean continuer = true;
         while (continuer) {
             afficherMenu();
             int choix = demanderChoix();
             switch (choix) {
                 case 1:
                     break;
                 case 2:
                     break;
                 case 3:
                     break;
                 case 4:
                     break;
                 case 5:
                     break;
                 case 6:
                     break;
                 default:
                     System.out.println(("choix invalide , veillez réessayer ! "));

             }
         }
         scanner.close();
     }
     public void afficherMenu(){
         System.out.println(" <<<<< MENU >>>>>");
         System.out.println ("1.afficher tous les produits");
         System.out.println ("2.Rechercher un produit");
         System.out.println("3.Modifier un produit");
         System.out.println("4.Ajouter un produit");
         System.out.println("5.Afficher les produits sous seuil critique ");
         System.out.println("6.Supprimer un produit");
         System.out.println(" Qu'est ce que vous voulez faire ?");
     }
     public int demanderChoix(){
         try {
             return Integer.parseInt(scanner.nextLine());
         } catch (NumberFormatException e){
             return -1 ;
         }
     }

     public void AjoutProdit(){
         System.out.println("====== Ajout Produit =====");

     }

     public void suppressionProduit(){

     }
     public void modificationProduit(){

     }
     public void rechercheProduit(){

     }

}
