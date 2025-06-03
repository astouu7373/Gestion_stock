package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static model.Role.Admin;
import static model.Role.Employee;
import static org.junit.jupiter.api.Assertions.*;

public class StockManagerTest {
    private StockManager stockManager ;
    @BeforeEach
    public void setUp(){
        stockManager = new StockManager() ;
    }
@Test
    public void ajoutProduitTest(){
    Product produit = new ElectrocicProduct ("ecouteur" , 20 , 45 ,5 , 2);
    Product produitcopy = new ElectrocicProduct("ecouteur",22,40,10,5);

    assertTrue(stockManager.ajouterProduit(produit));
    assertFalse(stockManager.ajouterProduit(produitcopy), "ce nom de produit existe déjà");
    assertEquals (1 , stockManager.afficherProduits().size() ,"le produit devrait etre ajouter au stock");
    assertEquals("ecouteur" , stockManager.afficherProduits().get(0).getNom() ,"le nom du produit est ecouteur");
    assertEquals(20 , stockManager.afficherProduits().get(0).getQuantite());
    assertEquals(45 , stockManager.afficherProduits().get(0).getPrix());
    assertEquals(5 , stockManager.afficherProduits().get(0).getSeuil());
}

@Test
public void supprimerPoduitTest(){
    Product produit1 = new ElectrocicProduct ("ecouteur" , 20 , 45 ,5 , 2);
    stockManager.ajouterProduit(produit1);
    Product produit2 = new FoodProduct("Lait", 25, 5,3, LocalDate.of(2026,01,28));
    stockManager.ajouterProduit(produit2);
    User admin = new User("astou","dia","diaass","xyzt" ,Admin);
    User employe = new User("astou","dia","diaass","xyzt" ,Employee);
    assertTrue(stockManager.supprimerProduit("Lait" , admin));
    assertFalse(stockManager.supprimerProduit("ecouteur" , employe));
    assertFalse(stockManager.supprimerProduit("beurre",admin) ,"ce produit n'existe pas il ne peut etre supprimé !");
    assertEquals (1 , stockManager.afficherProduits().size() ,"il devrait rester qu'un seul produit");
    assertEquals("ecouteur" , stockManager.afficherProduits().get(0).getNom() ,"le nom du produit restant est ecouteur ");
    assertEquals(20 , stockManager.afficherProduits().get(0).getQuantite());

}
@Test
public void seuilCritiqueTest(){
    Product produit1 = new ElectrocicProduct ("ecouteur" , 2 , 45 ,10 , 2);
     stockManager.ajouterProduit(produit1);
     assertNotNull(stockManager.produitSousSeuilCritique(),"il y ' a un produit sous seuil");
     assertEquals("ecouteur", stockManager.produitSousSeuilCritique().getNom() , "le produit sous seuil est ecouteur ");
}

@Test
    public  void modifierProduitTest(){
    Product produitAmodifier = new FoodProduct("Lait", 25, 5,3, LocalDate.of(2026,01,28));
    stockManager.ajouterProduit(produitAmodifier);
    Product produitExistant = new FoodProduct("pomme", 25, 5,3, LocalDate.of(2026,01,28));
    stockManager.ajouterProduit(produitExistant);
assertTrue(stockManager.modifierProduit(produitAmodifier.getNom(), "Lait",20,10,5, null, null));
assertFalse(stockManager.modifierProduit(produitAmodifier.getNom(), produitExistant.getNom(),10,50,20, null , null)," ce nom existe déjà");

    }
}
