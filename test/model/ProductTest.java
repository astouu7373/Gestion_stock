package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
 @Test
void testConstructeurElectrocic(){
     ElectronicProduct product = new ElectronicProduct("Chargeur", 20, 20, 3, 3);
     assertEquals("Chargeur", product.getNom());
     assertEquals(20, product.getQuantite());
     assertEquals(20,product.getPrix());
     assertEquals(3, product.getSeuil());
     assertEquals(3, product.getGarantie());
 }

@Test
void testConstructeurFood(){
    FoodProduct product = new FoodProduct("Lait", 25, 5,3, LocalDate.of(2026, 1,28));
    assertEquals("Lait", product.getNom());
    assertEquals(25, product.getQuantite());
    assertEquals(5,product.getPrix());
    assertEquals(3, product.getSeuil());
    assertEquals(LocalDate.of(2026, 1, 28), product.getDatePeremption());
}

}
