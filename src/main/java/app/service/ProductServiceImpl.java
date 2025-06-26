package app.service;

import app.domain.Product;
import app.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public Product create(Product product) {
        if (product == null) {
            throw new ProductSaveException("Product cannot be null!");
        }
        String name = product.getName();
        if (name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new ProductSaveException("Product name should be at least 3 characters long!");
        }
        if (product.getPrice() <= 0) {
            throw new ProductSaveException("Product price cannot be negative or zero!");
        }
        product.setActivity(true);
        return repository.save(product);
    }

    @Override
    public List<Product> readAllActiveProducts() {
        return repository.findAll().stream()
                .filter(x -> x.isActivity())
                .collect(Collectors.toList());
    }

    @Override
    public Product readById(Long id) {
        Product product = repository.findById(id);

        if (product == null || !product.isActivity()) {
            throw new ProductNotFoundException("Product whith id " + id + "not found!");
        }
        return product;
    }

    @Override
    public void update(Product product) {
        if (product == null) {
            throw new ProductUpdateException("Product cannot be null!");
        }

        Long id = product.getId();
        if (id == null || id < 0) {
            throw new ProductUpdateException("Product id should be positive!");
        }

        String name = product.getName();
        if (name == null || name.trim().isEmpty() || name.length() < 3) {
            throw new ProductUpdateException("Product name should be at least 3 characters long!");

        }
        if (product.getPrice() <= 0) {
            throw new ProductUpdateException("Product price cannot be negative or zero!");
        }
    }

    @Override
    public void deleteById(Long id) {
        readById(id).setActivity(false);
    }

    @Override
    public void deleteByName(String name) { //удаляем продукт из базы данных по его наименованию.
        Product product = readAllActiveProducts().stream()
                .filter(p -> p.getName().equals(name))
                .findFirst()
                .orElse(null);

        if (product == null) {
            throw new ProductNotFoundException("Product with name " + name + "not found!");
        }
        product.setActivity(false);
    }

    @Override
    public void restoreById(Long id) {
        readById(id).setActivity(true);

    }

    @Override
    public long readActiveProductsTotalCount() { //возвращаем общее количество продуктов в базе данных (активных)
        return readAllActiveProducts().size();
    }

    @Override
    public double readActiveProductsTotalCost() {
        return readAllActiveProducts().stream()
                .mapToDouble(p-> p.getPrice())
                .sum();
    }

    @Override
    public double readActiveProductsAveragePrice() { //Вернуть среднюю стоимость продукта в базе данных (из активных)
        return readAllActiveProducts().stream()
                .mapToDouble(p->p.getPrice())
                .average()
                .orElse(0);
    }
}
