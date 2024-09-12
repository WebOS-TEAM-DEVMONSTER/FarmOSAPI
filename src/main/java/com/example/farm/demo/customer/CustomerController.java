// package com.example.farm.demo.customer;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;
// import java.util.Optional;

// @RestController
// @RequestMapping("/customers")
// public class CustomerController {

//     @Autowired
//     private CustomerService customerService;

//     @GetMapping
//     public List<Customer> getAllCustomers() {
//         return customerService.getAllCustomers();
//     }

//     @GetMapping("/{id}")
//     public Optional<Customer> getCustomerById(@PathVariable("id") String id) {
//         return customerService.getCustomerById(id);
//     }

//     @PostMapping
//     public Customer createCustomer(@RequestBody Customer customer) {
//         return customerService.createCustomer(customer);
//     }

//     @PutMapping("/{id}")
//     public Customer updateCustomer(@PathVariable("id") String id, @RequestBody Customer customerDetails) {
//         return customerService.updateCustomer(id, customerDetails);
//     }

//     @DeleteMapping("/{id}")
//     public void deleteCustomer(@PathVariable("id") String id) {
//         customerService.deleteCustomer(id);
//     }
// }