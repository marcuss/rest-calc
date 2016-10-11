package co.marcuss.interviews.resources;

import com.codahale.metrics.annotation.Timed;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.inject.Singleton;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.marcuss.interviews.api.Calculus;
import co.marcuss.interviews.api.Operation;
import co.marcuss.interviews.api.Response;
import co.marcuss.interviews.core.CalculusCache;
import io.dropwizard.jersey.caching.CacheControl;

/**
 * Modified by marcuss on 8/5/16.
 */
@Path("/calc")
@Singleton
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CalcServiceResource {

  @GET
  @Timed
  @Path("/add/{numbers:.*}")
  @CacheControl(maxAge = 360, maxAgeUnit = TimeUnit.DAYS)
  public Response add(
      @PathParam("numbers") @DefaultValue("0") String numbers) {
    return
        CalculusCache.calcResponses.getUnchecked(
            Calculus.builder
                .numbers(parseNumbers(numbers))
                .operation(Operation.ADD)
                .build()
        );
  }

  private List<Integer> parseNumbers(String numbers) {
    try {
      List<Integer> parsed =
          Arrays.stream(numbers.split("/"))
              .map(Integer::parseInt)
              .collect(Collectors.toList());
      return  parsed;
    }catch (Exception e){
      throw new BadRequestException(e);
    }
  }

  @GET
  @Timed
  @Path("/subtract/{numbers:.*}")
  @CacheControl(maxAge = 360, maxAgeUnit = TimeUnit.DAYS)
  public Response subtract(
      @PathParam("numbers") @DefaultValue("0") String numbers) {

    return
        CalculusCache.calcResponses.getUnchecked(
            Calculus.builder
                .numbers(parseNumbers(numbers))
                .operation(Operation.SUBTRACT)
                .build()
        );
  }

  @GET
  @Timed
  @Path("/multiply/{numbers:.*}")
  @CacheControl(maxAge = 360, maxAgeUnit = TimeUnit.DAYS)
  public Response multiply(
      @PathParam("numbers") @DefaultValue("0") String numbers) {

    return
        CalculusCache.calcResponses.getUnchecked(
            Calculus.builder
                .numbers(parseNumbers(numbers))
                .operation(Operation.MULTIPLY)
                .build()
        );
  }

  @GET
  @Timed
  @Path("/divide/{numbers:.*}")
  @CacheControl(maxAge = 360, maxAgeUnit = TimeUnit.DAYS)
  public Response divide(
      @PathParam("numbers") @DefaultValue("0") String numbers) {

    return
        CalculusCache.calcResponses.getUnchecked(
            Calculus.builder
                .numbers(parseNumbers(numbers))
                .operation(Operation.DIVIDE)
                .build()
        );
  }
}
