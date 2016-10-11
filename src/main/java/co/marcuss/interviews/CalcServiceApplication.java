package co.marcuss.interviews;

import co.marcuss.interviews.health.CalcServiceHealthCheck;
import co.marcuss.interviews.resources.CalcServiceResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class CalcServiceApplication extends Application<CalcServiceConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CalcServiceApplication().run(args);
    }

    @Override
    public String getName() {
        return "CalcService";
    }

    @Override
    public void initialize(final Bootstrap<CalcServiceConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CalcServiceConfiguration configuration,
                    final Environment environment) {
        environment.healthChecks().register("calc-service", new CalcServiceHealthCheck());
        environment.jersey().register(new CalcServiceResource());
    }

}
