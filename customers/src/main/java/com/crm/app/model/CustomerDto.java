package com.crm.app.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is an abstraction of a business customer.
 */
@Entity
@Table(name = "customer")
public class CustomerDto {

    /**
     * The customer identification number.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    /**
     * The first name of the customer.
     */
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the customer.
     */
    @Column(name = "last_name")
    private String lastName;

    /**
     * The birth date of the customer.
     */
    @Column(name = "birth_date")
    private LocalDate birthDate;

    /**
     * Gets the id of this customer.
     * 
     * @return The id of this customer.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of this customer.
     * 
     * @param id
     *            The id to set.
     */
    public void setId(final long id) {
        this.id = id;
    }

    /**
     * Retrieves the first name of the customer.
     * 
     * @return The first name of the customer.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the customer.
     * 
     * @param firstName
     *            The first name to set.
     */
    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    /**
     * Retrieves the last name of the customer.
     * 
     * @return The last name of the customer.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the customer.
     * 
     * @param lastName
     *            The last name to set.
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * Retrieves the birth date of the customer.
     * 
     * @return
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Sets the birth date of the customer.
     * 
     * @param birthDate
     *            The date to set.
     */
    public void setBirthDate(final LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Copies the information of another customer.
     * 
     * @param another
     *            Another customer to copy details from.
     */
    public void copy(final CustomerDto another) {
        setFirstName(another.firstName);
        setLastName(another.lastName);
        setBirthDate(another.birthDate);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(final Object object) {
        if (object instanceof CustomerDto) {
            final CustomerDto another = (CustomerDto) object;
            return Objects.equals(firstName, another.firstName) && Objects.equals(lastName, another.lastName)
                    && Objects.equals(birthDate, another.birthDate);
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthDate);
    }

}
