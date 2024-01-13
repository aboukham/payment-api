package com.example.PaymentAPI.aop;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PerformanceTrackerHandler  implements ObservationHandler<Observation.Context> {
    @Override
    public void onStart(Observation.Context context) {
       log.info("Execution started...", context.getName());
       context.put("Time", System.currentTimeMillis());
    }

    @Override
    public void onError(Observation.Context context) {
        log.info("Error occurred", context.getError().getMessage());
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onScopeReset(Observation.Context context) {
        ObservationHandler.super.onScopeReset(context);
    }

    @Override
    public void onStop(Observation.Context context) {
        StringBuilder logInfo = new StringBuilder("Execution stopped ");
        logInfo.append(context.getName());
        logInfo.append(" duration ");
        logInfo.append(System.currentTimeMillis() - context.getOrDefault("Time", 0L));

        log.info(logInfo.toString());
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
