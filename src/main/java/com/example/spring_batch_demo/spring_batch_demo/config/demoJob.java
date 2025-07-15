package com.example.spring_batch_demo.spring_batch_demo.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.database.builder.JpaItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.task.SimpleAsyncTaskExecutorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.transaction.PlatformTransactionManager;

import com.example.spring_batch_demo.spring_batch_demo.dto.EmployeeDTO;
import com.example.spring_batch_demo.spring_batch_demo.listener.CustomItemWriteListener;
import com.example.spring_batch_demo.spring_batch_demo.listener.EmployeeImportJobListener;
import com.example.spring_batch_demo.spring_batch_demo.listener.EmployeeStepExecutionListener;
import com.example.spring_batch_demo.spring_batch_demo.mapper.EmployeeFileRowMapper;
import com.example.spring_batch_demo.spring_batch_demo.model.Employee;
import com.example.spring_batch_demo.spring_batch_demo.processor.EmployeeProcessor;
import com.example.spring_batch_demo.spring_batch_demo.skip.JobSkipPolicy;
import com.example.spring_batch_demo.spring_batch_demo.validator.EmployeeValidator;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class demoJob {
  @Bean
  public FlatFileItemReader<EmployeeDTO> employeeReader() {
    return new FlatFileItemReaderBuilder<EmployeeDTO>()
        .linesToSkip(1)
        .name("csvEmployeeReader")
        .resource(new ClassPathResource("employees.csv"))
        .delimited()
        .delimiter(",")
        .names("employeeId", "firstName", "lastName", "email", "dateOfBirth")
        .fieldSetMapper(new EmployeeFileRowMapper())
        .build();
  }

  @Bean
  public JpaItemWriter<Employee> employeeWriter(EntityManagerFactory entityManagerFactory) {
    return new JpaItemWriterBuilder<Employee>()
        .entityManagerFactory(entityManagerFactory)
        .build();

//    JpaItemWriter<Employee> writer = new JpaItemWriter<>();
//    writer.setEntityManagerFactory(entityManagerFactory);
//    return writer;
  }

//  @Bean
//  public Step step1Demo1() throws Exception {
//    return this.stepBuilderFactory.get("csvEmployeeImportStep")
//        .<EmployeeDTO, Employee>chunk(5)
//        .reader(employeeReader())
//        .processor(employeeProcessor())
//        .writer(employeeWriter())
//        .build();
//  }
  @Bean(name = "csvEmployeeImportStep")
  public Step employeeImportStep(ItemReader<EmployeeDTO> reader, ItemWriter<Employee> writer, JobRepository jobRepository, PlatformTransactionManager transactionManager, EmployeeProcessor processor) {
    return new StepBuilder("csvEmployeeImportStep", jobRepository)
        .<EmployeeDTO, Employee>chunk(5, transactionManager)
        .reader(reader)
        .processor(processor)
        .writer(writer)
        .taskExecutor(taskExecutor())
        .allowStartIfComplete(true)
        .build();
  }

//  @Bean
//  public Job demo1Job() throws Exception {
//    return this.jobBuilderFactory.get("csvEmployeeImportJob")
//        .start(step1Demo1())
//        .build();
//  }
  @Bean(name = "csvEmployeeImportJob")
  public Job employeeImportJob(@Qualifier("csvEmployeeImportStep") Step employeeStep, JobRepository jobRepository, EmployeeImportJobListener listener) {
    return new JobBuilder("csvEmployeeImportJob", jobRepository)
        .incrementer(new RunIdIncrementer())
        .listener(listener)
        .flow(employeeStep)
        .end()
        .build();
  }

  @Bean
  public TaskExecutor taskExecutor() {
    SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor();
    simpleAsyncTaskExecutor.setConcurrencyLimit(7);
    return simpleAsyncTaskExecutor;
  }
}
