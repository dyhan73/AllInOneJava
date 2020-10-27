package com.example.ch15;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("customerRepository")
public class CustomerRepositoryJDBC implements CustomerRepository {
    @Autowired
    private DataSource dataSource;

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet rs = null;
    private static final String SQL_GETALL = "select * from customer";
    private static final String SQL_GETBYNAME = "select * from customer where name=?";
    private static final String SQL_INSERT = "insert into customer (name, address, email) values (?, ?, ?)";

    @Override
    public List<CustomerEntity> findAll() {
        List<CustomerEntity> customers = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GETALL);
            rs = statement.executeQuery();

            CustomerEntity customer = null;
            while (rs.next()) {
                customer = new CustomerEntity();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
//                return customers;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public List<CustomerEntity> findByName(String name) {
        List<CustomerEntity> customers = new ArrayList<>();
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_GETBYNAME);
            statement.setString(1, name);
            rs = statement.executeQuery();

            CustomerEntity customer = null;
            while (rs.next()) {
                customer = new CustomerEntity();
                customer.setId(rs.getInt("customer_id"));
                customer.setName(rs.getString("name"));
                customer.setAddress(rs.getString("address"));
                customer.setEmail(rs.getString("email"));
                customers.add(customer);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
//                return customers;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return customers;
    }

    @Override
    public void save(CustomerEntity customer) {
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL_INSERT);
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getAddress());
            statement.setString(3, customer.getEmail());
            statement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                    statement = null;
                }
                if (connection != null) {
                    connection.close();
                    connection = null;
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }
}
