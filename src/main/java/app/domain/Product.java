package app.domain;

import java.util.Objects;

public class Product {
    private final Long id;
    private boolean activity;
    private String name;
    private double price;

    public Product(Long id, boolean activity, String name, double price) {
        this.id = id;
        this.activity = activity;
        this.name = name;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return activity == product.activity && Double.compare(price, product.price) == 0 && Objects.equals(id, product.id) && Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, activity, name, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", activity=" + activity +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
