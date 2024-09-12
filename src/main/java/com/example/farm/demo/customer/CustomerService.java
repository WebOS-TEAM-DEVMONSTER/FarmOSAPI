// package com.example.farm.demo.customer;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.util.Date;
// import java.util.List;
// import java.util.Optional;

// @Service
// public class CustomerService {

//     @Autowired
//     private CustomerRepository customerRepository;

//     // 모든 고객 정보 조회
//     public List<Customer> getAllCustomers() {
//         return customerRepository.findAll();
//     }

//     // 고객 ID로 고객 정보 조회
//     public Optional<Customer> getCustomerById(String id) {
//         return customerRepository.findById(id);
//     }

//     //고객 생성
//     public Customer createCustomer(Customer customer) {
//         customer.setCreatedAt(new Date());
//         customer.setUpdatedAt(new Date());
//         return customerRepository.save(customer);
//     }

//     // 고객 정보 수정
//     public Customer updateCustomer(String id, Customer customerDetails) {
//         Optional<Customer> customerOptional = customerRepository.findById(id);

//         if (customerOptional.isPresent()) {
//             Customer customer = customerOptional.get();
//             customer.setName(customerDetails.getName());
//             customer.setPhoneNumber(customerDetails.getPhoneNumber());
//             customer.setProfileImage(customerDetails.getProfileImage());
//             customer.setUpdatedAt(new Date());
//             return customerRepository.save(customer);
//         } else {
//             throw new RuntimeException("Customer not found with id " + id);
//         }
//     }

//     //고객 정보 삭제
//     public void deleteCustomer(String id) {
//         customerRepository.deleteById(id);
//     }
// }