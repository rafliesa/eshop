package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Product extends BaseModel {
    public Product() {}

    public Product(String id, String name, int quantity) {
        super.setId(id);
        super.setName(name);
        super.setQuantity(quantity);
    }
}
