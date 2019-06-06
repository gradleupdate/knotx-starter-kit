package com.project.example.handler;

import io.knotx.server.api.handler.RoutingHandlerFactory;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;

public class CustomHandlerFactory implements RoutingHandlerFactory {

  @Override
  public String getName() {
    return "custom-handler";
  }

  @Override
  public Handler<RoutingContext> create(Vertx vertx, JsonObject config) {
    return event -> {
      event.response().end(config.getString("message", "No message defined."));
    };
  }
}
