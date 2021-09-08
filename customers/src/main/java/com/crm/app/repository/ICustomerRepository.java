package com.crm.app.repository;

import java.util.List;

import com.crm.app.model.CustomerDto;

/**
 * Interface for a customer data repository that will handle the CRUD
 * operations.
 */
public interface ICustomerRepository {

    /**
     * Saves a new customer data to the repository.
     * 
     * @param customerDto
     *            The customer data to save.
     */
    void saveCustomer(final CustomerDto customerDto);

    /**
     * Updates a customer that matches the id with the new details provided.
     * 
     * @param id
     *            The customer identification number.
     * @param customerDto
     *            A customer instance that holds the update details.
     * @return
     */
    CustomerDto updateCustomer(final long id, final CustomerDto customerDto);

    /**
     * Deletes a customer that matches the provided id.
     * 
     * @param customerId
     *            The customer identification number.
     */
    void deleteCustomer(final long customerId);

    /**
     * Matches customers whose first or last name contains a given text.
     * 
     * @param text
     *            The text to be used in matching customer records.
     * @return A list of customers that matches the criterion.
     */
    List<CustomerDto> findCustomersWithTextInName(final String text);

    /**
     * Delete all customer records.
     */
    void deleteCustomers();
}