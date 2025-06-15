package gestionstock;

import model.CodeAcces;
import model.InterfaceConsole;
public class Main {
    /**
     * Le point d'entrée de l'application.
     * Verifie en boucle le code d'accès, et démarre l'interface.
     * Relance l'authentification après chaque déconnexion.
     * @param args les arguments de la ligne de commande.
     */
    public static void main(String[] args) {
        InterfaceConsole ap = new InterfaceConsole();
        do {
            CodeAcces.verifierCode();
            ap.demarrer();
        } while (true);
    }
}


