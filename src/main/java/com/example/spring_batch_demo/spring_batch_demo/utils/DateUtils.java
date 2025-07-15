package com.example.spring_batch_demo.spring_batch_demo.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateUtils {
  public static LocalDate parseDate(String dateStr){
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    return LocalDate.parse(dateStr, formatter);
  }
}
