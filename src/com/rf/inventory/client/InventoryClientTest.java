/**
 * 
 */
package com.rf.inventory.client;

import java.util.List;

import org.junit.*;
import static org.junit.Assert.*;

/**
 * @author v.lakshmanan
 *
 */
public class InventoryClientTest {

    private InventoryClient client;
    
    @Before
    public void setUp() throws Exception {
        client = new InventoryClient();
    }

    private boolean contains(List<Item> items, int productId){
        for (Item item : items){
            if (item.getProductId() == productId){
                return true;
            }
        }
        return false;
    }
    
    @Test
    public void testGetItems() {
        client.insertItem(3004,12);
        ItemList items = client.getItems();
        for (Item item : items.getItems()){
            System.out.print(item.getProductId() + " " + item.getQuantity() + ";");
        }
        System.out.println();
        assertTrue(items != null );
        assertTrue(items.getItems().size() > 0);
        assertTrue(contains(items.getItems(), 3004));
    }

    @Test
    public void testDeleteAndInsert() {
        client.insertItem(3004,12);
        assertTrue(contains(client.getItems().getItems(), 3004));
        client.deleteItem(3004);
        assertTrue(!contains(client.getItems().getItems(), 3004));
        client.insertItem(3004, 12);
        assertTrue(contains(client.getItems().getItems(), 3004));
    }
    
    @Test
    public void testUpdate() {
        int status = client.updateQuantity(3004,23);
        assertEquals("updateQuantity(3004,23) return status", 202, status);
        ItemList items = client.getItems();
        for (Item item : items.getItems()){
            if (item.getProductId() == 3004){
                assertEquals(item.getQuantity(), 23);
                return;
            }
        }
        fail("3004 wasn't found");
    }
}
