package com.example.spring_batch_demo.spring_batch_demo.model;

import java.io.Serializable;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "employees")
public class Employee implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  private String employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private LocalDate dateOfBirth;
  private Integer age;

  // Default constructor
  public Employee() {
  }

  // Constructor with parameters
  public Employee(String employeeId, String firstName, String lastName, String email, LocalDate dateOfBirth, Integer age) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
    this.age = age;
  }

  // Getter and Setter methods
  public String getEmployeeId() {
    return employeeId;
  }

  public void setEmployeeId(String employeeId) {
    this.employeeId = employeeId;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public int getAge() {
    return age;
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  // toString method
  @Override
  public String toString() {
    return "Employee{" +
        "employeeId='" + employeeId + '\'' +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", email='" + email + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", age=" + age +
        '}';
  }

  // Builder pattern for Employee class
  public static class Builder {
    private String employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dateOfBirth;
    private Integer age;

    // Builder methods to set fields
    public Builder employeeId(String employeeId) {
      this.employeeId = employeeId;
      return this;
    }

    public Builder firstName(String firstName) {
      this.firstName = firstName;
      return this;
    }

    public Builder lastName(String lastName) {
      this.lastName = lastName;
      return this;
    }

    public Builder email(String email) {
      this.email = email;
      return this;
    }

    public Builder dateOfBirth(LocalDate dateOfBirth) {
      this.dateOfBirth = dateOfBirth;
      return this;
    }

    public Builder age(Integer age) {
      this.age = age;
      return this;
    }

    // Build method to create the Employee instance
    public Employee build() {
      return new Employee(employeeId, firstName, lastName, email, dateOfBirth, age);
    }
  }
}
