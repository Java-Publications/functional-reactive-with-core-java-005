package org.rapidpm.frp.v003;

import java.util.Optional;
import java.util.function.Function;

/**
 *
 */
public class Main {

  public static interface Service {
    String doWork(String txt) throws Exception;
  }

  @FunctionalInterface
  public interface CheckedFunction<T, R> extends Function<T, Optional<R>> {
    @Override
    default Optional<R> apply(T t) {
      try {
        return Optional.ofNullable(applyWithException(t));
      } catch (Exception e) {
        return Optional.empty();
      }
    }

    R applyWithException(T t) throws Exception;

  }

  public static void main(String[] args) {

    Optional<String> optional;

    final CheckedFunction<String, String> checkedFunction = new CheckedFunction<String, String>() {
      @Override
      public String applyWithException(String s) throws Exception {
        return ((Service) txt -> txt.toUpperCase() + "-workedOn").doWork(s);
      }
    };

    checkedFunction.apply("Hello")
                   .ifPresent((result) -> System.out.println("result = " + result));

    final Function<String, Optional<String>> f = checkedFunction;

    f.apply("Hello")
     .ifPresent((result) -> System.out.println("result = " + result));


  }
}
