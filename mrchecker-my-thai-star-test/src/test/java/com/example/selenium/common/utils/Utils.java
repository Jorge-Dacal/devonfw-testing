package com.example.selenium.common.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;

/**
 * @author jodacalc
 *
 */
public class Utils {
  public static String getRandomEmail(String name) {

    Random random = new Random();
    int rint = random.nextInt(1000);
    return name + "_" + rint + "_@test.com";
  }

  public static String getTomorrowDate() {

    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DAY_OF_YEAR, 1);
    return new SimpleDateFormat("MM/dd/yyyy HH:mm a").format(calendar.getTime());
  }

  public static int getRandom1toMax(int max) {

    Random random = new Random();
    return random.nextInt(max) + 1;
  }

}
