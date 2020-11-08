package com.udacity.jdnd.course3.critter.repository;

import com.udacity.jdnd.course3.critter.entities.pet.Pet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PetRepository extends CrudRepository<Pet,Long> {
    List<Pet> getPetsByOwnerId(Long ownerId);
    List<Pet> findByIdIn(Long[] petIds);
}
