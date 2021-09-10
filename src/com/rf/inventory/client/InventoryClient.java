/**
 * Exercise 8.1
 */
package com.rf.inventory.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Demonstrates the JAX-RS 2.0 Client API to consume messages from 
 * a RESTful web service
 */
public class InventoryClient {
    // BONUS 2 TODO: To intercept messages with Fiddler, change the host name in 
    //               both URLs from localhost to the actual host name
    private static final String BASE_URL = "http://localhost:8080/inventory/rs/item";
    private static final String BASE_URL_JSON = "http://localhost:8080/inventory_json/rs/item";

    private final Client client;
    
    public InventoryClient(){
        // TODO: Set the instance field 'client'
        // HINT: See slide 8-5
        client = ClientBuilder.newClient();
        
        // BONUS 2 TODO: Uncomment the following lines
        //System.setProperty("http.proxyHost", "127.0.0.1");
        //System.setProperty("http.proxyPort", "8888");
    }
    
    public ItemList getItems() {
        // TODO: Invoke the "GET" operation on BASE_URL + "/all"
        //       Tell JAX-RS to unmarshall the response to an ItemList
        // HINT: See slide 8-5
        ItemList itemList=client.target(BASE_URL +"/all").request().get(ItemList.class);
        // TODO: print the size of the ItemList to the console
        System.out.println(itemList.getItems().size());
        // TODO: Return the ItemList
        return itemList;
    }

    public int deleteItem(int productId) {
        int status;
        
        // TODO: Invoke the DELETE operation on BASE_URL + "/" + productId
        // HINT: To add the productId to the URL, use the path() and resolveTemplate() 
        //       methods instead of String concatenation
        // HINT: See slide 8-10
        Response response=client.target(BASE_URL).path("/{productId}").resolveTemplate("productId", productId).request().delete();
        
        // TODO: Get the HTTP status from the response
        // HINT: See slide 8-18
        status = response.getStatus();
        
        // TODO: print the response status to the console
        System.out.println("status = " + status);
        
        return status;
    }
    
    /**
     * Returns the HTTP status of the POST request that inserts an item
     */
    public void insertItem(int productId, int qty){
        Item item = new Item();
        item.setProductId(productId);
        item.setQuantity(qty);
        insertItem(item);
    }
    
    /**
     * Returns the HTTP status of the POST request that inserts an item
     */
    public int insertItem(Item item){
        int status;

        // TODO: Create an XML Entity from the Item
        // HINT: See slide 8-16
        Entity<Item> entity=Entity.xml(item);
        
        // TODO: Invoke the POST operation on BASE_URL, posting the Item's Entity as XML
        Response response=client.target(BASE_URL).request().post(entity);
        
        // TODO: Get the HTTP status from the response
        // HINT: See slide 8-18
        status =  response.getStatus();
        
        // TODO: print the response status to the console
        System.out.println(status);
        return status;
    }
    
    /**
     * Returns the HTTP status of the PUT request that updates an item
     */
    public int updateQuantity(int productId, int qty){
        int status;

        // TODO: Note how we create an Item using the passed-in parameters
        //       (no code changes required)
        Item item = new Item();
        item.setProductId(productId);
        item.setQuantity(qty);
        
        // TODO: Create an XML Entity from the new Item
        Entity<Item> entity=Entity.xml(item);
        
        // TODO: Invoke the PUT operation on BASE_URL + "/" + productId, 
        //       marshalling the Item's Entity as XML
        Response response=client.target(BASE_URL).path("/{productId}").resolveTemplate("productId", productId).request().put(entity);
        
        // TODO: Get the HTTP status from the response
        status = response.getStatus();

        // TODO: print the response status to the console
        System.out.println(status);
        
        return status;
    }
}
