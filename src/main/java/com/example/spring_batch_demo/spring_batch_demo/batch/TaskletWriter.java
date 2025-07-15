package com.example.spring_batch_demo.spring_batch_demo.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring_batch_demo.spring_batch_demo.model.Employee;
import com.example.spring_batch_demo.spring_batch_demo.repo.EmployeeRepository;

public class TaskletWriter implements Tasklet, StepExecutionListener {
  private final Logger logger = LoggerFactory.getLogger(TaskletWriter.class);
  private List<Employee> employeeList;
  private EmployeeRepository employeeRepository;

  public TaskletWriter(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Override
  public void beforeStep(StepExecution stepExecution) {
    ExecutionContext executionContext = stepExecution
        .getJobExecution()
        .getExecutionContext();

    employeeList = (List<Employee>) executionContext.get("employeeData");
    logger.info("Tasklet Writer initialized..................................................");
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    employeeRepository.saveAll(employeeList);
    logger.info("Write data {} ", employeeList);
    return RepeatStatus.FINISHED;
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    // If any after step is required
    logger.info("Tasklet Writer ended............................................................");
    return ExitStatus.COMPLETED;
  }
}
