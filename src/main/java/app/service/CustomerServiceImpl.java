package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.repositories.CustomerRepository;
import app.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository repository; //CustomerRepository repository — ссылка на класс, в котором мы прописывали все операции с клиентами (например, поиск, сохранение, удаление).
    private ProductRepository productRepository;

    /*
    Конструктор класса CustomerServiceImpl.
    В конструктор передаётся объект CustomerRepository.
    Внутри конструктора этот объект присваивается полю repository.
    Благодаря этому, все методы класса могут использовать repository для работы с клиентами.
     */
    public CustomerServiceImpl(CustomerRepository repository) { //
        this.repository = repository;
    }

    @Override
    public Customer create(Customer customer) {
        if (customer == null) {
            throw new CustomerCreateException("Customer cannot be null!");
        }
        String name = customer.getName().toLowerCase();
        if (name.isBlank() || name.length() < 5) { //customer.getName().isBlank() проверяет, что имя покупателя не пустое и не состоит только из пробелов.
            throw new CustomerCreateException("The customer name field must not be empty and must contain at least 5 characters!");
        }
        if (repository.existsByName(name)) {
            throw new CustomerCreateException("A customer with this name already exists!");
        }
        customer.setActivity(true);
        return repository.save(customer);
    }

    @Override
    public List<Customer> getAllActiveCustomer() {
        return repository.findAll().stream()
                .filter(customer -> customer.isActivity())
                .collect(Collectors.toList());
    }

    @Override
    public Customer getById(Long id) {
        Customer customer = repository.findById(id);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with id " + id + " not found!");
        }
        return customer;
    }

    @Override
    public void update(Customer customer) {
        if (customer == null) {
            throw new CustomerUpdateException("Customer cannot be null!");
        }
        if (repository.update(customer) == null) {
            throw new CustomerUpdateException("Customer not found!");
        }
        if (customer.getId() == 0 || customer.getId() < 0) {
            throw new CustomerUpdateException("Customer id should be positive!");
        }
        String name = customer.getName().toLowerCase();
        if (name.isBlank() || name.length() < 5) {
            throw new CustomerUpdateException("The customer name field must not be empty and must contain at least 5 characters!");
        }
        if (repository.existsByName(name)) {
            throw new CustomerUpdateException("A customer with this name already exists!");
        }
        if (!customer.isActivity()) {
            throw new CustomerUpdateException("Cannot update an inactive customer!");
        }
    }

    @Override
    public void deleteById(Long id) {
        getById(id).setActivity(false);
    }

    @Override
    public void deleteByName(String name) {
        Customer customer = getAllActiveCustomer().stream()
                .filter(customer1 -> customer1.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer with name " + name + " not found!");
        }
    }

    @Override
    public void restoreById(Long id) {
        getById(id).setActivity(true);
    }

    @Override
    public long getActiveCustomersTotalCount() {
        return getAllActiveCustomer().size();
    }

    @Override
    public double getTotalCostShoppingCart(Long id) {
        Customer customer = repository.findById(id);
        if (!customer.isActivity()) {
            throw new CustomerNotFoundException("Customer not found!");
        }
        return customer.getProducts()
                .stream()
                .mapToDouble(p -> p.getPrice())
                .sum();
    }

    @Override
    public double getAverageCostProduct(Long id) {
        Customer customer = repository.findById(id);
        if (!customer.isActivity()) {
            throw new CustomerNotFoundException("Customer not found!");
        }
        return customer.getProducts()
                .stream()
                .mapToDouble(p -> p.getPrice())
                .average()
                .orElse(0);
    }

    @Override
    public void addProductToShoppingCart(Long customerId, Long productId) {
        Customer customer = repository.findById(customerId);
        if (!customer.isActivity()) {
            throw new CustomerNotFoundException("Customer not found or not active!");
        }
        Product product = productRepository.findById(productId);
        if (!product.isActivity()) {
            throw new ProductNotFoundException("Product not found or not active!");
        }
        customer.getProducts().add(product);
    }

    @Override
    public void deleteProductFromShoppingCart(Long customerId, Long productId) {
        Customer customer = repository.findById(customerId);
        if (!customer.isActivity()) {
            throw new CustomerNotFoundException("Customer not found or not active!");
        }
        Product product = productRepository.findById(productId);
        if (!product.isActivity()) {
            throw new ProductNotFoundException("Product not found or not active!");
        }
        customer.getProducts().removeIf(p -> p.getId().equals(productId)); //removeIf(...) проходит по всем элементам списка и удаляет те, для которых условие возвращает true. Метод возвращает true, если хотя бы один элемент был удалён, и false, если ни один элемент не подошёл под условие (ничего не удалено).
    }

    @Override
    public void clearShoppingCart(Long customerId) {
        Customer customer = repository.findById(customerId);
        if (!customer.isActivity()) {
            throw new CustomerNotFoundException("Customer not found or not active!");
        }
        customer.getProducts().clear();
    }

}
