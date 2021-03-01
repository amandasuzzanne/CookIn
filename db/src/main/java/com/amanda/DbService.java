package com.amanda;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.mongo.MongoClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class DbService extends AbstractVerticle {

    private MongoClient bdClient;

    private static final String CATEGORY_TABLE = "category";

    @Override
    public void start() {
        JsonObject config = new JsonObject()
                .put("db_name", "db")
                .put("connection_string", "mongodb://localhost:27017");

        this.bdClient = MongoClient.create(this.vertx, config);

        Router r = Router.router(this.vertx);
        r.get("/").handler(this::ping);
        r.post("/createCategory").handler(this::createCategory);
        r.post("/getCategory").handler(this::getCategory);
        r.get("/listCategory").handler(this::listCategory);

        this.vertx.createHttpServer()
            .requestHandler(r)
            .listen(8085);
    }

    private void listCategory(final RoutingContext rc) {
        JsonObject qry = new JsonObject();
        this.bdClient.find(CATEGORY_TABLE, qry, res -> {
            if (res.succeeded()) {
                JsonArray  rst = new JsonArray(res.result());
                rc.response().end(rst.encode());
            }else {
                JsonObject err = new JsonObject()
                        .put("error", res.cause().getMessage());

                rc.response().end(err.encode());
            }
        });
    }

    private void getCategory(final RoutingContext rc) {
        rc.request().bodyHandler(b -> {
            JsonObject body = b.toJsonObject();
            bdClient.findOne(CATEGORY_TABLE, body,
                    new JsonObject(), res -> {
                        if (res.succeeded()){
                            rc.response().end(res.result().encode());
                        } else {
                            JsonObject err = new JsonObject()
                                    .put("body", body)
                                    .put("error", res.cause().getMessage());

                            rc.response().end(err.encode());
                        }
                    });
        });
    }

    private void createCategory(final RoutingContext rc) {
        rc.request().bodyHandler(b -> {
            JsonObject body = b.toJsonObject();
            bdClient.insert(CATEGORY_TABLE, body, res -> {
                if (res.succeeded()) {
                    rc.response().end(body.encode());
                } else {
                    JsonObject err = new JsonObject()
                            .put("body", body)
                            .put("error", res.cause().getMessage());

                    rc.response().end(err.encode());
                }
            });
        });
    }

    private void ping(final RoutingContext rc) {
        rc.response().end("Server is up  and running");
    }
}
