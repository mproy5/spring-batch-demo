package com.example.spring_batch_demo.spring_batch_demo.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import com.example.spring_batch_demo.spring_batch_demo.dto.EmployeeDTO;
import com.example.spring_batch_demo.spring_batch_demo.utils.DateUtils;

public class EmployeeFileRowMapper implements FieldSetMapper<EmployeeDTO> {

  @Override
  public EmployeeDTO mapFieldSet(FieldSet fieldSet) {
    EmployeeDTO employeeDTO = new EmployeeDTO();
    employeeDTO.setEmployeeId(fieldSet.readString("employeeId"));
    employeeDTO.setFirstName(fieldSet.readString("firstName"));
    employeeDTO.setLastName(fieldSet.readString("lastName"));
    employeeDTO.setEmail(fieldSet.readString("email"));
    employeeDTO.setDateOfBirth(DateUtils.parseDate(fieldSet.readString("dateOfBirth")));
    return employeeDTO;
  }
}
