package com.amanda;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import org.json.simple.JsonObject;


public class CookIn extends AbstractVerticle {

    @Override
    public void start() {
        Router r = Router(this.vertx);
        r.get("/").handler(this::ping);

        this.vertx.createHttpServer()
            .requestHandler(r)
            .listen(8080);
    }

    private void ping(final RoutingContext rc) {
        Student s = new Student ("Amanda Suzzanne", "SCCI/01140/2018");
        rc.response().end(JsonObject.mapFrom(s).encode());
    }
}
