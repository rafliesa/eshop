package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product("1", "Sampo Cap Bambang", 100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getId(), savedProduct.getId());
        assertEquals(product.getName(), savedProduct.getName());
        assertEquals(product.getQuantity(), savedProduct.getQuantity());
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product("1", "Sampo Cap Bambang", 100);
        productRepository.create(product);
        assertTrue(productRepository.delete("1"));
        assertFalse(productRepository.delete("1"));
    }

    @Test
    void testEditProduct() {
        Product product = new Product("1", "Sampo Cap Bambang", 100);
        productRepository.create(product);

        Product updatedProduct = new Product("1", "Sampo Cap Jawa", 200);
        Product edited = productRepository.edit("1", updatedProduct);

        assertNotNull(edited);
        assertEquals("Sampo Cap Jawa", edited.getName());
        assertEquals(200, edited.getQuantity());
    }

    @Test
    void testEditProductNotFound() {
        Product updatedProduct = new Product("1", "Sampo Cap Jawa", 200);
        Product edited = productRepository.edit("1", updatedProduct);
        assertNull(edited);
    }
}