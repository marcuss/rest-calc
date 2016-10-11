package co.marcuss.interviews.service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import co.marcuss.interviews.api.Calculus;

/**
 * Modified by marcuss on 8/5/16.
 */
public class CalculusService {

  public static Integer add(List<Integer> numbers) {
    return
        numbers.stream()
            .filter(Objects::nonNull)
            .collect(
                Collectors.summingInt(Integer::intValue)
            );
  }

  public static Long subtract(List<Integer> numbers) {
    List<Integer> filtered =
        numbers
            .stream()
            .filter(Objects::nonNull)
            .collect(Collectors.toList());
    if(filtered.isEmpty()){return 0l;}
    Integer start = filtered.remove(0);
    return filtered.stream().mapToLong(Integer::longValue).reduce(start, (a, b) -> a - b);
  }

  public static Long multiply(List<Integer> numbers) {
    List<Integer> filtered = numbers.stream()
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    if (filtered.isEmpty()) {
      return 0l;
    }
    return filtered.stream()
        .mapToLong(Integer::longValue)
        .reduce(1, (a, b) -> a * b);
  }

  public static Double divide(Integer number1, Integer number2) {

    if (number1 != null && number2 == null) {
      return Double.NaN;
    }

    if (number1 == null && number2 != null) {
      return 0d;
    }

    if (number1 == null && number2 == null) {
      return 0d;
    }

    return number1.doubleValue() / number2.doubleValue();
  }

  public static Double execute(Calculus calc) {
    switch (calc.getOperation()) {
      case ADD:
        return add(calc.getNumbers()).doubleValue();
      case SUBTRACT:
        return subtract(calc.getNumbers()).doubleValue();
      case MULTIPLY:
        return multiply(calc.getNumbers()).doubleValue();
      case DIVIDE:
        return divide(calc.getNumbers().get(0), calc.getNumbers().get(1));
      default:
        return null;
    }
  }
}
