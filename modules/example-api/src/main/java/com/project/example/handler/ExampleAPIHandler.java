package com.project.example.handler;

import io.knotx.server.api.handler.RoutingHandlerFactory;
import io.vertx.core.Handler;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;
import io.vertx.reactivex.ext.web.RoutingContext;

public class ExampleAPIHandler implements RoutingHandlerFactory {

  @Override
  public String getName() {
    return "example-api";
  }

  @Override
  public Handler<RoutingContext> create(Vertx vertx, JsonObject config) {
    return event -> {
      event.response().end(config.getJsonObject("body", new JsonObject().put("status", "failed")).encode());
    };
  }
}
