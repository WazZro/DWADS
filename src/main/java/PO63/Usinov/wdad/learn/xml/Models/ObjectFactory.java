package PO63.Usinov.wdad.learn.xml.Models;

import javax.xml.bind.annotation.XmlRegistry;
import java.io.Serializable;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the xml package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory implements Serializable {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: xml
     * 
     */
    public ObjectFactory() {
    }

    public Note createNote() {
        return new Note();
    }

    /**
     * Create an instance of {@link Privileges }
     * 
     */
    public Privileges createPrivileges() {
        return new Privileges();
    }

    /**
     * Create an instance of {@link ALL }
     * 
     */
    public ALL createALL() {
        return new ALL();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Notes }
     * 
     */
    public Notes createNotes() {
        return new Notes();
    }

}
