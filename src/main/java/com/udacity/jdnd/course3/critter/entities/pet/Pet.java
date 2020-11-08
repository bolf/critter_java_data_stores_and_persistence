package com.udacity.jdnd.course3.critter.entities.pet;

import com.udacity.jdnd.course3.critter.entities.user.Customer;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name="pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private PetType type;
    @Nationalized
    private String name;
    private LocalDate birthDate;
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    private Customer owner;

    public Pet(){}

    public Pet(PetType type, String name, LocalDate birthDate, String notes, Customer owner) {
        this.type = type;
        this.name = name;
        this.birthDate = birthDate;
        this.notes = notes;
        this.owner = owner;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Customer getOwner() {
        return owner;
    }

    public void setOwner(Customer owner) {
        this.owner = owner;
    }
}
