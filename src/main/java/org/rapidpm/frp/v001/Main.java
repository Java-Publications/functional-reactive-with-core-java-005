package org.rapidpm.frp.v001;

/**
 *
 */
public class Main {


  public static interface Service {
    String doWork(String txt) throws Exception;
  }

  public static Service serviceA = txt -> txt.toUpperCase() + "-workedOnA";
  public static Service serviceB = txt -> txt.toUpperCase() + "-workedOnB";

  public static void main(String[] args) {

    try {
      new Service() {
        @Override
        public String doWork(String txt) throws Exception {
          return txt.toUpperCase() + "-workedOn";
        }
      }.doWork("");
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      ((Service) txt -> txt.toUpperCase() + "-workedOn").doWork("");
    } catch (Exception e) {
      e.printStackTrace();
    }


    try {
      final String helloA = serviceA.doWork("Hello A");
    } catch (Exception e) {
      e.printStackTrace();
    }

    try {
      final String helloB = serviceB.doWork("Hello B");
    } catch (Exception e) {
      e.printStackTrace();
    }


  }
}
