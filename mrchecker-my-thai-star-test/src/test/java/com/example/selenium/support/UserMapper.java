package com.example.selenium.support;

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

  public static class User {

    private String username;

    private String password;

    // Arguments order depends on data in CSV line
    public User(String username, String password) {

      this.username = username;
      this.password = password;

    }

    // When there is only one argument after CSV line split, than treat this one as it is argument AGE
    public User(String username) {

      this.username = username;
      this.password = "fakepassword";
    }

    public String getUsername() {

      return this.username;
    }

    public String getPassword() {

      return this.password;
    }

    @Override
    public String toString() {

      return "Usern with username: " + this.username + " and password: " + this.password;
    }
  }
}
