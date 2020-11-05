package com.udacity.jdnd.course3.critter.entities.user;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.util.Set;

/**
 * Customer and Employee have little in common,
 * I decided not to use inheritance at all for the sake of simplicity.
 * So, Customer and Employee are totally standalone entities,
 * each has individual table in the database.
 */

@Entity
@Table(name="employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ElementCollection
    private Set<EmployeeSkill> skills;
    @ElementCollection
    private Set<DayOfWeek> daysAvailable;

    public Employee(){}

    public Employee(String name, Set<EmployeeSkill> skills, Set<DayOfWeek> daysAvailable) {
        this.name = name;
        this.skills = skills;
        this.daysAvailable = daysAvailable;
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

    public Set<EmployeeSkill> getSkills() {
        return skills;
    }

    public void setSkills(Set<EmployeeSkill> skills) {
        this.skills = skills;
    }

    public Set<DayOfWeek> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(Set<DayOfWeek> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }
}