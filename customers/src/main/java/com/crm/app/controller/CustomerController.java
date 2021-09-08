package com.crm.app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.app.model.CustomerDto;
import com.crm.app.repository.EBeanCustomerRepository;
import com.crm.app.repository.ICustomerRepository;

/**
 * Controller that handles the REST request and delegates it the customer
 * repository.
 */
@RequestMapping("/api/")
@RestController
public class CustomerController {

    /**
     * Use the EBean repository. Normally the implementation is injected by a
     * container (i.e. Spring, PICO).
     */
    final ICustomerRepository repository = new EBeanCustomerRepository();

    /**
     * Creates a new customer.
     * 
     * @param customerDto
     *            The customer to store to the repository.
     * @return
     */
    @PostMapping("/customers")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDto) {
        repository.saveCustomer(customerDto);
        return new ResponseEntity<>(customerDto, HttpStatus.CREATED);
    }

    /**
     * Updates an existing customer with new details.
     * 
     * @param id
     *            The customer id.
     * @param customerDto
     *            The customer instance that holds the update details
     * @return
     */
    @PutMapping("/customers/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable long id, @RequestBody CustomerDto customerDto) {
        final CustomerDto updatedCustomer = repository.updateCustomer(id, customerDto);
        return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
    }

    /**
     * Deletes the customer that matches the given id.
     * 
     * @param id
     *            The id of the customer.
     * @return
     */
    @DeleteMapping("/customers/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable long id) {
        repository.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Finds customers whose first or last names contains the text.
     * 
     * @param text
     *            Used to match customers whose first or last name contains the
     *            text.
     * @return
     */
    @GetMapping("/customers/{text}")
    public ResponseEntity<List<CustomerDto>> findCustomerWithTextInName(@PathVariable String text) {
        final List<CustomerDto> matchedCustomers = repository.findCustomersWithTextInName(text);
        return new ResponseEntity<>(matchedCustomers, HttpStatus.OK);
    }

}
