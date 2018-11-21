package PO63.Usinov.wdad.learn.xml.Models;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.NormalizedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "user")
public class User implements Serializable {
    @XmlAttribute(name = "name")
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String name;
    @XmlAttribute(name = "mail", required = true)
    @XmlJavaTypeAdapter(NormalizedStringAdapter.class)
    protected String mail;
    @XmlAttribute(name = "rights")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String rights;

    /**
     * Gets the value of the name property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the mail property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the value of the mail property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMail(String value) {
        this.mail = value;
    }

    /**
     * Gets the value of the rights property.
     *
     * @return possible object is
     * {@link String }
     */
    public String getRights() {
        if (rights == null) {
            return "R";
        } else {
            return rights;
        }
    }

    /**
     * Sets the value of the rights property.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRights(String value) {
        this.rights = value;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;

        if (obj == null || obj.getClass() != this.getClass()) return false;

        User u = (User) obj;
        return name.equals(u.name) && mail.equals(u.mail);
    }
}
