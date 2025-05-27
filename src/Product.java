public class Product {
    String Nom;
    int Quantité;
    double Prix;
    Product(String nom, int quantité, double prix){
        this.Nom = nom;
        this.Quantité = quantité;
        this.Prix = prix;
    }
    public String getNom() { return Nom;};
    public void setNom(String Nom){
       this.Nom = Nom;
    }
    public int getQuantité(){return Quantité;};
    public void setQuantité(int Quantité){
        this.Quantité = Quantité;
    }
    public double getPrix(){return Prix;};
    public void SetPrix( double Prix){
        this.Prix = Prix;
    }
}
