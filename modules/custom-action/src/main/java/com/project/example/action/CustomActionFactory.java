package com.project.example.action;

import io.knotx.fragments.handler.api.Action;
import io.knotx.fragments.handler.api.ActionFactory;
import io.knotx.fragments.handler.api.domain.FragmentResult;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonObject;

public class CustomActionFactory implements ActionFactory {

  @Override
  public String getName() {
    return "customAction";
  }

  @Override
  public Action create(String alias, JsonObject config, Vertx vertx, Action doAction) {
    return (fragmentContext, resultHandler) -> Future.succeededFuture(
        new FragmentResult(fragmentContext.getFragment(), FragmentResult.SUCCESS_TRANSITION))
        .setHandler(resultHandler);
  }
}
