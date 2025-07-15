package com.example.spring_batch_demo.spring_batch_demo.processor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;
import org.springframework.batch.core.JobExecution;

import com.example.spring_batch_demo.spring_batch_demo.dto.EmployeeDTO;
import com.example.spring_batch_demo.spring_batch_demo.model.Employee;

@StepScope
@Component
public class EmployeeProcessor implements ItemProcessor<EmployeeDTO, Employee> {
//  private LocalDate start;
//  private LocalDate end;
//
//  // Setters for job parameters
//  public void setJobParameters(String startDate, String endDate) {
//    if (startDate != null) {
//      this.start = LocalDate.parse(startDate);
//    } else {
//      this.start = LocalDate.of(1900, 1, 1); // default start date
//    }
//
//    if (endDate != null) {
//      this.end = LocalDate.parse(endDate);
//    } else {
//      this.end = LocalDate.now(); // default end date
//    }
//  }

  @Override
  public Employee process(EmployeeDTO employeeDTO) throws Exception {
    Employee employee = new Employee();
    employee.setEmployeeId(employeeDTO.getEmployeeId());
    employee.setFirstName(employeeDTO.getFirstName());
    employee.setLastName(employeeDTO.getLastName());
    employee.setEmail(employeeDTO.getEmail());
    employee.setDateOfBirth(employeeDTO.getDateOfBirth());
    employee.setAge(Long.valueOf(ChronoUnit.YEARS.between(employeeDTO.getDateOfBirth(), LocalDate.now())).intValue());
    System.out.println("inside processor " + employee.toString());
//    if (employee.getDateOfBirth().isBefore(start) || employee.getDateOfBirth().isAfter(end)) {
//      System.out.println("!!!! Date of birth doesn't fit the range, so skip the employee !!!!" + employee.toString());
//      return null;
//    }
    return employee;
  }
}
