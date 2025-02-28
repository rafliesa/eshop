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
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testDeleteFound() {
        Product product = new Product();
        String id = product.getProductId();
        productRepository.create(product);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());

        assertTrue(productRepository.delete(id));
        assertFalse(productRepository.delete(id));
    }


    @Test
    void testDeleteNotFound() {
        String randomNonexistentID = UUID.randomUUID().toString();
        assertFalse(productRepository.delete(randomNonexistentID));
    }

    @Test
    void testEditNameSuccess() {
        Product product = new Product();
        String productId = product.getProductId();
        product.setProductName("Sampo Cap Bambang");
        productRepository.create(product);

        productRepository.edit(productId, "Sampo Cap Jawa", 0);

        Iterator<Product> iterator = productRepository.findAll()    ;
        assertTrue(iterator.hasNext());

        Product savedProduct = iterator.next();
        assertEquals("Sampo Cap Jawa",  product.getProductName());

    }

    @Test
    void testProductNameRemainsUnchangedIfSetToBlank() {
        Product product = new Product();
        String productId = product.getProductId();
        product.setProductName("Sampo Cap Bambang");
        productRepository.create(product);
        assertEquals("Sampo Cap Bambang", product.getProductName());

        productRepository.edit(productId, "", 0);

        Iterator<Product> iterator = productRepository.findAll()    ;
        assertTrue(iterator.hasNext());

        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals("Sampo Cap Bambang",  product.getProductName());

        productRepository.edit(productId, null, 0);

        iterator = productRepository.findAll()    ;
        assertTrue(iterator.hasNext());

        savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals("Sampo Cap Bambang",  product.getProductName());

    }


    @Test
    void testEditQuantity() {
        Product product = new Product();
        String productId = product.getProductId();
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.edit(productId, "", 0);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(0, product.getProductQuantity());
    }


    @Test
    void testEditQuantityRemainsUnchangedIfSetToNegative() {
        Product product = new Product();
        String productId = product.getProductId();
        product.setProductQuantity(100);
        productRepository.create(product);

        productRepository.edit(productId, "", -100);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(100, savedProduct.getProductQuantity());
    }

    @Test
    void testEditProductNotFound() {
        Product product = new Product();
        String id = product.getProductId();
        productRepository.create(product);

        Iterator<Product> iterator = productRepository.findAll();
        assertTrue(iterator.hasNext());

        Product savedProduct = iterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());

        assertTrue(productRepository.delete(id));

        assertFalse(productRepository.edit(id, "", 0));
    }

}
