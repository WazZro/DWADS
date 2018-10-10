package PO63.Usinov.wdad.learn.xml.Models;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "allOrUser"
})
@XmlRootElement(name = "privileges")
public class Privileges {
    @XmlElements({
        @XmlElement(name = "ALL", required = true, type = ALL.class),
        @XmlElement(name = "user", required = true, type = User.class)
    })
    protected List<Object> allOrUser;

    /**
     * Gets the value of the allOrUser property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the allOrUser property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getALLOrUser().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ALL }
     * {@link User }
     * 
     * 
     */
    public List<Object> getALLOrUser() {
        if (allOrUser == null) {
            allOrUser = new ArrayList<Object>();
        }
        return this.allOrUser;
    }
}