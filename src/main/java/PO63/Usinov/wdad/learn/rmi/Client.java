package PO63.Usinov.wdad.learn.rmi;

import PO63.Usinov.wdad.data.managers.Appconfig;
import PO63.Usinov.wdad.data.managers.PreferencesManager;
import PO63.Usinov.wdad.data.managers.XmlDataManager;
import PO63.Usinov.wdad.data.managers.XmlDataManagerImpl;
import PO63.Usinov.wdad.utils.PreferencesManagerConstants;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Client {
    private PreferencesManager pm;
    private Registry rmi;
    private static final String CONFIG_NAME = "appconfig.xml";
    private static final String REMOTE_OBJECT_NAME = "XmlDataManagerImpl";


    public XmlDataManager main() {
        pm = PreferencesManager.getInstance();

        try {
            pm.read(CONFIG_NAME);
            var address = pm.getProperty(PreferencesManagerConstants.REGISTRY_ADDRESS);
            var port = Integer.parseInt(pm.getProperty(PreferencesManagerConstants.REGISTRY_PORT));

            rmi = LocateRegistry.getRegistry(address, port);
            return  (XmlDataManager) rmi.lookup(REMOTE_OBJECT_NAME);
        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }
}
