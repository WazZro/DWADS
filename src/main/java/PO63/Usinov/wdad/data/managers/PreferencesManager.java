package PO63.Usinov.wdad.data.managers;

import PO63.Usinov.wdad.utils.PreferencesManagerConstants;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PreferencesManager {
    private String filename;
    private Properties properties;
    protected static Appconfig rootElement;

    protected final static PreferencesManager instance = new PreferencesManager();

    public static PreferencesManager getInstance() {
        return instance;
    }

    public static Object deserialize(String filename, Class c) throws Exception {
        StringReader sr = new StringReader(new String(Files.readAllBytes(Paths.get(filename))));
        JAXBContext context = JAXBContext.newInstance(c);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        instance.filename = filename;
        return unmarshaller.unmarshal(sr);
    }

    public static void serialize(String filename, Class c, Object obj) throws Exception {
        JAXBContext context = JAXBContext.newInstance(c);
        Marshaller marshaller = context.createMarshaller();
        marshaller.marshal(obj, new FileOutputStream(filename));
    }

    public void read(String filename) throws Exception {
        rootElement = (Appconfig) deserialize(filename, Appconfig.class);
        properties = new Properties(rootElement);
    }

    public void setProperty(String key, String value) throws Exception {
        properties.setProperty(key, value);
        serialize(filename, Appconfig.class, rootElement);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public void setProperties(Properties prop) {
        this.properties = prop;
    }

    public Properties getProperties() {
        return properties;
    }

    public void addBindedObject(String name, String className) {
        var obj = new Bindedobject();
        obj.name = name;
        obj.clazz = className;
        var array = rootElement.rmi.server.registryOrBindedobject;
        array.add(obj);
    }

    public void removeBindedObject(String name) {
        var array = rootElement.rmi.server.registryOrBindedobject;
        var i = array.listIterator();
        while (i.hasNext()) {
            var o = i.next();
            if (o instanceof Registry)
                continue;
            var bo = (Bindedobject) o;
            if (bo.name.equals(name)) {
                i.remove();
                break;
            }
        }
    }

    public boolean isCreateRegistry() {
        return getProperty(PreferencesManagerConstants.CREATE_REGISTRY).equals("yes");
    }

    @Deprecated
    public Rmi getRmi() {
        return rootElement.rmi;
    }

    @Deprecated
    public void setRmi(Rmi rmi) {
        rootElement.rmi = rmi;
    }

    @Deprecated
    public Server getServer() {
        return rootElement.rmi.server;
    }

    @Deprecated
    public void setServer(Server server) {
        rootElement.rmi.server = server;
    }

    @Deprecated
    public Client getClient() {
        return rootElement.rmi.client;
    }

    @Deprecated
    public void setClient(Client client) {
        rootElement.rmi.client = client;
    }

    @Deprecated
    public void setClassprovider(String classprovider) {
        rootElement.rmi.classprovider = classprovider;
    }

    @Deprecated
    public String getClassprovider() {
        return rootElement.rmi.classprovider;
    }

    @Deprecated
    public List<Object> getRegistryOrBindedObject() {
        return rootElement.rmi.server.registryOrBindedobject;
    }

    @Deprecated
    public void setRegistryOrBindedObject(List<Object> objects) {
        rootElement.rmi.server.registryOrBindedobject = objects;
    }

    @Deprecated
    public void setPolicyPath(String policyPath) {
        rootElement.rmi.client.policypath = policyPath;
    }

    @Deprecated
    public String getPolicyPath() {
        return rootElement.rmi.client.policypath;
    }

    @Deprecated
    public void setUseCodeBaseOnly(String useCodeBaseOnly) {
        rootElement.rmi.client.usecodebaseonly = useCodeBaseOnly;
    }

    @Deprecated
    public String getUseCodeBaseOnly() {
        return rootElement.rmi.client.usecodebaseonly;
    }
}
