package co.marcuss.interviews.api;

import java.util.List;

/**
 * Created by marcuss on 8/4/16.
 */
public class Calculus {

  private List<Integer> numbers;

  private Operation operation;

  public static CalculusBuilder builder = new CalculusBuilder();

  public Operation getOperation() {
    return operation;
  }

  public void setOperation(Operation operation) {
    this.operation = operation;
  }

  public Calculus(List<Integer> numbers, Operation operation) {
    this.setNumbers(numbers);
    this.setOperation(operation);
  }

  public List<Integer> getNumbers() {
    return numbers;
  }

  public void setNumbers(List<Integer> numbers) {
    this.numbers = numbers;
  }


  public static class CalculusBuilder {
    private List<Integer> numbers;
    private Operation operation;

    public CalculusBuilder numbers(List<Integer> numbers) {
      this.numbers = numbers;
      return this;
    }

    public CalculusBuilder operation(Operation operation) {
      this.operation = operation;
      return this;
    }

    public Calculus build() {
      return new Calculus(numbers, operation);
    }
  }
}
