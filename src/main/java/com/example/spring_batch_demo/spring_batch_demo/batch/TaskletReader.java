package com.example.spring_batch_demo.spring_batch_demo.batch;

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
import org.springframework.batch.repeat.RepeatStatus;

import com.example.spring_batch_demo.spring_batch_demo.dto.EmployeeDTO;
import com.example.spring_batch_demo.spring_batch_demo.utils.FileUtils;

public class TaskletReader implements Tasklet, StepExecutionListener {
  private final Logger logger = LoggerFactory.getLogger(TaskletReader.class);
  private List<EmployeeDTO> employeeDTOList;
  private FileUtils fileUtil;

  @Override
  public void beforeStep(StepExecution stepExecution) {
    employeeDTOList = new ArrayList<>();
    fileUtil = new FileUtils("employees.csv");
    logger.info("Tasklet Reader initialized.............................................................");
  }

  @Override
  public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
    EmployeeDTO employeeDTO = fileUtil.readLine();

    while (employeeDTO != null) {
      employeeDTOList.add(employeeDTO);
      logger.info("Read data: " + employeeDTO);
      employeeDTO = fileUtil.readLine();
    }
    return RepeatStatus.FINISHED;
  }

  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    fileUtil.closeReader();
    stepExecution
        .getJobExecution()
        .getExecutionContext()
        .put("employeeDTOData", employeeDTOList);

    logger.info("Tasklet Reader ended.....................................................................");

    return ExitStatus.COMPLETED;
  }

}
