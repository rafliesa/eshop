package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@Repository
public class ProductRepository implements BaseModelrepository<Product> {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    @Override
    public boolean delete(String id) {
        return productData.removeIf(product -> product.getId().equals(id));
    }

    @Override
    public Product findById(String id) {
        return productData.stream().filter(product -> product.getId().equals(id))
                                   .findFirst()
                                   .orElse(null);
    }

    @Override
    public Product edit(String id, Product newProduct) {
        for (int i = 0; i < productData.size(); i++) {
            Product product = productData.get(i);
            if (product.getId().equals(id)) {
                product.setName(newProduct.getName());
                product.setQuantity(newProduct.getQuantity());
                return product;
            }
        }
        return null;
    }

}
