package org.rapidpm.frp.v008;

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

  public static <T,R> Function<CheckedFunction<T, R>, Function<T, Result<R>>> tryIt(){
    return (f) -> f;
  }


  public static void main(String[] args) {
//    final Function<String, Result<String>> fA = tryIt().apply(serviceA::doWork);
//    final Function<String, Result<String>> fB = tryIt().apply(txt -> txt.toUpperCase() + "-workedOnA");

    final Function<String, Result<String>> fA = Main.<String,String>tryIt().apply(serviceA::doWork);
    final Function<String, Result<String>> fB = Main.<String,String>tryIt().apply(txt -> txt.toUpperCase() + "-workedOnA");




  }
}
