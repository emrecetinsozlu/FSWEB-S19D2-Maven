package com.workintech.s18d4.controller;


import com.workintech.s18d4.entity.Customer;
import com.workintech.s18d4.service.CustomerService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;


    @GetMapping
    public List<Customer> findAll(){
        return customerService.findAll();

    }

    @GetMapping("/{id}")
    public Customer getById(@PathVariable Long id){
        return customerService.findById(id);
    }

    @PostMapping
    public Customer save(@RequestBody Customer customer){
        return customerService.save(customer);
    }

    @PutMapping
    public Customer update(@RequestBody Customer customer, Long id){
        Customer existingCustomer = customerService.findById(id);
        if(existingCustomer == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer not found");
        }
        existingCustomer.setFirstName(customer.getFirstName());
        existingCustomer.setLastName(customer.getLastName());
        existingCustomer.setEmail(customer.getEmail());
        existingCustomer.setAddress(customer.getAddress());
        existingCustomer.setSalary(customer.getSalary());
        return customerService.save(existingCustomer);
    }

    @DeleteMapping("/{id}")
    public Customer delete(@PathVariable Long id){
        return customerService.delete(id);
    }
}
