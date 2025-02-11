package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "createProduct";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product, Model model) {
        service.create(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String ProductListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.deleteById(id);
        return "redirect:/product/list";
    }

    @GetMapping("/update/{id}")
    public String updateProductPage(@PathVariable String id, Model model) {
        Product product = service.getProductById(id);
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable String id, String productName, int productQuantity) {
        service.update(id, productName, productQuantity);
        return "redirect:/product/list";
    }
}
