package com.example.ch15;

import java.util.List;

public interface CustomerRepository {
    List<CustomerEntity> findAll();
    List<CustomerEntity> findByName(String name);
    void save(CustomerEntity customer);
}
