package PO63.Usinov.wdad.learn.rmi;

import PO63.Usinov.wdad.data.managers.Appconfig;
import PO63.Usinov.wdad.data.managers.PreferencesManager;
import PO63.Usinov.wdad.data.managers.XmlDataManager;
import PO63.Usinov.wdad.data.managers.XmlDataManagerImpl;
import PO63.Usinov.wdad.utils.PreferencesManagerConstants;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Server {
    private PreferencesManager pm;
    private Registry rmi;
    private static final String CONFIG_NAME = "appconfig.xml";
    private static final String REMOTE_OBJECT_NAME = "XmlDataManagerImpl";

    public void main() {
        pm = PreferencesManager.getInstance();

        try {
            pm.read(CONFIG_NAME);

            var address = pm.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
            var port = Integer.parseInt(pm.getProperty(PreferencesManagerConstants.REGISTRY_PORT));

            rmi = pm.isCreateRegistry() ?
                    LocateRegistry.createRegistry(port) :
                    LocateRegistry.getRegistry(address, port);

            var xmlImpl = new XmlDataManagerImpl();
            xmlImpl.init();

            var stub = (XmlDataManager) UnicastRemoteObject.exportObject(xmlImpl, 0);
            rmi.bind(REMOTE_OBJECT_NAME, stub);
            pm.addBindedObject(REMOTE_OBJECT_NAME, stub.getClass().getCanonicalName());
            PreferencesManager.deserialize(CONFIG_NAME, Appconfig.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
