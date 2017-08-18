package org.rapidpm.frp.v005;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntBinaryOperator;
import java.util.function.IntConsumer;
import java.util.stream.Stream;

import org.rapidpm.frp.functions.CheckedFunction;
import org.rapidpm.frp.model.Result;

/**
 *
 */
public class Main {

  public static interface Service {
    String doWork(String txt) throws Exception;
  }

  private static IntConsumer print = System.out::println;

  public static void main(String[] args) {
    //from now on using functional-reactive lib
    //https://github.com/functional-reactive/functional-reactive-lib

    final Function<String, Result<Integer>> f = (CheckedFunction<String, Integer>) Integer::valueOf;

    Stream
        .of("1" , "2" , "Hi" , "3")
        .parallel()
        .map(f)
        .filter(Result::isPresent)
        .mapToInt(Result::get)
        .reduce((left , right) -> left + right)
        .ifPresent(print);
  }
}
