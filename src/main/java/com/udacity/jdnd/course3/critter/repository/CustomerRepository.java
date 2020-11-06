package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.entities.user.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CustomerRepository extends CrudRepository<Customer,Long> {
    public Customer findCustomerByPetsId(Long id);
}
