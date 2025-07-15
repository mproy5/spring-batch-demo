package com.example.spring_batch_demo.spring_batch_demo.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

import com.example.spring_batch_demo.spring_batch_demo.batch.TaskletWriter;

public class EmployeeImportJobListener implements JobExecutionListener {
  private final Logger logger = LoggerFactory.getLogger(TaskletWriter.class);

  @Override
  public void beforeJob(JobExecution jobExecution) {
    logger.info("Job {} started", jobExecution.getJobInstance().getJobName());
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      logger.info("Job {} completed", jobExecution.getJobInstance().getJobName());
    } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
      logger.info("Job {} failed", jobExecution.getJobInstance().getJobName());
    }
  }
}
