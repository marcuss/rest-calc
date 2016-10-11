package co.marcuss.interviews.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * Created by marcuss on 8/4/16.
 */
public class CalcServiceHealthCheck extends HealthCheck{

  @Override
  protected Result check() throws Exception {
    return Result.healthy();
  }
}
