/**
 * 
 */
package com.rf.inventory.client;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlAttribute;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Data type corresponding to XML posted by server. The XML looks like this:
 * <item productId="3212" quantity="4" />
 * 
 * @author v.lakshmanan
 *
 */
// TODO: Note that usually a client developer needs to define this class explicitly.
// REST services often don't supply a schema that defines their message
// payloads, so you can't use a tool to generate the required JavaBean classes.
// (No code changes required.)
@XmlRootElement
public class Item {
    private int productId;
    private int quantity;

    public Item() {
        this(-1, -1);
    }

    public Item(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    @XmlAttribute
    // BONUS TODO: remove @XmlAttribute so JSON members are marshalled as elements
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    @XmlAttribute
    // BONUS TODO: remove @XmlAttribute so JSON members are marshalled as elements
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
