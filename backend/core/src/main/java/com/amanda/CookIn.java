package com.amanda;

import io.vertx.core.AbstractVerticle;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.core.json.JsonObject;
import org.slf4j.Logger;
import org.slf4j.Loggerfactory;


public class CookIn extends AbstractVerticle {

    private final Logger logger =
    Loggerfactory.getLogger(this.getClass().getName());

    @Override
    public void start() {
        Router r = Router(this.vertx);
        r.get("/").handler(this::ping);

        this.vertx.createHttpServer()
            .requestHandler(r)
            .listen(8080);
    }

    private void ping(final RoutingContext rc) {
        try { 
            Student s = new Student (
                "Amanda Suzzanne", "SCCI/01140/2018");
                 rc.response().end(JsonObject
                 .mapFrom(s).encode());
            
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            rc.response().end(e.getMessage());
        }
       
    }
}
