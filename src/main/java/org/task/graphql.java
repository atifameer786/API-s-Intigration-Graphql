package org.task;

import java.util.List;

import javax.inject.Inject;

import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Query;


import io.vertx.core.cli.annotations.Description;

@GraphQLApi
public class graphql {
    @Inject
    Implementation impl;

    @Query("getAllOrders")
    @Description("get All Orders")
    public List<Information> getAllOrders(){
        return impl.getAllInfo();
    }
    @Mutation
    @Description("addOrder")
    public Information addOrder(Information info){
        return impl.addOrder(info);
    }
    @Query("getById")
    @Description("getById")
    public Information getById(int i){
        return impl.getById(i);
    }
    @Query("getByName")
    @Description("get by name")
    public List<Information> getByName(Information name){
        return impl.getByName(name);
    }
    @Mutation("deleteById")
    @Description("delete by id")
    public Information deleteById(int id){
        return impl.deleteId(id);
    }
    @Mutation("updateName")
    @Description("update name by id")
    public Information updateName(int id,Information info){
        return impl.updateName(id, info);
    }
}
/*
 * query q1{
  getAllOrders{
    id
    name
  }
}
query q2{
  getById(i:4){
    id
    name
  }
}
query q3{
  getByName(name:{
    name:"virag+anurag"
    id:0
  }){
    id
    name
  }
}
mutation m1{
  addOrder (info:{id:4,
    name:"arjun"
    
  } ){
    id
    name
  }
}
mutation m2{
  updateName(id:2,info:{
    name:"virag"
    id:2
  }){
    id
    name
  }
}
mutation m3{
  deleteById(id:4){
    id
    name
  }
}

 */