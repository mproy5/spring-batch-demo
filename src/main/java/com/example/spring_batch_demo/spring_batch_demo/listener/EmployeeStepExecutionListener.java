package com.example.spring_batch_demo.spring_batch_demo.listener;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.StepExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.spring_batch_demo.spring_batch_demo.processor.EmployeeProcessor;

@Component
public class EmployeeStepExecutionListener extends StepExecutionListenerSupport {

  private final EmployeeProcessor employeeProcessor;

  @Autowired
  public EmployeeStepExecutionListener(EmployeeProcessor employeeProcessor) {
    this.employeeProcessor = employeeProcessor;
  }

  @Override
  public void beforeStep(StepExecution stepExecution) {
    String startDate = stepExecution.getJobExecution().getJobParameters().getString("startDate");
    String endDate = stepExecution.getJobExecution().getJobParameters().getString("endDate");

//    employeeProcessor.setJobParameters(startDate, endDate);
  }
}

