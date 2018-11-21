package PO63.Usinov.wdad;

import PO63.Usinov.wdad.data.managers.PreferencesManager;
import PO63.Usinov.wdad.learn.rmi.Server;
import PO63.Usinov.wdad.learn.xml.XmlTask;

public class Application {
    public static void main(String[] args){
        var server = new Server();
        server.main();
    }
}
