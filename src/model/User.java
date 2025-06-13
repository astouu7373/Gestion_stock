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

    public boolean verifierMotDePasse(String mdp) {
        return this.motDePasse.equals(mdp);
    }

    public abstract void afficherMenu(InterfaceConsole ui );

    public abstract String getRole();
}
