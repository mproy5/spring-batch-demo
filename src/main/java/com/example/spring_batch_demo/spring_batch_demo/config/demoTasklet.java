package com.example.spring_batch_demo.spring_batch_demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.spring_batch_demo.spring_batch_demo.batch.TaskletProcessor;
import com.example.spring_batch_demo.spring_batch_demo.batch.TaskletReader;
import com.example.spring_batch_demo.spring_batch_demo.batch.TaskletWriter;
import com.example.spring_batch_demo.spring_batch_demo.repo.EmployeeRepository;

@Configuration
@EnableBatchProcessing
public class demoTasklet {
  EmployeeRepository employeeRepository;

  public demoTasklet(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }

  @Bean
  Step readData(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("readTaskletData", jobRepository)
        .allowStartIfComplete(true)
        .tasklet(new TaskletReader(), transactionManager)
        .build();
  }

  @Bean
  Step processData(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("processTaskletData", jobRepository)
        .allowStartIfComplete(true)
        .tasklet(new TaskletProcessor(), transactionManager)
        .build();
  }

  @Bean
  Step writeData(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new StepBuilder("writeTaskletData", jobRepository)
        .allowStartIfComplete(true)
        .tasklet(new TaskletWriter(employeeRepository), transactionManager)
        .build();
  }

  @Bean
  Job employeeTaskletJob(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
    return new JobBuilder("employeeTaskletJob", jobRepository)
        .start(readData(jobRepository, transactionManager))
        .next(processData(jobRepository, transactionManager))
        .next(writeData(jobRepository, transactionManager))
        .build();
  }
}
