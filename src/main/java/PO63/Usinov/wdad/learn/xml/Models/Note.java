package PO63.Usinov.wdad.learn.xml.Models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "title",
    "text",
    "owner",
    "privileges",
    "cdate"
})
@XmlRootElement(name = "note")
public class Note implements Serializable {

    @XmlElement(required = true)
    protected String title;
    @XmlElement(required = true)
    protected String text;
    @XmlElement(required = true)
    protected User owner;
    @XmlElement(required = true)
    protected Privileges privileges;
    protected String cdate;

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setOwner(User value) {
        this.owner = value;
    }

    /**
     * Gets the value of the privileges property.
     * 
     * @return
     *     possible object is
     *     {@link Privileges }
     *     
     */
    public Privileges getPrivileges() {
        return privileges;
    }

    /**
     * Sets the value of the privileges property.
     * 
     * @param value
     *     allowed object is
     *     {@link Privileges }
     *     
     */
    public void setPrivileges(Privileges value) {
        this.privileges = value;
    }

    /**
     * Gets the value of the cdate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * Sets the value of the cdate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCdate(String value) {
        this.cdate = value;
    }

    public void update(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        Note o = (Note) obj;
        return title.equals(o.title) && cdate.equals(o.cdate) && text.equals(o.text);
    }
}
