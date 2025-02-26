package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;

public interface ProductService {
    public Product create(Product product);
    public List<Product> findAll();
    public void deleteById(String id);
    public Product getProductById(String id);
    public boolean update(String id, String productName, int productQuantity);
}
