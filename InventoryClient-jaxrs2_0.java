/**
 * 
 */
package com.rf.inventory.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 * 
 * Demonstrates the Jersey Client API to consume a RESTful web service
 * 
 * @author v.lakshmanan
 *
 */
public class InventoryClient {
    private final Client client;
    private final String BASE = "http://localhost:8080/inventory/rs/item";

    public InventoryClient(){
        // TODO: Set the instance field 'client'
        client = ClientBuilder.newClient();
    }
    
    public ItemList getItems(){
        // TODO: Invoke the "GET" operation on BASE/all and return result
        WebTarget target = client.target(BASE)
                                 .path("all");
        return target.request()
                     .get(ItemList.class);
//        return target.request()
//                     .buildGet()
//                     .invoke(ItemList.class);
    }

    public void deleteItem(int productId){
        // TODO: Invoke the DELETE operation on BASE/productId
        WebTarget target = client.target(BASE)
                                 .path("{id}");
        target.matrixParam("id", productId)
              .request()
              .delete();
//        target.matrixParam("id", productId)
//          .request()
//          .buildDelete()
//          .invoke();
    }
    
    public void insertItem(int productId, int qty){
        Item item = new Item();
        item.setProductId(productId);
        item.setQuantity(qty);
        insertItem(item);
    }
    
    public String insertItem(Item item){
        // TODO: Invoke the POST operation on BASE, posting XML corresponding to item
        WebTarget target = client.target(BASE);
//        String ok = target.request(MediaType.APPLICATION_XML)
//                          .post(Entity.entity(item, MediaType.APPLICATION_XML), String.class);
        Response response = target.request(MediaType.APPLICATION_XML)
                                  .post(Entity.entity(item, MediaType.APPLICATION_XML));
        int status = response.getStatus();
        String result = null;
        if (status == Response.Status.OK.getStatusCode()) {
            result = response.readEntity(String.class);
        }
        return result;
    }
    
    public String updateQuantity(int productId, int qty){
        // TODO: Create an Item using the passed-in parameters
        Item item = new Item();
        item.setProductId(productId);
        item.setQuantity(qty);
        // TODO: Invoke the PUT operation on BASE/productId, putting XML corresponding to item
        WebTarget target = client.target(BASE)
                                 .path("{id}");
        Response response = target.matrixParam("id", productId)
                                  .request(MediaType.APPLICATION_XML)
                                  .put(Entity.entity(item, MediaType.APPLICATION_XML));
        String ok = response.readEntity(String.class);
        return ok;
    }
}
