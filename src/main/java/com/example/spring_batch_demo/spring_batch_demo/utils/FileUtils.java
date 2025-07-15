package com.example.spring_batch_demo.spring_batch_demo.utils;


import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.spring_batch_demo.spring_batch_demo.dto.EmployeeDTO;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class FileUtils {
  private final Logger logger = LoggerFactory.getLogger(FileUtils.class);

  private String fileName;
  private CSVReader csvReader;
  private CSVWriter csvWriter;
  private FileReader fileReader;
  private FileWriter fileWriter;
  private File file;

  public FileUtils(String fileName) {
    this.fileName = fileName;
  }

  public EmployeeDTO readLine() {
    try {
      if (Objects.isNull(csvReader)) {
        initReader();
        csvReader.readNext();
      }

      String[] data = csvReader.readNext();

      if (Objects.isNull(data)) {
        return null;
      }

      return new EmployeeDTO(data[0], data[1], data[2], data[3], DateUtils.parseDate(data[4]));
    } catch (Exception e) {
      logger.error("Error while reading data in file: {}", fileName);
      return null;
    }
  }

  private void initReader() throws Exception {
    ClassLoader classLoader = this.getClass().getClassLoader();

    if (file == null) {
      file = new File(classLoader.getResource(fileName).getFile());
    }

    if (fileReader == null) {
      fileReader = new FileReader(file);
    }

    if (csvReader == null) {
      csvReader = new CSVReader(fileReader);
    }
  }

  public void closeReader() {
    try {
      csvReader.close();
      fileReader.close();
    } catch (IOException e) {
      logger.error("Error while closing reader.");
    }
  }
}
