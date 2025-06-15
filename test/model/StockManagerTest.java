package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StockManagerTest {
    private StockManager stockManager ;
    @BeforeEach
    public void setUp(){
        stockManager = new StockManager() ;
    }
@Test
    public void ajoutProduitTest(){
    Product produit = new ElectronicProduct("ecouteur" , 20 , 45 ,5 , 2);
    Product produitcopy = new ElectronicProduct("ecouteur",22,40,10,5);

    assertTrue(stockManager.ajouterProduit(produit));
    assertFalse(stockManager.ajouterProduit(produitcopy), "ce nom de produit existe déjà");
    assertEquals (1 , stockManager.afficherElectronicProduct().size() ,"le produit devrait etre ajouter au stock");
    assertEquals("ecouteur" , stockManager.afficherElectronicProduct().get(0).getNom() ,"le nom du produit est ecouteur");
    assertEquals(20 , stockManager.afficherElectronicProduct().get(0).getQuantite());
    assertEquals(45 , stockManager.afficherElectronicProduct().get(0).getPrix());
    assertEquals(5 , stockManager.afficherElectronicProduct().get(0).getSeuil());
}

@Test
public void supprimerPoduitTest(){
    Product produit1 = new ElectronicProduct("ecouteur" , 20 , 45 ,5 , 2);
    stockManager.ajouterProduit(produit1);
    Product produit2 = new FoodProduct("Lait", 25, 5,3, LocalDate.of(2026,1,28));
    stockManager.ajouterProduit(produit2);
    User admin = new Admin("dia","diaass");
    User employe = new Employe("astou","dia");
    assertTrue(stockManager.supprimerUnProduit("Lait" , admin));
    assertFalse(stockManager.supprimerUnProduit("ecouteur" , employe));
    assertFalse(stockManager.supprimerUnProduit("beurre",admin) ,"ce produit n'existe pas il ne peut etre supprime !");
    assertEquals (1 , stockManager.afficherElectronicProduct().size() ,"il devrait rester qu'un seul produit");
    assertEquals("ecouteur" , stockManager.afficherElectronicProduct().get(0).getNom() ,"le nom du produit restant est ecouteur ");
    assertEquals(20 , stockManager.afficherElectronicProduct().get(0).getQuantite());

}
@Test
public void seuilCritiqueTest(){
    Product produit1 = new ElectronicProduct("ecouteur" , 2 , 45 ,10 , 2);
     stockManager.ajouterProduit(produit1);
    List<Product> seuil = stockManager.produitSousSeuilCritique();
     assertNotNull(stockManager.produitSousSeuilCritique(),"il y ' a un produit sous seuil");
     assertTrue(seuil.stream().anyMatch(p->p.getNom().equals("ecouteur")), "le produit sous seuil est ecouteur ");
}

@Test
    public  void modifierProduitTest(){
    Product produitAmodifier = new FoodProduct("Lait", 25, 5,3, LocalDate.of(2026,1,28));
    stockManager.ajouterProduit(produitAmodifier);
    Product produitExistant = new FoodProduct("pomme", 25, 5,3, LocalDate.of(2026,1,28));
    stockManager.ajouterProduit(produitExistant);
assertTrue(stockManager.modifierUnProduit(produitAmodifier.getNom(), "Lait",20,10,5, null, null));
assertFalse(stockManager.modifierUnProduit(produitAmodifier.getNom(), produitExistant.getNom(),10,50,20, null , null)," ce nom existe déjà");

    }
}
