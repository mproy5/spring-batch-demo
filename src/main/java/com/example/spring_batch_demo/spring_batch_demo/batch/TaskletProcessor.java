package com.example.spring_batch_demo.spring_batch_demo.batch;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

import com.example.spring_batch_demo.spring_batch_demo.dto.EmployeeDTO;
import com.example.spring_batch_demo.spring_batch_demo.model.Employee;

public class TaskletProcessor implements Tasklet, StepExecutionListener {
  private final Logger logger = LoggerFactory.getLogger(TaskletProcessor.class);
  private List<EmployeeDTO> employeeDTOList;
  private List<Employee> employeeList = new ArrayList<>();

  @Override
  public void beforeStep(StepExecution stepExecution) {
    ExecutionContext executionContext = stepExecution.getJobExecution().getExecutionContext();
    employeeDTOList = (List<EmployeeDTO>) executionContext.get("employeeDTOData");
    logger.info("Tasklet Processor initialized...................................................");
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    for (EmployeeDTO employeeDTO : employeeDTOList) {
      Employee employee = new Employee();
      employee.setEmployeeId(employeeDTO.getEmployeeId());
      employee.setFirstName(employeeDTO.getFirstName());
      employee.setLastName(employeeDTO.getLastName());
      employee.setEmail(employeeDTO.getEmail());
      employee.setDateOfBirth(employeeDTO.getDateOfBirth());
      employee.setAge(Long.valueOf(ChronoUnit.YEARS.between(employeeDTO.getDateOfBirth(), LocalDate.now())).intValue());
      logger.info("Calculate employee with age {}", employee);
      employeeList.add(employee);
    }
    return RepeatStatus.FINISHED;
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    stepExecution
        .getJobExecution()
        .getExecutionContext()
        .put("employeeData", employeeList);
    logger.info("Tasklet Processor ended..............................................................");
    return ExitStatus.COMPLETED;
  }
}
