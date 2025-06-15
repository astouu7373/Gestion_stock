package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserManager {
    static String userFile = "User.CSV";
    private static final List<User> UTILISATEURS = new ArrayList<>();
    public static List<User> getUtilisateurs(){
        return UTILISATEURS;
    }

    /** Sauvegarde les utilisateurs qui ont un compte
     * Si la sauvegarde échoue le message "Erreur lors de la sauvegarde" s'affichera.
     */
    public static void sauvegarderUser(){
        try(FileWriter fw = new FileWriter(userFile)){
            for(User u: UTILISATEURS){
                fw.write(u.getLogin()+","+u.getMotDePasse()+","+u.getRole()+ "\n");
            }
        } catch (IOException ioe){
            System.out.println("Erreur lors de la sauvegarde:"+ioe.getMessage());
        }
    }

    /** Charge les utilisateurs dont les informations sont sauvegardées.
     * Si la charge échoue, le message "Erreur de chargement" s'affiche.
     */
    public static void chargerUser(){
        try(BufferedReader br = new BufferedReader(new FileReader(userFile))){
            String ligne;
            while((ligne= br.readLine())!=null){
                String[] parts = ligne.split(",");
                if(parts.length == 3){
                    String login = parts[0].trim();
                    String mdp= parts[1].trim();
                    String role = parts[2].trim();

                    User u = switch (role.toLowerCase()){
                        case "admin"-> new Admin(login,mdp);
                        case "employe"-> new Employe(login, mdp);
                        default -> null;
                    };
                    if (u!=null) {
                        UTILISATEURS.add(u);
                    }
                }
            }
        } catch (IOException ioe){
            System.out.println("Erreur de chargement!"+ioe.getMessage());
        }
    }
}
