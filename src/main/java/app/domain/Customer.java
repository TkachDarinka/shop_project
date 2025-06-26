package app.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

//POJO (Plain Old Java Object) — это обычный Java-объект, который не зависит от каких-либо специфических фреймворков или интерфейсов.
// Он может содержать любое количество полей и методов, которые не обязательно должны быть приватными или публичными.
// POJO обычно используется для инкапсуляции бизнес-логики в приложениях
public class Customer {
    private Long id;
    private boolean activity;
    private String name;
    private List<Product> products = new ArrayList<>();

    public Customer(boolean activity, String name, List<Product> products) {
        this.activity = activity;
        this.name = name;
        this.products = products;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public boolean isActivity() {
        return activity;
    }

    public void setActivity(boolean activity) {
        this.activity = activity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return activity == customer.activity && Objects.equals(id, customer.id) && Objects.equals(name, customer.name) && Objects.equals(products, customer.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, name, products);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", activity=" + activity +
                ", name='" + name + '\'' +
                ", products=" + products +
                '}';
    }
}
