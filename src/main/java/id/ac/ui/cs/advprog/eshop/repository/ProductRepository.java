package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@Repository
public class ProductRepository {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean deleteById(String id) {
        return productData.removeIf(product -> product.getProductId().equals(id));
    }

    public Product getProductById(String id) {
        return productData.stream().filter(product -> product.getProductId().equals(id))
                                   .findFirst()
                                   .orElse(null);
    }

    public boolean updateProduct(String id, String productName, int productQuantity) {
        Product product = getProductById(id);

        if (product == null) {
            return false;
        }

        if (productName != null && !productName.isEmpty()) {
            product.setProductName(productName);
        }

        if (productQuantity >= 0) {
            product.setProductQuantity(productQuantity);
        }

        return true;
    }

}
