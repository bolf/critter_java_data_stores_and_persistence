package com.udacity.jdnd.course3.critter.controller;

import com.udacity.jdnd.course3.critter.entities.pet.Pet;
import com.udacity.jdnd.course3.critter.entities.user.Employee;
import com.udacity.jdnd.course3.critter.repository.CustomerRepository;
import com.udacity.jdnd.course3.critter.entities.user.Customer;
import com.udacity.jdnd.course3.critter.entities.user.DTOs.CustomerDTO;
import com.udacity.jdnd.course3.critter.entities.user.DTOs.EmployeeDTO;
import com.udacity.jdnd.course3.critter.entities.user.DTOs.EmployeeRequestDTO;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import com.udacity.jdnd.course3.critter.repository.PetRepository;
import com.udacity.jdnd.course3.critter.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @Autowired
    private PetRepository petRepository;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(@RequestBody CustomerDTO customerDTO){
        customerDTO.setId(userService.saveCustomer(convertCustomerDTOToCustomer(customerDTO)).getId());
        return customerDTO;
    }

    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        userService.getAllCustomers().forEach(customer -> customerDTOS.add(convertCustomerToCustomerDTO(customer)));
        return customerDTOS;
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Customer customer = userService.findCustomerByPetId(petId);
        if(customer != null){
            return convertCustomerToCustomerDTO(customer);
        }
        return null;
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        employeeDTO.setId(userService.saveEmployee(convertEmployeeDTOToEmployee(employeeDTO)).getId());
        return employeeDTO;
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = userService.getEmployeeById(employeeId);
        if(employee!=null){
            return convertEmployeeToEmployeeDTO(employee);
        }
        return null;
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        userService.setEmployeeAvailability(daysAvailable, employeeId);
    }

    /** returns all saved employees that have the requested availability
     *  and skills and none that do not*/
    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        return userService.findEmployeesForService(employeeDTO.getSkills(),employeeDTO.getDate())
                .stream().map(this::convertEmployeeToEmployeeDTO).collect(Collectors.toList());
    }

    /**
     * Transformation DTO -> User & User -> DTO
     */
    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO,customer);
        return customer;
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        if (customer.getPets() != null) {
            customerDTO.setPetIds(new ArrayList<>());
            customer.getPets().forEach(pet -> customerDTO.getPetIds().add(pet.getId()));
        }
        return customerDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        return employee;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee,employeeDTO);
        return employeeDTO;
    }

}
