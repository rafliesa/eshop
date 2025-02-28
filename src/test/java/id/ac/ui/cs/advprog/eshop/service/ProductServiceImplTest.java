package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductRepository productRepository;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product)).thenReturn(product);
        Product createdProduct = productService.create(product);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAllProducts() {
        Iterator<Product> iterator = Arrays.asList(product).iterator();
        when(productRepository.findAll()).thenReturn(iterator);
        List<Product> products = productService.findAll();
        assertEquals(1, products.size());
        assertEquals(product, products.get(0));
    }

    @Test
    void testDeleteProductById() {
        productService.delete("1");
        verify(productRepository, times(1)).delete("1");
    }

    @Test
    void testGetProductById() {
        when(productRepository.findById("1")).thenReturn(product);
        Product foundProduct = productService.findById("1");
        assertEquals(product, foundProduct);
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.edit("1", "Updated Product", 20)).thenReturn(true);
        boolean result = productService.edit("1", "Updated Product", 20);
        assertTrue(result);
        verify(productRepository, times(1)).edit("1", "Updated Product", 20);
    }
}
