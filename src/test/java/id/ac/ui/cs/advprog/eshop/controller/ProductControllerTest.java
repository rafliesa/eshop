package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    private Product product;

    @BeforeEach
    void setUp() {
        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductQuantity(10);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model, times(1)).addAttribute(eq("product"), any(Product.class));
    }

    @Test
    void testCreateProduct() {
        String viewName = productController.createProduct(product, model);
        assertEquals("redirect:list", viewName);
        verify(service, times(1)).create(any(Product.class));
    }

    @Test
    void testProductListPage() {
        List<Product> products = Arrays.asList(product);
        when(service.findAll()).thenReturn(products);

        String viewName = productController.ProductListPage(model);
        assertEquals("productList", viewName);
        verify(model, times(1)).addAttribute("products", products);
    }

    @Test
    void testDeleteProduct() {
        String viewName = productController.deleteProduct("1");
        assertEquals("redirect:/product/list", viewName);
        verify(service, times(1)).deleteById("1");
    }

    @Test
    void testUpdateProductPage() {
        when(service.getProductById("1")).thenReturn(product);
        String viewName = productController.updateProductPage("1", model);
        assertEquals("editProduct", viewName);
        verify(model, times(1)).addAttribute("product", product);
    }

    @Test
    void testUpdateProduct() {
        String viewName = productController.updateProduct("1", "Updated Product", 20);
        assertEquals("redirect:/product/list", viewName);
        verify(service, times(1)).update("1", "Updated Product", 20);
    }
}
