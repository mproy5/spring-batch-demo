package com.example.spring_batch_demo.spring_batch_demo.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.batch.core.launch.JobLauncher;

@RestController
@RequestMapping("api/v1/jobs")
public class JobController {
  private final JobLauncher jobLauncher;
  private final Job csvEmployeeImportJob;
  private final Job employeeTaskletJob;

  @Autowired
  public JobController(JobLauncher jobLauncher,
      @Qualifier("csvEmployeeImportJob") Job csvEmployeeImportJob,
      @Qualifier("employeeTaskletJob") Job employeeTaskletJob) {
    this.jobLauncher = jobLauncher;
    this.csvEmployeeImportJob = csvEmployeeImportJob;
    this.employeeTaskletJob = employeeTaskletJob;
  }

  @GetMapping(value = "/employee")
  public String runEmployeeJob(@RequestParam(value = "startDate", required = false) String startDate,
      @RequestParam(value = "endDate", required = false) String endDate) {
    try {
      JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();
      jobParametersBuilder.addLong("startAt", System.currentTimeMillis());
      if (startDate != null) {
        jobParametersBuilder.addString("startDate", startDate);
      }
      if (endDate != null) {
        jobParametersBuilder.addString("endDate", endDate);
      }
      JobParameters jobParameters = jobParametersBuilder.toJobParameters();
      jobLauncher.run(csvEmployeeImportJob, jobParameters);
      return "Job csvEmployeeImportJob submitted successfully.";
    } catch (JobExecutionException e) {
      return "Job csvEmployeeImportJob failed: " + e.getMessage();
    }
  }

  @GetMapping(value = "/employee/tasklet")
  public String runEmployeeTaskletJob() {
    try {
      JobParameters jobParameters = new JobParametersBuilder()
          .addLong("startAt", System.currentTimeMillis())
          .toJobParameters();
      jobLauncher.run(employeeTaskletJob, jobParameters);
      return "Job employeeTaskletJob submitted successfully.";
    } catch (JobExecutionException e) {
      return "Job employeeTaskletJob failed: " + e.getMessage();
    }
  }
}



//public class JobController {
//
//  private JobRunner jobRunner;
//
//  @Autowired
//  public JobController(JobRunner jobRunner) {
//    this.jobRunner = jobRunner;
//  }
//
//  @RequestMapping(value = "/job")
//  public String runJob() {
//    jobRunner.runBatchJob();
//    return String.format("Job Demo1 submitted successfully.");
//  }
//}
