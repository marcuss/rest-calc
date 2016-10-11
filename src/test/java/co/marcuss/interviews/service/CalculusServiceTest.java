package co.marcuss.interviews.service;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;

import org.hamcrest.CoreMatchers;
import org.hamcrest.MatcherAssert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Arrays;

/**
 * MOdified by marcuss on 8/5/16.
 */
@RunWith(DataProviderRunner.class)
public class CalculusServiceTest {

  @DataProvider
  public static Object[][] addData() {
    return new Object[][]{
        {new Integer[]{3, 2, 1}, 6},
        {new Integer[]{-3, 2, 1}, 0},
        {new Integer[]{-2, -2, -2}, -6},
        {new Integer[]{null, 1, 1}, 2},
        {new Integer[]{1, null, 1}, 2},
        {new Integer[]{1, 1, null}, 2},
        {new Integer[]{null, null, 1}, 1},
        {new Integer[]{null, null, null}, 0},
        {new Integer[]{}, 0},
        {new Integer[]{3, 3, 3, 3, 3, 3, 3, 3, 3, 3}, 30},
    };
  }

  @Test
  @UseDataProvider("addData")
  public void testAdd(Integer[] numbers, Integer result) {
    MatcherAssert.assertThat(
        CalculusService.add(Arrays.asList(numbers)), CoreMatchers.equalTo(result)
    );
  }

  @DataProvider
  public static Object[][] subtractData() {
    return new Object[][]{
        {new Integer[]{3, 2, 1}, 0},
        {new Integer[]{-3, 2, 1}, -6},
        {new Integer[]{-2, -2, -2}, 2},
        {new Integer[]{null, 4, 1}, 3},
        {new Integer[]{4, null, 1}, 3},
        {new Integer[]{4, 1, null}, 3},
        {new Integer[]{-4, 1, null}, -5},
        {new Integer[]{null, null, 1}, 1},
        {new Integer[]{null, 1, null}, 1},
        {new Integer[]{1, null, null}, 1},
        {new Integer[]{10, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}, -3},
        {new Integer[]{null, null, null}, 0},
        {new Integer[]{}, 0}
    };
  }

  @Test
  @UseDataProvider("subtractData")
  public void testSubtract(Integer[] numbers, Integer result){
    MatcherAssert.assertThat(
        CalculusService.subtract(Arrays.asList(numbers)), CoreMatchers.equalTo(result.longValue())
    );
  }

  @Test
  public void testSubtractExtreme(){
    MatcherAssert.assertThat(
        CalculusService.subtract(Arrays.asList(new Integer[]{Integer.MIN_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE})),
        CoreMatchers.equalTo(-6442450942L)
    );
  }

  @Test
  public void testSubtractExtreme2(){
    MatcherAssert.assertThat(
        CalculusService.subtract(Arrays.asList(new Integer[]{Integer.MAX_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE})),
        CoreMatchers.equalTo(6442450943L)
    );
  }

  @DataProvider
  public static Object[][] multiplyData() {
    return new Object[][]{
        {new Integer[]{3, 2, 1}, 6},
        {new Integer[]{-3, 2, 1}, -6},
        {new Integer[]{-2, -2, -2}, -8},
        {new Integer[]{null, 4, 1}, 4},
        {new Integer[]{4, null, 1}, 4},
        {new Integer[]{4, 1, null}, 4},
        {new Integer[]{-4, 1, null}, -4},
        {new Integer[]{null, null, 1}, 1},
        {new Integer[]{null, 1, null}, 1},
        {new Integer[]{1, null, null}, 1},
        {new Integer[]{2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2}, 2048},
        {new Integer[]{2}, 2},
        {new Integer[]{}, 0},
        {new Integer[]{null, null, null}, 0},
    };
  }

  @Test
  @UseDataProvider("multiplyData")
  public void testMultiply(Integer[] numbers, Integer result){
    MatcherAssert.assertThat(
        CalculusService.multiply(Arrays.asList(numbers)), CoreMatchers.equalTo(result.longValue())
    );
  }

  @Test
  public void testMultiplyExtremeCase(){
    MatcherAssert.assertThat(
        CalculusService.multiply(Arrays.asList(new Integer[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE})),
        CoreMatchers.equalTo(new Long(Integer.MAX_VALUE)*new Long(Integer.MAX_VALUE)*new Long(Integer.MAX_VALUE))
    );
  }

  @DataProvider
  public static Object[][] divideData() {
    return new Object[][]{
        {new Integer[]{10, 3}, 10d/3d},
        {new Integer[]{40, 10}, 4d},
        {new Integer[]{-20, -20}, 1d},
        {new Integer[]{null, 4}, 0d},
        {new Integer[]{4, null}, Double.NaN},
        {new Integer[]{4, 1}, 4d},
        {new Integer[]{-4, 1}, -4d},
        {new Integer[]{null, null}, 0d},
        {new Integer[]{null, 1}, 0d},
        {new Integer[]{1, Integer.MAX_VALUE}, 4.656612875245797E-10}
    };
  }

  @Test
  @UseDataProvider("divideData")
  public void testDivide(Integer[] numbers, Double result){
    MatcherAssert.assertThat(
        CalculusService.divide(numbers[0], numbers[1]),
        CoreMatchers.equalTo(result)
    );
  }
}
