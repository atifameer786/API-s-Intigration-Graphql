package org.task;

import java.util.ArrayList;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Routes extends RouteBuilder {
    private final ArrayList<Information> fruits = new ArrayList<>();

    public Routes() {
        this.fruits.add(new Information(1, "Winterfruit"));
        this.fruits.add(new Information(2, "Tropical fruit"));
    }

    @Override
    public void configure() throws Exception {

        restConfiguration().bindingMode(RestBindingMode.json);
        
        rest("/fruits")
                .get()
                .to("direct:getFruits");
        rest("/getLastFruits")
            .get()
            .to("direct:getLastFruit");
        rest("/addFruits")
            .post()
            .to("direct:addFruit");
        



        from("direct:getFruits")
            .setBody().constant(fruits);
                    
        from("direct:getLastFruit")
        .process(exchange->{
            exchange.getIn().setBody(fruits.get(fruits.size()-1));
        });
        from("direct:addFruit").log("${body}")
        .process( exchange->{

                Map<String,Object> payload = exchange.getIn().getBody(Map.class);
                ObjectMapper mapper=new ObjectMapper();
                Information info;
                info = mapper.convertValue(payload, Information.class);
                fruits.add(info);   
                exchange.getIn().setBody(info);         
        });
    }
}
// from("direct:addFruit")
        //     // .convertBodyTo(String.class)
        //     .log("${body}").log("${body[id]}").log("${body[name]}")
        //     .setBody().constant(fruits.add(new Information(Integer.valueOf("${body[id]}"),"$body[name]")));
        //     ;
        //from("direct:addFruit")
        //        .process().body(Fruit.class, fruits::add)
        //       .setBody().constant(fruits);
