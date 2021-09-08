package com.crm.app.repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.crm.app.model.CustomerDto;

import junit.framework.TestCase;

/**
 * Test cases for customer repository CRUD operations.
 */
public class CustomerRepositoryTests extends TestCase {

    /**
     * The interface for the persistence of customer records.
     */
    ICustomerRepository repository;

    /**
     * Creates an implementation of a customer repository.
     * 
     * {@inheritDoc}
     */
    @Before
    public void setUp() {
        repository = new EBeanCustomerRepository(); // for this test we use the EBean implementation
    }

    /**
     * Tests saving of a new customer. After saving, a record is matched by first
     * name from the repository and asserts that the fetched details are the same as
     * what were initially saved.
     */
    @Test
    public void testCreateCustomer() {

        final String firstName = "Soliman";
        final CustomerDto customer = createCustomer(firstName, "Ortega");
        repository.saveCustomer(customer);
        matchNameAndAssertDetails(firstName, customer);

        repository.deleteCustomers(); // clean up
    }

    /**
     * Tests updating details of a saved customer.
     */
    @Test
    public void testUpdateCustomer() {
        final CustomerDto customerOne = createCustomer("Soliman", "Ortega");
        repository.saveCustomer(customerOne);
        final String lastName = "Ortegz";
        final CustomerDto newDetails = createCustomer("Sol", lastName);
        repository.updateCustomer(customerOne.getId(), newDetails);
        matchNameAndAssertDetails(lastName, newDetails);

        repository.deleteCustomers(); // clean up
    }

    /**
     * Tests deletion of a customer record.
     */
    @Test
    public void testDeleteCustomer() {
        /*
         * Find a match from an empty customer repository and assert empty matches
         */
        final String firstName = "Soliman";
        final List<CustomerDto> customersBeforeSave = repository.findCustomersWithTextInName(firstName);
        assertTrue(customersBeforeSave.isEmpty());
        /*
         * Save a customer, find the customer using first name and assert a match
         */
        final CustomerDto customer = createCustomer(firstName, "Ortega");
        repository.saveCustomer(customer);
        final List<CustomerDto> customersAfterSave = repository.findCustomersWithTextInName(firstName);
        assertEquals(1, customersAfterSave.size());
        /*
         * Delete and assert empty
         */
        repository.deleteCustomer(customer.getId());
        final List<CustomerDto> customersAfterDeletion = repository.findCustomersWithTextInName(firstName);
        assertTrue(customersAfterDeletion.isEmpty());

        repository.deleteCustomers(); // clean up
    }

    /**
     * Test finding customers by supplying a text that matches one or neither
     * customer and both customers.
     */
    public void testFindCustomersWithTextInName() {

        final List<CustomerDto> customers = new ArrayList<CustomerDto>();
        final CustomerDto customerOne = createCustomer("Soliman", "Ortega");
        repository.saveCustomer(customerOne);
        customers.add(customerOne);

        final CustomerDto customerTwo = createCustomer("Jo-Anne", "Carpio-Ortega");
        repository.saveCustomer(customerTwo);
        customers.add(customerTwo);

        List<CustomerDto> matches = repository.findCustomersWithTextInName("man"); // match customer one
        assertEquals(1, matches.size());
        assertTrue(matches.contains(customerOne));
        matches.clear();

        matches = repository.findCustomersWithTextInName("Ann"); // match customer two
        assertEquals(1, matches.size());
        assertTrue(matches.contains(customerTwo));
        matches.clear();

        matches = repository.findCustomersWithTextInName("Ortega"); // match both
        assertEquals(customers.size(), matches.size());
        assertTrue(matches.contains(customerOne));
        assertTrue(matches.contains(customerTwo));
        matches.clear();

        matches = repository.findCustomersWithTextInName("Json"); // match neither
        assertTrue(matches.isEmpty());

        repository.deleteCustomers(); // clean up
    }

    /**
     * Uniquely match a customer by the text supplied and asserts that the details
     * of the customer provided are the same as that of the matched customer.
     * 
     * @param textInName
     *            The text to search within either the first or last name.
     * @param custToCompare
     *            The customer against which the unique match is compared.
     */
    private void matchNameAndAssertDetails(final String textInName, final CustomerDto custToCompare) {
        final List<CustomerDto> customers = repository.findCustomersWithTextInName(textInName);
        assertEquals(1, customers.size());
        final CustomerDto dbCustomer = customers.stream().findFirst().get();
        assertTrue(dbCustomer.equals(custToCompare));
    }

    /**
     * Creates a customer who for the purposes of this test is born today :)
     * 
     * @param firstName
     *            The first name of the customer.
     * @param lastName
     *            The last name of the customer.
     * @return {@link CustomerDto} The newly created customer
     */
    private CustomerDto createCustomer(final String firstName, final String lastName) {
        final CustomerDto customer = new CustomerDto();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setBirthDate(LocalDate.now());
        return customer;
    }

}
