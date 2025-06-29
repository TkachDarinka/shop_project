package app.service;

import app.domain.Customer;

import java.util.List;

public class CustomerServiceImpl implements CustomerService{
    @Override
    public Customer create(Customer customer) {
        return null;
    }

    @Override
    public List<Customer> readAllActiveCustomer() {
        return List.of();
    }

    @Override
    public Customer getById(Long id) {
        return null;
    }

    @Override
    public void update(Customer customer) {

    }

    @Override
    public void deleteById(Long id) {

    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void restoreById(Long id) {

    }

    @Override
    public long getActiveCustomersTotalCount() {
        return 0;
    }

    @Override
    public double getTotalCostShoppingCartActiveCustomersById() {
        return 0;
    }

    @Override
    public double getAverageCostProductActiveCustomersById() {
        return 0;
    }

    @Override
    public void addProductToShoppingCart() {

    }

    @Override
    public void deleteProductFromShoppingCart() {

    }

    @Override
    public void clearShoppingCart() {

    }
}
