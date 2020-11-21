package live.syedriadh.Demo.REST.Service.service;

import live.syedriadh.Demo.REST.Service.entity.Customer;
import live.syedriadh.Demo.REST.Service.repository.CustomerJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerJpaRepository customerJpaRepository;

    @Autowired
    public CustomerServiceImpl(CustomerJpaRepository customerJpaRepository) {
        this.customerJpaRepository = customerJpaRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Customer::getId)).collect(Collectors.toList());
    }

    @Override
    public Optional<Customer> getCustomerById(int id) {
        return customerJpaRepository.findById(id);
    }

    @Override
    public Object getCustomerByName(String name) {
        List<Customer> customerList = customerJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Customer::getName)).collect(Collectors.toList());

        List<Customer> matchedCustomers = new ArrayList<>();

        for (Customer customer : customerList) {
            if (customer.getName().equals(name)) {
                matchedCustomers.add(customer);
            }
        }

        if (matchedCustomers.size() == 1)
            return matchedCustomers.get(0);
        else if (matchedCustomers.size() == 0)
            return null;
        else
            return matchedCustomers;
    }

    @Override
    public Object getCustomerByPhone(String phone) {
        List<Customer> customerList = customerJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Customer::getPhone)).collect(Collectors.toList());

        List<Customer> matchedCustomers = new ArrayList<>();

        for (Customer customer : customerList) {
            if (customer.getPhone().equals(phone)) {
                matchedCustomers.add(customer);
            }
        }

        if (matchedCustomers.size() == 1)
            return matchedCustomers.get(0);
        else if (matchedCustomers.size() == 0)
            return null;
        else
            return matchedCustomers;
    }

    @Override
    public Object getCustomerByEmail(String email) {
        List<Customer> customerList = customerJpaRepository.findAll().stream().sorted(
                Comparator.comparing(Customer::getEmail)).collect(Collectors.toList());

        List<Customer> matchedCustomers = new ArrayList<>();

        for (Customer customer : customerList) {
            if (customer.getEmail().equals(email)) {
                matchedCustomers.add(customer);
            }
        }

        if (matchedCustomers.size() == 1)
            return matchedCustomers.get(0);
        else if (matchedCustomers.size() == 0)
            return null;
        else
            return matchedCustomers;
    }

    @Override
    public Customer addNewCustomer(Customer customer) {
        return customerJpaRepository.saveAndFlush(customer);
    }

    @Override
    public Optional<Customer> updateCustomer(int id, Customer updatedCustomer) {
        Customer matchedCustomer = new Customer();

        if (customerJpaRepository.existsById(id)) {
            matchedCustomer = customerJpaRepository.getOne(id);

            if (updatedCustomer.getPhone() != null) {
                matchedCustomer.setPhone(updatedCustomer.getPhone());
            }

            if (updatedCustomer.getEmail() != null) {
                matchedCustomer.setEmail(updatedCustomer.getEmail());
            }

            if (updatedCustomer.getAddress() != null) {
                matchedCustomer.setAddress(updatedCustomer.getAddress());
            }

            customerJpaRepository.saveAndFlush(matchedCustomer);
        }

        return Optional.of(matchedCustomer);
    }
}
