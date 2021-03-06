package com.example.selenium.common.data;

import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

import junitparams.mappers.CsvWithHeaderMapper;

/**
 * @author jambulud
 *
 */
public class UserMapper extends CsvWithHeaderMapper {

  @Override
  public Object[] map(Reader reader) {

    Object[] map = super.map(reader);
    List<Object[]> result = new LinkedList<Object[]>();
    for (Object lineObj : map) {
      String line = (String) lineObj;

      // Splitted with ","
      Object[] lineSplitted = line.split(","); // Example line { 21,John }

      // Order of arguments must be inline with Person class constructor argument list
      result.add(lineSplitted);
    }
    return result.toArray();
  }
}
