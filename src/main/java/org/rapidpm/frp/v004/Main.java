package org.rapidpm.frp.v004;

import java.util.function.Consumer;
import java.util.function.Function;

import org.rapidpm.frp.model.Result;

/**
 *
 */
public class Main {

  public static interface Service {
    String doWork(String txt) throws Exception;
  }

  @FunctionalInterface
  public interface CheckedFunction<T, R> extends Function<T, Result<R>> {
    @Override
    default Result<R> apply(T t) {
      try {
        return Result.success(applyWithException(t));
      } catch (Exception e) {
        final String message = e.getMessage();
        return Result.failure((message != null) ? message : e.getClass().getSimpleName());
      }
    }

    R applyWithException(T t) throws Exception;

  }

  public static void main(String[] args) {

    final Consumer<String> print = System.out::println;

    final Function<String, Result<String>> checkedFunction
        = (CheckedFunction<String, String>)
        ((Service) txt -> txt.toUpperCase() + "-workedOn")::doWork;


    checkedFunction.apply("Hello")
                   .ifPresentOrElse(
                       (result) -> print.accept("result = " + result) ,
                       (failed) -> print.accept("failed = " + failed)
                   );

    checkedFunction.apply("Hello")
                   .ifPresentOrElse(
                       (result) -> print.accept("result = " + result) ,
                       () -> print.accept("failed = execute something like logging ?")
                   );

    checkedFunction.apply(null)
                   .ifPresentOrElse(
                       (result) -> print.accept("result = " + result) ,
                       (failed) -> print.accept("failed = " + failed)
                   );

    checkedFunction.apply(null)
                   .ifPresentOrElse(
                       (result) -> print.accept("result = " + result) ,
                       () -> print.accept("failed = execute something like logging ?")
                   );


  }
}
