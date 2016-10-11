package co.marcuss.interviews.core;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import co.marcuss.interviews.api.Calculus;
import co.marcuss.interviews.api.Response;
import co.marcuss.interviews.service.CalculusService;

/**
 * Modified by marcuss on 8/5/16.
 */
public class CalculusCache {

  public static LoadingCache<Calculus, Response> calcResponses =
      CacheBuilder.newBuilder()
      .maximumSize(1000)
      .build(
          new CacheLoader<Calculus, Response>() {
            public Response load(Calculus key) {
              return new Response(CalculusService.execute(key));
            }
          }
      );
}
