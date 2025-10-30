package Control;

import Helpers.IMenu;
import Helpers.MenuBuilder;
import Helpers.SafeInput;
import Helpers.TableFormatter;
import models.Customer;
import service.CustomerService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


public class CustomerControl implements IMenu {

    private final CustomerService customerService;
    private final SafeInput input;

    public CustomerControl(CustomerService customerService){
        this.customerService = customerService;
        this.input = new SafeInput(new Scanner(System.in));
    }
    @Override
    public void menu() {
        MenuBuilder roomMenu = new MenuBuilder("\n=== Customer Menu ===", "Back to main menu");
        roomMenu.addItem("Add Customer", this::addCustomer)
                .addItem("Show all Customers", this::showAllCustomers)
                .addItem("Find Customer by email", this::findCustomerByEmail)
                .addItem("Update Customer city", this::updateCustomerCity)
                .addItem("Delete customer", this::deleteCustomer);
        roomMenu.runMenu();

    }

    private void deleteCustomer() {
        showAllCustomers();
        int id = input.nextInt("Enter Id of customer to delete: ");
        input.getScanner().nextLine();
        if (id<1){
            System.out.println("Invalid Id");
            return;
        }

        try {
            boolean success = customerService.deleteCustomerById(id);
            if (success){
                System.out.println("The customer was deleted ");
            }else {
                System.out.println("There is no customer with that id ");
            }
        }catch (SQLException e){
            System.out.println("There was an error deleting customer: "+e.getMessage());
        }
    }

    private void updateCustomerCity() {
        showAllCustomers();
        int id = input.nextInt("Enter customer Id: ");
        input.getScanner().nextLine();
        if (id < 1){
            System.out.println("Invalid Id");
            return;
        }


        String newCity = input.nextLine("Enter updated city: ");
        if (newCity.isBlank()){
            System.out.println("Invalid city");
            return;
        }

        try{
            boolean success = customerService.updateCustomerCity(id,newCity);
            if (success){
                System.out.println("The city was changed ");
            }else {
                System.out.println("Customer with that id doesn't exist ");
            }
        } catch (SQLException e) {
            System.out.println("Error changing city: " + e.getMessage());
        }

    }

    private void findCustomerByEmail() {
        String email = input.nextLine("Enter customer email: ").trim();
        if (!email.contains("@")) {
            System.out.println("Invalid email");
            return;
        }
        try{
            Customer customer = customerService.findCustomerByEmail(email);
            if (customer != null){
                System.out.println("Id: " + customer.getCustomerId());
                System.out.println("Name: " + customer.getName());
                System.out.println("City: " + customer.getCity());
                System.out.println("Email: " + customer.getEmail());
            }else {
                System.out.println("Customer not found");
            }

        }catch (SQLException e){
            System.out.println("Error fetching customer: " + e.getMessage());
        }
    }

    private void showAllCustomers() {
        try{
            List<Customer> customers = customerService.showAllCustomers();

            if (customers.isEmpty()){
                System.out.println("No customers found");
                return;
            }

            TableFormatter table = new TableFormatter()
                    .setTabs(5,20,20,30)
                    .setBoldHeader(true);

            System.out.println(table.formatHeader("ID", "Name", "City", "Email"));
            System.out.println(table.formatSeparator());

            for (Customer c : customers){
                System.out.println(table.formatNextRow(
                        String.valueOf(c.getCustomerId()),
                        c.getName(),
                        c.getCity(),
                        c.getEmail()
                ));
            }
        }catch (SQLException e){
            System.out.println("Error fetching customers: "+e.getMessage());
        }
    }

    private void addCustomer(){
        String name = input.nextLine("Enter customer name: ");
        String email = input.nextLine("Enter customer email: ").trim();
        if (!email.contains("@")) {
            System.out.println("Invalid email");
            return;
        }

        String city = input.nextLine("Enter customer city: ");

        Customer customer = new Customer(name, city, email);

        try {
            boolean success = customerService.addCustomer(customer);
            if (success){
                System.out.println("Customer added successfully");
            } else {
                System.out.println("A customer with that email already exists");
            }

        }catch (SQLException e){
            System.out.println("Error adding customer: "+e.getMessage());
        }

    }
}
