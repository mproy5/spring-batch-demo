//package com.example.spring_batch_demo.spring_batch_demo.config;
//
//import org.springframework.batch.core.launch.JobLauncher;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.scheduling.annotation.EnableAsync;
//
//@Configuration
//public class BatchConfig {
//  public JobRepository jobRepository;
//
//  @Autowired
//  public BatchConfig(JobRepository jobRepository) {
//    this.jobRepository = jobRepository;
//  }
//
//  @Bean
//  public JobLauncher simpleJobLauncher() {
//    SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
//    jobLauncher.setJobRepository(jobRepository);
//    return jobLauncher;
//  }
//}
