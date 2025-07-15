package com.example.spring_batch_demo.spring_batch_demo.dto;

import java.io.Serializable;
import java.time.LocalDate;

public class EmployeeDTO implements Serializable {
  private static final long serialVersionUID = 1L;
  private String employeeId;
  private String firstName;
  private String lastName;
  private String email;
  private LocalDate dateOfBirth;

  public EmployeeDTO() {
  }

  public EmployeeDTO(String employeeId, String firstName, String lastName, String email, LocalDate dateOfBirth) {
    this.employeeId = employeeId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.email = email;
    this.dateOfBirth = dateOfBirth;
  }

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
}
