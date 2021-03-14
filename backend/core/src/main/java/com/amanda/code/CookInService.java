package com.amanda.code;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class CookInService extends AbstractVerticle {

    @Override
    public void start() {
        Router r = Router.router(vertx);
        r.get("/").handler(this::ping);
        this.vertx.createHttpServer()
        .requestHandler(r)
        .listen(8085);
    }

    private void ping(RoutingContext rc){
        rc.response().end("Server is up and running");
    }

}
