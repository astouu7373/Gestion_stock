package model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ProductTest {
 @Test
void testConstructeur(){
     Product product = new ElectrocicProduct("Chargeur", 20, 20, 3);
     assertEquals("Chargeur", product.getNom());
     assertEquals(20, product.getQuantité());
     assertEquals(20,product.getPrix());
 }




}
