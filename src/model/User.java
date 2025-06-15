package model;

public abstract class User {
    protected String login;
    protected String motDePasse;

    public User(String login, String motDePasse) {
        this.login = login;
        this.motDePasse = motDePasse;
    }

    public String getLogin() {
        return login;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    /**
     * Verification du mot de passe fourni
     * @param mdp le mot de passe à verifier
     * @return true si le mot de passe est correct sinon false.
     */
    public boolean verifierMotDePasse(String mdp) {
        return this.motDePasse.equals(mdp);
    }

    /**
     * Affiche le menu specifique à l'utilisateur.
     * L'admin et l'employé ont un menu différent
     * @param ui l'interface utilisée pour afficher le menu
     */
    public abstract void afficherMenu(InterfaceConsole ui);

    /**
     * Retourne le rôle de l'utilisateur.
     * @return une chaine représentant le rôle de l'utilisateur.
     */
    public abstract String getRole();
}
