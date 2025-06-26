package app.service;

import app.domain.Product;

import java.util.List;

public interface ProductService {
    Product create(Product product); //Сохранить продукт в базе данных (при сохранении продукт автоматически считается активным)
    List<Product> readAllActiveProducts(); //Вернуть все продукты из базы данных (активные).
    Product readById(Long id); //Вернуть один продукт из базы данных по его идентификатору (если он активен).
    void update(Product product); //Изменить один продукт в базе данных по его идентификатору.
    void deleteById(Long id); //Удалить продукт из базы данных по его идентификатору.
    void deleteByName(String name); //Удалить продукт из базы данных по его наименованию.
    void restoreById(Long id); //Восстановить удалённый продукт в базе данных по его идентификатору.
    long readActiveProductsTotalCount(); //Вернуть общее количество продуктов в базе данных (активных).
    double readActiveProductsTotalCost(); //Вернуть суммарную стоимость всех продуктов в базе данных (активных).
    double readActiveProductsAveragePrice(); //Вернуть среднюю стоимость продукта в базе данных (из активных).

}
