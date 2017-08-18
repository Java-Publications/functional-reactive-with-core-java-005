package org.rapidpm.frp.v007;

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

  public static Service serviceA = txt -> txt.toUpperCase() + "-workedOnA";

  public static Function<CheckedFunction<String, String>, Function<String, Result<String>>> tryIt
      = (f) -> f;


  public static void main(String[] args) {
//    final Function<String, Result<String>> fA
//                  = (CheckedFunction<String, String>) serviceA::doWork;

    final Function<String, Result<String>> fA = tryIt.apply(serviceA::doWork);


  }

}
