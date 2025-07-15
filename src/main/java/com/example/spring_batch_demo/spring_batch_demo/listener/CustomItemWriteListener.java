package com.example.spring_batch_demo.spring_batch_demo.listener;

import org.springframework.batch.core.ItemWriteListener;
import org.springframework.batch.item.Chunk;

import com.example.spring_batch_demo.spring_batch_demo.model.Employee;

public class CustomItemWriteListener implements ItemWriteListener<Employee> {

  @Override
  public void beforeWrite(Chunk<? extends Employee> items) {
    // Log or process items before they are written
    System.out.println("Preparing to write.............................................");
  }

  @Override
  public void afterWrite(Chunk<? extends Employee> items) {
    // Optionally log or process after items are written
    System.out.println("Successfully wrote................................................");
  }

  @Override
  public void onWriteError(Exception exception, Chunk<? extends Employee> items) {
    // Handle write errors, log the error
    System.err.println("Error writing items: " + items);
    exception.printStackTrace();
  }
}
