package com.example.spring_batch_demo.spring_batch_demo.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.JobParametersValidator;

public class EmployeeValidator implements JobParametersValidator {
  @Override
  public void validate(JobParameters parameters) throws JobParametersInvalidException {
    String startDate = parameters.getString("startDate");
    String endDate = parameters.getString("endDate");

    if (startDate == null || endDate == null) {
      return;
    }

    try {
      LocalDate start = LocalDate.parse(startDate);
      LocalDate end = LocalDate.parse(endDate);
      if (start.isAfter(LocalDate.now()) || end.isAfter(LocalDate.now())) {
        throw new IllegalArgumentException("Start date/End date cannot be in the future.");
      }
      if (start.isAfter(end)) {
        throw new JobParametersInvalidException("Start date cannot be after end date.");
      }
    } catch (IllegalArgumentException e) {
      throw new IllegalArgumentException(e.getMessage());
    } catch (DateTimeParseException e) {
      throw new JobParametersInvalidException(e.getMessage());
    } catch (Exception e) {
      throw new JobParametersInvalidException("An error occurred while processing the dates.");
    }
  }
}
