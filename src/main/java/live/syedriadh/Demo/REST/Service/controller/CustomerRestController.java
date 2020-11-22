package live.syedriadh.Demo.REST.Service.controller;

import live.syedriadh.Demo.REST.Service.entity.Customer;
import live.syedriadh.Demo.REST.Service.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/customers", headers = {"Accept=application/json,application/xml"},
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class CustomerRestController {
    private final CustomerService customerService;

    @Autowired
    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/all")
    @ResponseBody
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/by-id")
    @ResponseBody
    public Optional<Customer> getCustomerById(@RequestParam("id") int id) {
        return customerService.getCustomerById(id);
    }

    @GetMapping("/by-name")
    @ResponseBody
    public Object getCustomerByName(@RequestParam("name") String name) {
        return customerService.getCustomerByName(name);
    }

    @GetMapping("/by-phone")
    @ResponseBody
    public Object getCustomerByPhone(@RequestParam("phone") String phone) {
        return customerService.getCustomerByPhone(phone);
    }

    @GetMapping("/by-email")
    @ResponseBody
    public Object getCustomerByEmail(@RequestParam("email") String email) {
        return customerService.getCustomerByEmail(email);
    }

    @PostMapping("/new")
    @ResponseBody
    public Customer addNewCustomer(@RequestBody Customer customer) {
        return customerService.addNewCustomer(customer);
    }

    @PatchMapping("/update")
    @ResponseBody
    public Optional<Customer> updateCustomer(@RequestParam("id") int id, @RequestBody Customer customer) {
        return customerService.updateCustomer(id, customer);
    }
}
