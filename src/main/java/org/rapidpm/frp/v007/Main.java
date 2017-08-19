package org.rapidpm.frp.v007;

import static java.lang.System.out;

import java.util.Objects;
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

  public static Service serviceA
      = txt -> txt.toUpperCase() + "-workedOnA";

  public static Function<CheckedFunction<String, String>,
      Function<String, Result<String>>>
      tryIt = (f) -> f;

  public static Function<String, Result<String>> tryIt(CheckedFunction<String, String> function) {
    Objects.requireNonNull(function);
    return function;
  }

  public static void main(String[] args) {

    final Function<String, Result<String>> fA = tryIt.apply(serviceA::doWork);
    final Function<String, Result<String>> fB = tryIt.apply(txt -> txt.toUpperCase() + "-workedOnA");

    final Function<String, Result<String>> fC = tryIt(serviceA::doWork);
    final Function<String, Result<String>> fD = tryIt(txt -> txt.toUpperCase() + "-workedOnA");

    out.println("fA = " + fA.apply(null));
    out.println("fB = " + fB.apply(null));
    out.println("fC = " + fC.apply(null));
    out.println("fD = " + fD.apply(null));

  }

}
