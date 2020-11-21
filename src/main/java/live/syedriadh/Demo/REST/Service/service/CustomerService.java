package live.syedriadh.Demo.REST.Service.service;

import live.syedriadh.Demo.REST.Service.entity.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<Customer> getAllCustomers();
    Optional<Customer> getCustomerById(int id);
    Object getCustomerByName(String name);
    Object getCustomerByPhone(String phone);
    Object getCustomerByEmail(String email);
    Customer addNewCustomer(Customer customer);
    Optional<Customer> updateCustomer(int id, Customer customer);
}