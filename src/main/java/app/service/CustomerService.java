package app.service;

import app.domain.Customer;
import app.domain.Product;

import java.util.List;

public interface CustomerService {

    Customer create(Customer customer); //Сохранить покупателя в базе данных (при сохранении покупатель автоматически считается активным).

    List<Customer> getAllActiveCustomer(); //Вернуть всех покупателей из базы данных (активных).

    Customer getById(Long id); //Вернуть одного покупателя из базы данных по его идентификатору (если он активен).

    void update(Customer customer); //Изменить одного покупателя в базе данных по его идентификатору.

    void deleteById(Long id); //Удалить покупателя из базы данных по его идентификатору.

    void deleteByName(String name); //Удалить покупателя из базы данных по его имени.

    void restoreById(Long id); //Восстановить удалённого покупателя в базе данных по его идентификатору.

    long getActiveCustomersTotalCount(); //Вернуть общее количество покупателей в базе данных (активных).

    double getTotalCostShoppingCart(Long id); //Вернуть стоимость корзины покупателя по его идентификатору (если он активен). (без аргумента, так как используем метод getActiveCustomersTotalCount())

    double getAverageCostProduct(Long id); //Вернуть среднюю стоимость продукта в корзине покупателя по его идентификатору (если он активен)

    void addProductToShoppingCart(Long customerId, Long productId); //Добавить товар в корзину покупателя по их идентификаторам (если оба активны)

    void deleteProductFromShoppingCart(Long customerId, Long productId); //Удалить товар из корзины покупателя по их идентификаторам

    void clearShoppingCart(Long customerId); //Полностью очистить корзину покупателя по его идентификатору (если он активен)

}
