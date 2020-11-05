package com.udacity.jdnd.course3.critter.entities.user;

import com.udacity.jdnd.course3.critter.entities.pet.Pet;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Customer and Employee have little in common,
 * I decided not to use inheritance at all for the sake of simplicity.
 * So, Customer and Employee are totally standalone entities,
 * each has individual table in the database.
 */

@Entity
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String notes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner", cascade = {CascadeType.ALL})
    private List<Pet> pets;

    public void addPet(Pet pet){
        if(pets == null){
            pets = new ArrayList<>();
        }
        pets.add(pet);
        pet.setOwner(this);
    }

    public Customer(){}

    public Customer(String name, String phoneNumber, String notes, List<Pet> pets) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.notes = notes;
        this.pets = pets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Pet> getPets() {
        return pets;
    }

    public void setPets(List<Pet> pets) {
        this.pets = pets;
    }
}
