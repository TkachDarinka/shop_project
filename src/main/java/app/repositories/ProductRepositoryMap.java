package app.repositories;

import app.domain.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductRepositoryMap implements ProductRepository {

    private final Map<Long, Product> database = new HashMap<>();
    private long currentId = 0;

    @Override
    public Product save(Product product) {
        product.setId(++currentId);
        database.put(currentId,product);
        return product;
    }

    @Override
    public List<Product> findAll() {
        return new ArrayList<>(database.values());
    }

    @Override
    public Product findById(Long id) {
       /* return database.values().stream()
                .filter(p->p.getId().equals(id))
                .findFirst()
                .orElse(null);*/
        return database.get(id);
    }

    @Override
    public boolean deleteById(Long id) {
        Product oldProduct = findById(id);

        if(oldProduct == null){
            return false;
        }
        oldProduct.setActivity(false);
        return true;
    }

    @Override
    public Product updateById(Product product) {
        Long id = product.getId();
        double newPrice = product.getPrice();
        String newName = product.getName();
        boolean newActiv = product.isActivity();

        Product oldProduct = findById(id);

        if(oldProduct != null){
            oldProduct.setName(newName);
            oldProduct.setPrice(newPrice);
            oldProduct.setActivity(newActiv);
        }
        return oldProduct;
    }
    /*
    public static void main(String[] args) {
        ProductRepository repository = new ProductRepositoryMap();

        System.out.println(repository.save(new Product(true, "Coffee", 3.20)));
        System.out.println(repository.save(new Product(true, "Baguet", 4.10)));

        System.out.println(repository.findAll());

        System.out.println(repository.findById(2L));

        System.out.println("-------Delete-------");
        repository.deleteById(1L);
        System.out.println(repository.findById(1L));
        System.out.println("-------Update-------");
        Product newProduct = new Product(true, "Baguette",7);
        newProduct.setId(2L);
        System.out.println(repository.updateById(newProduct));
    }
*/
}
