package service;

import dao.CustomerDAO;
import models.Customer;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class CustomerService {

    private final  CustomerDAO customerDAO;

    public CustomerService(CustomerDAO customerDAO){
        this.customerDAO = customerDAO;
    }

    public boolean addCustomer(Customer customer)throws SQLException {
        Customer existingCustomer = customerDAO.findCustomerByEmail(customer.getEmail());
        if (existingCustomer != null){
            return false;
        }
        customerDAO.addCustomer(customer);
        return true;
    }

    public List<Customer> showAllCustomers()throws SQLException{
        return customerDAO.getAllCustomers();
    }

    public Customer findCustomerByEmail(String email) throws SQLException{
        return customerDAO.findCustomerByEmail(email);
    }

    public boolean updateCustomerCity(int id, String city) throws SQLException{
        Customer existing = customerDAO.getCustomerById(id);
        if (existing == null){
            return false;
        }
        customerDAO.updateCustomerCity(id, city);
        return true;
    }

    public boolean deleteCustomerById(int id) throws SQLException{
        Customer existing = customerDAO.getCustomerById(id);
        if (existing == null){
            return false;
        }
        customerDAO.deleteCustomer(id);
        return true;
    }


}
