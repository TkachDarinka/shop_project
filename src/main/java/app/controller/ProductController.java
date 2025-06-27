package app.controller;

import app.domain.Product;
import app.service.ProductService;

import java.util.List;

public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService){
        this.productService = productService;
    }
    public Product save (String name, double price){
        Product product = new Product(name,price);
        return productService.create(product);
    }
    public List<Product> getAll(){
        return productService.readAllActiveProducts();
    }
    public Product getById(Long id){
        return productService.readById(id);
    }
    public void update(Long id, String name, double price){
        Product product= new Product(id,name,price);
        productService.update(product);
    }

    public void deleteById(Long id) {
        productService.deleteById(id);
    }

    public void deleteByName(String name) {
        productService.deleteByName(name);
    }

    public void restoreById(Long id) {
        productService.restoreById(id);
    }

    public long readActiveProductsTotalCount() {
        return productService.readActiveProductsTotalCount();
    }

    public double readActiveProductsTotalCost() {
        return productService.readActiveProductsTotalCost();
    }

    public double readActiveProductsAveragePrice() {
        return productService.readActiveProductsAveragePrice();
    }
}
