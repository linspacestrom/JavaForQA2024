package ru.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}

enum ProductType {
    GOOD,
    SERVICE;
}

record Product(String id, String name, long cost, ProductType productType) {}

record Customer(String id, String name, String phone, long age) {}

record Order(String id, String customerld, String productld, long count, long amount) {}

class ProductRepository {
    List<Product> productList = new ArrayList<>();

    public void save(String id, String name, long cost, ProductType productType){
        productList.add(new Product(id, name, cost, productType));
    };
    public List<Product> findAll() {
        return productList;
    }
}

class OrderRepository {
    List<Order> orderList = new ArrayList<>();
    public void save(String id, String customerld, String productld, long count, long amount) {
        orderList.add(new Order(id,customerld,productld,count,amount));
    };
    public List<Order> findAll(){
        return orderList;
    }
}

class CustomerRepository {
    List<Customer> customerList = new ArrayList<>();
    public void save(String id, String name, String phone, long age) {
        customerList.add(new Customer(id, name, phone, age));
    };
    public List<Customer> findAll() {
        return customerList;
    }
}

class ProductService {
    ProductRepository productRepository = new ProductRepository();
    public void save(String id, String name, long cost, ProductType productType) {
        productRepository.save(id, name, cost, productType);
    }
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    public List<Product> findByProductType(ProductType productType) {
        List<Product> productList = new ArrayList<>();
        for (Product product : productRepository.findAll()) {
            if (productType.equals(product.productType())) {
                productList.add(product);
            }
        }
        return productList;
    }
}

class CustomerService {
    CustomerRepository customerRepository = new CustomerRepository();
    public void save(String id, String name, String phone, long age){
        customerRepository.save(id, name, phone, age);
    }
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }
}

class OrderService {
    long amount;
    OrderRepository orderRepository = new OrderRepository();
    List<Order> orderList = new ArrayList<>();
    public void add(Customer customer, Product product, long count) {
        amount = product.cost()*count;
        Order order = new Order(UUID.randomUUID().toString(), customer.id(), product.id(), count, amount);
    }
}