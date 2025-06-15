package model;

import java.util.Scanner;

public class CodeAcces {
    private static final  String ACCES = "GestionStockPOO";

    /**
     * Demande à l'utilisateur de saisir le code d'acces
     * La méthode boucle tant que l'utilisateur ne saisit pas le bon code
     * Lorsque le code correspond au bon code(@code ACCES)
     * L'accès est autorisé
     * En cas de saisie incorrect un message d'erreur est affiché
     * Et la saisie est redemandée
     */
    public static void verifierCode(){
        Scanner scanner = new Scanner(System.in);
        String saisie;
        while (true) {
            System.out.print("veuillez entrer le code d'acces:");
            saisie = scanner.nextLine();
            if (ACCES.equals(saisie)) {
                System.out.println("Acces autorise!");
               break;
            } else {
                System.out.println("Code incorrect! Acces refuse!\n ");

            }
        }
    }
}
