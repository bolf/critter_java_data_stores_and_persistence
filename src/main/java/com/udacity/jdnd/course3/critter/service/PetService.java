package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private CustomerRepository customerRepository;

    public Pet savePet(Pet pet){
        return petRepository.save(pet);
    }

    public Pet getPetById(Long id){
        try{
            return petRepository.findById(id).get();
        } catch (NoSuchElementException e) {
            return null;
        }
    }

    public List<Pet> getPetsByOwner(Long id) {
        return petRepository.getPetsByOwnerId(id);
    }

    public Iterable<Pet> getAllPets(){
        return petRepository.findAll();
    }


}
