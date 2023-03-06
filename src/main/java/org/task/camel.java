package org.task;

import javax.enterprise.context.ApplicationScoped;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

@ApplicationScoped
public class camel extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().bindingMode(RestBindingMode.json);
        // //get allInfo
        // rest("/Info").get().to("direct:getAllInfo");

        // //add Info
        // rest("/addInfo").post().to("direct:addInfo");

        // //delete Info
        // rest("/deleteInfo").post().to("direct:deleteInfo");

        rest("/info")
        .get("/all").to("direct:getAllInfo")
        .post("/add").to("direct:addInfo")
        .post("/delete").to("direct:deleteInfo");

        from("direct:getAllInfo")
        .setBody(constant("select * from Information "))
        .to("jdbc:datasource");

        from("direct:addInfo")
        // .log("${body}").log("${body[id]}").log("${body[name]}")
        .setBody(simple("insert into Information values(${body[id]},'${body[name]}')"))
        .to("jdbc:datasource");

        from("direct:deleteInfo").log("${body}").log("${body[id]}")
        .setBody(simple("delete from Information where id= ${body[id]}"))
        .to("jdbc:datasource");
    }

}


// from("direct:projects")
// .setHeader("lic", constant("ASF"))
// .setHeader("min", constant(123))
// .setBody("select * from projects where license = :?lic and id > :?min order by id")
// .to("jdbc:myDataSource?useHeadersAsParameters=true")