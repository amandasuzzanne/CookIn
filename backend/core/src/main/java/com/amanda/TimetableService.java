package com.amanda;

import io.vertx.core.AbstractVerticle;

public class TimetableService extends AbstractVerticle {

    @Override
    public void start() {
        Router r = Router(this.vertx);
        r.get("/").handler(this::ping);

        this.vertx.createHttpServer()
            .requestHandler(r)
            .listen(8080);
    }

    private void ping(final RoutingContext){
        rc.response().end("Amanda is a G")
    }
}