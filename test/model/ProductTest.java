package model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
 @Test
void testConstructeurElectrocic(){
     Product product = new ElectrocicProduct("Chargeur", 20, 20, 3, 3);
     assertEquals("Chargeur", product.getNom());
     assertEquals(20, product.getQuantité());
     assertEquals(20,product.getPrix());
     assertEquals(3, product.getSeuil());
     assertEquals(3, ((ElectrocicProduct) product).getGarantie());
 }

@Test
void testConstructeurFood(){
    Product product = new FoodProduct("Lait", 25, 5,3, LocalDate.of(2026,01,28));
    assertEquals("Lait", product.getNom());
    assertEquals(25, product.getQuantité());
    assertEquals(5,product.getPrix());
    assertEquals(3, product.getSeuil());
    assertEquals(LocalDate.of(2026, 01, 28), ((FoodProduct) product).getDate_peremption());
}

}
