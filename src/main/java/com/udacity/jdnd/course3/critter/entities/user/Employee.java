package com.udacity.jdnd.course3.critter.entities.user;

import javax.persistence.*;

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

}
