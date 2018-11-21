package PO63.Usinov.wdad.data.managers;

import PO63.Usinov.wdad.utils.PreferencesManagerConstants;

import java.lang.reflect.InvocationTargetException;
import java.util.NoSuchElementException;

public class Properties {
    private static final String DEF_CLASS_PROVIDER_DEFAULT = "http://www.yourhost.free.ru/cp/cp.jar";
    private static final String DEF_POLICY_PATH_DEFAULT = "client.policy";
    private static final String DEF_USE_CODE_BASE_ONLY_DEFAULT = "no";
    private static final String DEF_CREATE_REGISTRY_DEFAULT = "yes";
    private static final String DEF_REGISTRY_ADDRESS = "localhost";
    private static final String DEF_REGISTRY_PORT = "1099";

    private Appconfig appconfig;

    Properties(Appconfig appconfig) {
        this.appconfig = appconfig;
    }

    public void setProperty(String key, String value) {
        switch (key) {
            case PreferencesManagerConstants.CREATE_REGISTRY:
                ((Registry) appconfig.rmi.server.registryOrBindedobject.get(0)).createregistry = value;
                return;
            case PreferencesManagerConstants.REGISTRY_ADDRESS:
                ((Registry) appconfig.rmi.server.registryOrBindedobject.get(0)).registryaddress = value;
                return;
            case PreferencesManagerConstants.REGISTRY_PORT:
                ((Registry) appconfig.rmi.server.registryOrBindedobject.get(0)).registryport = value;
                return;
        }

        String[] path = key.split("\\.");

        if (path[0].equalsIgnoreCase("appconfig")) {
            try {
                Object o = getObject(path);
                var set = o.getClass().getMethod("set" + path[path.length - 1], String.class);
                set.invoke(o, value);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new NoSuchElementException("No element found");
            }
        } else
            throw new NoSuchElementException("No element found");
    }

    public String getProperty(String key) {
        switch (key) {
            case PreferencesManagerConstants.CREATE_REGISTRY:
                return ((Registry) appconfig.rmi.server.registryOrBindedobject.get(0)).createregistry;
            case PreferencesManagerConstants.REGISTRY_ADDRESS:
                return ((Registry) appconfig.rmi.server.registryOrBindedobject.get(0)).registryaddress;
            case PreferencesManagerConstants.REGISTRY_PORT:
                return ((Registry) appconfig.rmi.server.registryOrBindedobject.get(0)).registryport;
        }

        String[] path = key.split("\\.");

        if (path[0].equalsIgnoreCase("appconfig")) {
            try {
                Object o = getObject(path);
                var set = o.getClass().getMethod("get" + path[path.length - 1]);
                return (String) set.invoke(o);

            } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
                throw new NoSuchElementException("No element found");
            }
        } else
            throw new NoSuchElementException("No element found");
    }


    public Object getObject(String[] path) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Properties.normalizeKey(path);
        Object[] objects = new Object[path.length];
        objects[0] = appconfig;

        for (int i = 1; i < path.length - 1; i++) {
            var get = objects[i - 1].getClass().getMethod("get" + path[i]);
            objects[i] = get.invoke(objects[i - 1]);
        }

        return objects[objects.length - 1];
    }

    public static void normalizeKey(String[] path) {
        for (int i = 0; i < path.length; i++) {
            var str = path[i];
            var chars = str.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            path[i] = new String(chars);
        }
    }

    private void setClientProperty(String key, String value) {
        switch (key) {
            case "policypath":

                break;
            case "usecodebaseonly":
                break;
        }
    }
}
