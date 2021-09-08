package com.crm.app.repository;

import java.util.List;

import com.crm.app.model.CustomerDto;

import io.ebean.DB;
import io.ebean.Database;
import io.ebean.Expr;

/**
 * An EBean implementation of customer repository.
 */
public class EBeanCustomerRepository implements ICustomerRepository {

    private Database database = DB.getDefault();

    /**
     * {@inheritDoc}
     */
    @Override
    public void saveCustomer(final CustomerDto customerDto) {
        database.save(customerDto);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CustomerDto updateCustomer(final long id, final CustomerDto customerDto) {
        final CustomerDto customerForUpdate = findCustomerById(id);
        customerForUpdate.copy(customerDto);
        database.save(customerForUpdate);
        return customerForUpdate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteCustomer(final long id) {
        DB.delete(CustomerDto.class, id);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<CustomerDto> findCustomersWithTextInName(final String text) {
        return database.find(CustomerDto.class).select("id, firstName, lastName, birthDate").where()
                .or(Expr.contains("firstName", text), Expr.contains("lastName", text)).findList();
    }

    @Override
    public void deleteCustomers() {
        DB.find(CustomerDto.class).delete();

    }

    /**
     * Finds a customer by identification number.
     * 
     * @param id
     *            The customer identification number
     * @return The customer record that matches the id.
     */
    private CustomerDto findCustomerById(final long id) {
        return DB.find(CustomerDto.class, id);
    }

}