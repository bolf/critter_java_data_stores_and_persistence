package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entitie.pet.Pet;
import com.udacity.jdnd.course3.critter.entitie.pet.PetDTO;
import com.udacity.jdnd.course3.critter.service.PetService;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Handles web requests related to Pets.
 */
@RestController
@RequestMapping("/pet")
public class PetController {
    @Autowired
    private PetService petService;
    @Autowired
    private UserService userService;

    @PostMapping
    public PetDTO savePet(@RequestBody PetDTO petDTO) {
        Pet pet = convertPetDTOToPet(petDTO);
        Pet savedPet = petService.savePet(pet);
        if(savedPet.getOwner() != null) {
            savedPet.getOwner().addPet(savedPet);
        }
        return convertPetToPetDTO(savedPet);
    }

    @GetMapping("/{petId}")
    public PetDTO getPet(@PathVariable long petId) {
        Pet pet = petService.getPetById(petId);
        if (pet != null) {
            return convertPetToPetDTO(pet);
        }
        return null;
    }

    @GetMapping
    public List<PetDTO> getPets(){
        List<PetDTO> petDTOs = new ArrayList<>();
        petService.getAllPets().forEach(pet -> {petDTOs.add(convertPetToPetDTO(pet));});
        return petDTOs;
    }

    @GetMapping("/owner/{ownerId}")
    public List<PetDTO> getPetsByOwner(@PathVariable long ownerId) {
        List<PetDTO> petDTOs = new ArrayList<>();
        petService.getPetsByOwner(ownerId).forEach(pet -> {petDTOs.add(convertPetToPetDTO(pet));});
        return petDTOs;
    }

    private Pet convertPetDTOToPet(PetDTO petDTO){
        Pet pet = new Pet();
        BeanUtils.copyProperties(petDTO,pet);
        if(petDTO.getOwnerId() > 0){
            pet.setOwner(userService.getCustomerById(petDTO.getOwnerId()));
        }
        return pet;
    }

    private PetDTO convertPetToPetDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        BeanUtils.copyProperties(pet,petDTO);
        petDTO.setOwnerId(pet.getOwner().getId());
        return petDTO;
    }
}
