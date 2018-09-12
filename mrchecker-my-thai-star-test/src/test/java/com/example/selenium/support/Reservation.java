package com.example.selenium.support;

/**
 * @author jambulud
 *
 */
public class Reservation {
  private String date;

  private String email;

  private String id;

  /**
   * The constructor.
   *
   * @param date
   * @param email
   * @param ids
   */
  public Reservation(String date, String email, String id) {

    this.date = date;
    this.email = email;
    this.id = id;
  }

  /**
   * The constructor.
   *
   * @param date
   * @param email
   */
  public Reservation(String date, String email) {

    this.date = date;
    this.email = email;
  }

  public String getDate() {

    return this.date;
  }

  public String getEmail() {

    return this.email;
  }

  public String getReservationId() {

    return this.id;
  }

  public void setDate(String date) {

    this.date = date;
  }

  public void setEmail(String email) {

    this.email = email;
  }

  /**
   * @param id
   */
  public void setReservationId(String id) {

    this.id = id;
  }

  @Override
  public String toString() {

    return "{date: " + this.date + ", email:" + this.email + ", ids: " + this.id + "}\n";
  }

}
