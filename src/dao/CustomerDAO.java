package dao;

import models.Customer;

import java.util.List;

public interface CustomerDAO {

    void addCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer findCustomerByEmail(String email);
    int updateCustomerCity(int customerId, String newCity);
    int deleteCustomer(int customerId);
}
