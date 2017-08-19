package org.rapidpm.frp.v002;

import java.util.Optional;

/**
 *
 */
public class Main {

  public static interface Service {
    String doWork(String txt) throws Exception;
  }


  public static void main(String[] args) {


    Optional<String> optional;
    try {
      final String result = ((Service) txt -> txt.toUpperCase() + "-workedOn").doWork("");
      optional = Optional.of(result);
    } catch (Exception e) {
      e.printStackTrace();
      optional = Optional.empty();
    }

    optional.ifPresent((result) -> System.out.println("result = " + result));

  }
}
