package com.example.selenium.pages.mythaistarCarlos;

public class User {
  private String username;

  private String email;

  private String amountOfGuests;

  public User(String name, String mail, String guests) {

    this.username = name;
    this.email = mail;
    this.amountOfGuests = guests;

  }

  public String getUsername() {

    return this.username;
  }

  public String getEmail() {

    return this.email;
  }

  public String getAmountOfGuests() {

    return this.amountOfGuests;
  }

}
