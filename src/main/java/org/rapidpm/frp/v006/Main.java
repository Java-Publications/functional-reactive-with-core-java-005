package org.rapidpm.frp.v006;

import java.util.function.Function;

import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class Main {

  public static interface Service {
    String doWork(String txt) throws Exception;
  }

  public static Service serviceA  = txt -> txt.toUpperCase() + "-workedOnA";
  public static Service serviceB  = txt -> txt.toUpperCase() + "-workedOnB";

  public static void main(String[] args) {

    //both must be executed, even if first one fails
    try {
      final String resultA = serviceA.doWork(null);
    } catch (Exception e) {
      e.printStackTrace();
    }
    try {
      final String resultB = serviceB.doWork("Hello");
    } catch (Exception e) {
      e.printStackTrace();
    }

    final Function<String, Result<String>> fA = (CheckedFunction<String, String>) (txt) -> serviceA.doWork(txt);
    final Function<String, Result<String>> fB = (CheckedFunction<String, String>) (txt) -> serviceB.doWork(txt);

    final Result<String> resultFA = fA.apply(null);
    final Result<String> resultFB = fB.apply("Hello");

  }
}
